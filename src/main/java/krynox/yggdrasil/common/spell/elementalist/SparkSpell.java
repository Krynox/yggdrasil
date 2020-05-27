package krynox.yggdrasil.common.spell.elementalist;

import krynox.yggdrasil.common.spell.Spell;
import krynox.yggdrasil.common.spell.SpellEffect;
import krynox.yggdrasil.common.spell.SpellTargeting;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.EntityRayTraceResult;

public class SparkSpell extends Spell {
    @Override
    public void cast(PlayerEntity player) {
        EntityRayTraceResult r = SpellTargeting.rayTraceEntity(player, (e) -> e instanceof LivingEntity, 10);
        if(r != null) {
            SpellEffect.spiritDamage((LivingEntity) r.getEntity(), 30, player);
        }
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public int getMomentum() {
        return 0;
    }

    @Override
    public void render() {

    }
}
