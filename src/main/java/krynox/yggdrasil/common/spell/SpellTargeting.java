package krynox.yggdrasil.common.spell;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.Vec3d;

import java.util.function.Predicate;

public class SpellTargeting {
    /**
     * Wrapper around mojang's ProjectileHelper::rayTraceEntities to make it a little more ergonomic for my
     * purposes.
     */
    public static EntityRayTraceResult rayTraceEntity(PlayerEntity player, Predicate<Entity> filter, double maxDistance) {
        Vec3d start = player.getPositionVec();
        Vec3d end = start.add(player.getLookVec().scale(maxDistance));

        AxisAlignedBB aabb = new AxisAlignedBB(start.x, start.y, start.z, end.x, end.y, end.z);
        aabb.grow(4);

        return ProjectileHelper.rayTraceEntities(player, start, end, aabb, filter, maxDistance);
    }
}
