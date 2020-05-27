package krynox.yggdrasil.common.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class YggdrasilPacketHandler {
    private static final String NET_PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("yggdrasil" ,"main"),
            () -> NET_PROTOCOL_VERSION,
            NET_PROTOCOL_VERSION::equals,
            NET_PROTOCOL_VERSION::equals);


    public static void registerMessages() {
        int id = 0;
        CHANNEL.registerMessage(id++, SpellMessage.class, SpellMessage::encode, SpellMessage::decode, SpellMessage::handle);

    }

}
