package krynox.yggdrasil;

import krynox.yggdrasil.common.network.YggdrasilPacketHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import krynox.yggdrasil.common.init.ModCapabilities;
import krynox.yggdrasil.common.spell.Spell;
import krynox.yggdrasil.common.spell.Traitline;
import krynox.yggdrasil.common.util.RegistryHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Yggdrasil.MODID)
public class Yggdrasil {
    public static final String MODID = "yggdrasil";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static final RegistryHelper<Spell> REG_HELPER_SPELL = new RegistryHelper<>(Spell.class);
    public static final RegistryHelper<Traitline> REG_HELPER_TRAITLINE = new RegistryHelper<>(Traitline.class);
    
    public Yggdrasil() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
    }

    // fires AFTER registry events
    public void commonSetup(FMLCommonSetupEvent e) {
        ModCapabilities.registerAll();
        REG_HELPER_SPELL.init();
        REG_HELPER_TRAITLINE.init();
        YggdrasilPacketHandler.registerMessages();
    }
}
