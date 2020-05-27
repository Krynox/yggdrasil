package krynox.yggdrasil.common.network;

import krynox.yggdrasil.Yggdrasil;
import krynox.yggdrasil.common.spell.elementalist.SparkSpell;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SpellMessage {
    private ResourceLocation spellToCast;

    public SpellMessage(ResourceLocation spellToCast) {
        this.spellToCast = spellToCast;
    }

    public ResourceLocation getSpellToCast() {
        return spellToCast;
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeResourceLocation(spellToCast);
    }

    public static SpellMessage decode(PacketBuffer buffer) {
        return new SpellMessage(buffer.readResourceLocation());
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        Yggdrasil.LOGGER.info("Handled packet! " + this.getSpellToCast());
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity sender = ctx.get().getSender();
            new SparkSpell().cast(sender);
        });
        ctx.get().setPacketHandled(true);
    }
}
