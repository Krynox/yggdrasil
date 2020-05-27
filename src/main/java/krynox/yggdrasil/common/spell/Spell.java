package krynox.yggdrasil.common.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class Spell extends ForgeRegistryEntry<Spell> {
    public abstract void cast(PlayerEntity player);

    public abstract int getCooldown();

    public abstract int getMomentum();

    public abstract void render();
}
