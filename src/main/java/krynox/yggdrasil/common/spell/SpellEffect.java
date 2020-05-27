package krynox.yggdrasil.common.spell;

import krynox.yggdrasil.Yggdrasil;
import krynox.yggdrasil.common.init.ModCapabilities;
import krynox.yggdrasil.common.util.damagesource.SpiritEmptyDamageSource;
import net.minecraft.entity.LivingEntity;

import java.util.List;

public class SpellEffect {
    public static void spiritDamage(List<LivingEntity> targets, int damage, LivingEntity caster) {
        for(LivingEntity e : targets) {
            spiritDamage(e, damage, caster);
        }
    }

    public static void spiritDamage(LivingEntity target, int damage, LivingEntity caster) {
        target.getCapability(ModCapabilities.STATS_CAP).ifPresent((cap) -> {
            cap.decrementBarrier(damage);
            if(cap.getBarrier() <= 0) {
                target.attackEntityFrom(new SpiritEmptyDamageSource(caster), target.getMaxHealth()*1000);
            }
            Yggdrasil.LOGGER.info(target.getDisplayName() + " hit by " + caster.getDisplayName());
        });

    }
}
