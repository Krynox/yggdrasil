package krynox.yggdrasil.common.init;

import krynox.yggdrasil.Yggdrasil;
import krynox.yggdrasil.common.spell.Spell;
import krynox.yggdrasil.common.spell.Traitline;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber(modid = Yggdrasil.MODID, bus = Bus.MOD)
public class ModRegistries {

    @SubscribeEvent
    public static void registerRegistries(RegistryEvent.NewRegistry e) {
        Yggdrasil.LOGGER.info("Regiatering spell registry.");

        new RegistryBuilder<Spell>()
            .setType(Spell.class)
            .setName(new ResourceLocation(Yggdrasil.MODID, "spell_registry"))
            .create();

        new RegistryBuilder<Traitline>()
            .setType(Traitline.class)
            .setName(new ResourceLocation(Yggdrasil.MODID, "traitline_registry"))
            .create();
    }
    
}
