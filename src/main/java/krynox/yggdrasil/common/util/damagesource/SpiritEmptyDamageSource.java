package krynox.yggdrasil.common.util.damagesource;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class SpiritEmptyDamageSource extends EntityDamageSource {
    public SpiritEmptyDamageSource(Entity damageSourceEntityIn) {
        super("yggdrasilSpirit", damageSourceEntityIn);
    }

    @Override
    public boolean isDamageAbsolute() {
        return true;
    }

    @Override
    public ITextComponent getDeathMessage(LivingEntity entityLivingBaseIn) {
        return new TranslationTextComponent("death.attack." + this.damageType, entityLivingBaseIn.getDisplayName(), this.damageSourceEntity.getDisplayName());
    }
}
