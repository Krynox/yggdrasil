package krynox.yggdrasil.common.capability.cooldowns;

import java.util.HashMap;
import java.util.Map;

import krynox.yggdrasil.Yggdrasil;
import krynox.yggdrasil.common.spell.Spell;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;

public class CooldownsImpl implements Cooldowns {
    HashMap<Spell, Integer> cooldowns;

    private CooldownsImpl(HashMap<Spell, Integer> cooldowns) {
        this.cooldowns = cooldowns;
    }
    
    public CooldownsImpl() {
        this(new HashMap<>());
    }

	@Override
	public int getTimeRemaining(Spell spell) {
        if(cooldowns.containsKey(spell)) {
            return cooldowns.get(spell);
        } else {
            return spell.getCooldown();
        }
	}

	@Override
	public void setTimeRemaining(Spell spell, int millis) {
        if(cooldowns.containsKey(spell)) {
            cooldowns.replace(spell, millis);
        } else {
            cooldowns.put(spell, millis);
        }
	}

	@Override
	public void putOnCooldown(Spell spell) {
        setTimeRemaining(spell, spell.getCooldown());
	}
    
    public static CooldownsImpl deserialize(INBT nbt) {
        CompoundNBT root = (CompoundNBT) nbt;
        HashMap<Spell, Integer> map = new HashMap<>();

        for(String spellRegName : root.keySet()) {
            Yggdrasil.REG_HELPER_SPELL.get(spellRegName).ifPresent((spell) -> map.put(spell, root.getInt(spellRegName)));
        }

        return new CooldownsImpl(map);
    }

    public static INBT serialize(CooldownsImpl cooldowns) {
        CompoundNBT nbt = new CompoundNBT();
        for (Map.Entry<Spell, Integer> pair : cooldowns.cooldowns.entrySet()) {
            nbt.putInt(pair.getKey().getRegistryName().toString(), pair.getValue());
        }
        return nbt;
    }

    public static void clone(CooldownsImpl oldCooldowns, CooldownsImpl newCooldowns) {
        newCooldowns.cooldowns = oldCooldowns.cooldowns;
    }
}
