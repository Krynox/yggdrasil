package krynox.yggdrasil.common.capability;

import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Function;

import krynox.yggdrasil.Yggdrasil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import static krynox.yggdrasil.common.init.ModCapabilities.SPELLBOOK_CAP;

public class CapabilityRegistrationHelper {

    public static <Cap, Impl extends Cap> void registerCap(Class<Cap> capClass, Callable<Impl> defaultImplSupplier,
            Function<INBT, Impl> deserializer, Function<Impl, INBT> serializer) {

        CapabilityManager.INSTANCE.register(capClass,

                new Capability.IStorage<Cap>() {

                    @Override
                    public INBT writeNBT(Capability<Cap> capability, Cap instance, Direction side) {
                        return serializer.apply((Impl) instance);
                    }

                    @Override
                    public void readNBT(Capability<Cap> capability, Cap instance, Direction side, INBT nbt) {
                        instance = deserializer.apply(nbt);
                    }
                },

                defaultImplSupplier);
    }

    public static <EventType, Cap> void attachCap(AttachCapabilitiesEvent<EventType> event, Capability<Cap> capability,
            String capName) {
        LazyOptional<Cap> instance = LazyOptional.of(capability::getDefaultInstance);

        event.addCapability(new ResourceLocation(Yggdrasil.MODID, capName), new ICapabilitySerializable<INBT>() {

            String errorMsg = "Default instance not available for " + capName
                    + ". This is an error and should be reported to the mod author.";

            @Override
            public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
                return capability.orEmpty(cap, instance);
            }

            @Override
            public INBT serializeNBT() {
                return capability.getStorage().writeNBT(capability,
                        instance.orElseThrow(() -> new RuntimeException(errorMsg)), null);
            }

            @Override
            public void deserializeNBT(INBT nbt) {
                capability.getStorage().readNBT(capability, instance.orElseThrow(() -> new RuntimeException(errorMsg)),
                        null, nbt);
            }
        });
    }

    public static <Cap, Impl extends Cap> void cloneCapForPlayerDeath(PlayerEntity oldPlayer, PlayerEntity newPlayer,
            Capability<Cap> cap, BiConsumer<Impl, Impl> cloner) {

        oldPlayer.getCapability(cap).ifPresent((oldCap) -> {
            newPlayer.getCapability(cap).ifPresent((newCap) -> {
                cloner.accept((Impl) oldCap, (Impl) newCap);
                Yggdrasil.LOGGER.info("Cloned " + oldCap.getClass() + " of player " + oldPlayer.getName().getFormattedText());
            });
        });
    }

}
