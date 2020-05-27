package krynox.yggdrasil.client;

import krynox.yggdrasil.Yggdrasil;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Yggdrasil.MODID, bus = Bus.MOD)
public class ClientInit {
    @SubscribeEvent
    public static void initClient(FMLClientSetupEvent e) {
        KeybindHandler.registerBinds();
    }
}
