package krynox.yggdrasil.common.init;

import static krynox.yggdrasil.common.capability.CapabilityRegistrationHelper.attachCap;
import static krynox.yggdrasil.common.capability.CapabilityRegistrationHelper.cloneCapForPlayerDeath;
import static krynox.yggdrasil.common.capability.CapabilityRegistrationHelper.registerCap;

import krynox.yggdrasil.Yggdrasil;
import krynox.yggdrasil.common.capability.cooldowns.Cooldowns;
import krynox.yggdrasil.common.capability.cooldowns.CooldownsImpl;
import krynox.yggdrasil.common.capability.loadout.Loadout;
import krynox.yggdrasil.common.capability.loadout.LoadoutImpl;
import krynox.yggdrasil.common.capability.spellbook.Spellbook;
import krynox.yggdrasil.common.capability.spellbook.SpellbookTree;
import krynox.yggdrasil.common.capability.stats.Stats;
import krynox.yggdrasil.common.capability.stats.StatsImpl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Yggdrasil.MODID, bus = Bus.FORGE)
public class ModCapabilities {
    /*
     * INJECTED CAP FIELDS
     */
    @CapabilityInject(Spellbook.class)
    public static final Capability<Spellbook> SPELLBOOK_CAP = null;

    @CapabilityInject(Loadout.class)
    public static final Capability<Loadout> LOADOUT_CAP = null;

    @CapabilityInject(Cooldowns.class)
    public static final Capability<Cooldowns> COOLDOWNS_CAP = null;

    @CapabilityInject(Stats.class)
    public static final Capability<Stats> STATS_CAP = null;
    
    public static void registerAll() {
        registerCap(Spellbook.class, SpellbookTree::new, SpellbookTree::deserialize, SpellbookTree::serialize);
        registerCap(Loadout.class, LoadoutImpl::new, LoadoutImpl::deserialize, LoadoutImpl::serialize);
        registerCap(Cooldowns.class, CooldownsImpl::new, CooldownsImpl::deserialize, CooldownsImpl::serialize);
        registerCap(Stats.class, StatsImpl::new, StatsImpl::deserialize, StatsImpl::serialize);
    }

    @SubscribeEvent
    public static void attachYggdrasilCapabilities(AttachCapabilitiesEvent<Entity> e) {
        if (e.getObject() instanceof PlayerEntity) {
            attachCap(e, SPELLBOOK_CAP, "capability_spellbook");
            attachCap(e, LOADOUT_CAP, "capability_loadout");
            attachCap(e, COOLDOWNS_CAP, "capability_cooldowns");
        }

        if (e.getObject() instanceof LivingEntity) {
            attachCap(e, STATS_CAP, "capability_stats");
        }
    }

    @SubscribeEvent
    public static void persistYggdrasilCapsOnClone(PlayerEvent.Clone e) {
        PlayerEntity oldPlayer = e.getOriginal();
        PlayerEntity newPlayer = e.getPlayer();

        //Cooldowns and Stats not persisted on death; we want those to reset
        //todo - would like the 4th arg to be of the form Impl::clone, but java seems to need it spelled out like a baby
        cloneCapForPlayerDeath(oldPlayer, newPlayer, SPELLBOOK_CAP, (oldCap,newCap) -> SpellbookTree.clone((SpellbookTree) oldCap, (SpellbookTree) newCap));
        cloneCapForPlayerDeath(oldPlayer, newPlayer, LOADOUT_CAP, (oldCap,newCap) -> LoadoutImpl.clone((LoadoutImpl) oldCap, (LoadoutImpl) newCap));
    }
}
