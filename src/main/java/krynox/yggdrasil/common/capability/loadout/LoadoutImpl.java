package krynox.yggdrasil.common.capability.loadout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import krynox.yggdrasil.Yggdrasil;
import krynox.yggdrasil.common.spell.Traitline;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;

public class LoadoutImpl implements Loadout {
    Set<Traitline> equippedTraitlines;

	public LoadoutImpl() {
        equippedTraitlines = new HashSet<>();
	}
    
    @Override
	public boolean equipTraitline(Traitline traitline) {
        if(equippedTraitlines.contains(traitline)) {
            return false;
        }	

        equippedTraitlines.add(traitline);
        return true;
	}

	@Override
	public boolean dequipTraitline(Traitline traitline) {
        if(!equippedTraitlines.contains(traitline)) {
            return false;
        }	

        equippedTraitlines.remove(traitline);
        return true;
	}

	@Override
	public List<Traitline> getEquippedTraitlines() {
		return new ArrayList<Traitline>(equippedTraitlines);
	}

    public static LoadoutImpl deserialize(INBT nbt) {
        CompoundNBT root = (CompoundNBT) nbt;
        ListNBT loadoutTag = root.getList("equippedTraitlines", 8); //8 is the magic number for StringNBT id
        LoadoutImpl loadout = new LoadoutImpl();

        loadoutTag.iterator().forEachRemaining((strTag) -> Yggdrasil.REG_HELPER_TRAITLINE.get(strTag.getString()));
        return loadout;
    }

    public static INBT serialize(LoadoutImpl loadout) {
        CompoundNBT root = new CompoundNBT();
        ListNBT loadoutTag = new ListNBT();
        for (Traitline t : loadout.equippedTraitlines) {
            loadoutTag.add(StringNBT.valueOf(t.getRegistryName().toString()));
        }
        
        root.put("equippedTraitlines", loadoutTag);
        return root;
    }

    public static void clone(LoadoutImpl oldLoadout, LoadoutImpl newLoadout) {
        newLoadout.equippedTraitlines = oldLoadout.equippedTraitlines;
    }

}
