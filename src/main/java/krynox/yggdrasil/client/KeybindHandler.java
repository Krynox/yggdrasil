package krynox.yggdrasil.client;

import krynox.yggdrasil.common.network.SpellMessage;
import krynox.yggdrasil.common.network.YggdrasilPacketHandler;
import krynox.yggdrasil.common.spell.elementalist.SparkSpell;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.Level;
import org.lwjgl.glfw.GLFW;

import krynox.yggdrasil.Yggdrasil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Yggdrasil.MODID)
public class KeybindHandler {
    public static final KeyBinding spellcastingModeKeybind = new KeyBinding("yggdrasil.keybind.spell", GLFW.GLFW_KEY_BACKSLASH, "yggdrasil.keyCategory");
    public static final KeyBinding wordBase1Keybind = new KeyBinding("yggdrasil.keybind.wordBase1", GLFW.GLFW_KEY_BACKSLASH, "yggdrasil.keyCategory");
    public static final KeyBinding wordBase2Keybind = new KeyBinding("yggdrasil.keybind.wordBase2", GLFW.GLFW_KEY_J, "yggdrasil.keyCategory");
    public static final KeyBinding wordBase3Keybind = new KeyBinding("yggdrasil.keybind.wordBase3", GLFW.GLFW_KEY_K, "yggdrasil.keyCategory");
    public static final KeyBinding wordTrait1Keybind = new KeyBinding("yggdrasil.keybind.wordTrait1", GLFW.GLFW_KEY_U, "yggdrasil.keyCategory"); 
    public static final KeyBinding wordTrait2Keybind = new KeyBinding("yggdrasil.keybind.wordTrait2", GLFW.GLFW_KEY_U, "yggdrasil.keyCategory");
    public static final KeyBinding wordTrait3Keybind = new KeyBinding("yggdrasil.keybind.wordTrait3", GLFW.GLFW_KEY_I, "yggdrasil.keyCategory");
    public static final KeyBinding wordTrait4Keybind = new KeyBinding("yggdrasil.keybind.wordTrait4", GLFW.GLFW_KEY_O, "yggdrasil.keyCategory");
    public static final KeyBinding wordTrait5Keybind = new KeyBinding("yggdrasil.keybind.wordTrait5", GLFW.GLFW_KEY_P, "yggdrasil.keyCategory");

    public static void registerBinds() {
        Yggdrasil.LOGGER.log(Level.DEBUG, "Registering keybinds.");
        
        ClientRegistry.registerKeyBinding(spellcastingModeKeybind);
        ClientRegistry.registerKeyBinding(wordBase1Keybind);
        ClientRegistry.registerKeyBinding(wordBase2Keybind);
        ClientRegistry.registerKeyBinding(wordBase3Keybind);
        ClientRegistry.registerKeyBinding(wordTrait1Keybind);
        ClientRegistry.registerKeyBinding(wordTrait2Keybind);
        ClientRegistry.registerKeyBinding(wordTrait3Keybind);
        ClientRegistry.registerKeyBinding(wordTrait4Keybind);
        ClientRegistry.registerKeyBinding(wordTrait5Keybind);
    }

    @SubscribeEvent
    public static void onKeyPress(ClientTickEvent e) {
        if(e.phase == Phase.END) {
            if(spellcastingModeKeybind.isKeyDown()) {
                onSpellKeyDown(e);
            }

            //TODO - if in spellcasting mode
            if(true) {
                if(wordBase1Keybind.isKeyDown()) {
                    onBase1KeyDown(e);
                }
                
                if(wordBase2Keybind.isKeyDown()) {
                    onBase2KeyDown(e);
                }
                
                if(wordBase3Keybind.isKeyDown()) {
                    onBase3KeyDown(e);
                }
                
                if(wordTrait1Keybind.isKeyDown()) {
                    onTrait1KeyDown(e);
                }
                
                if(wordTrait2Keybind.isKeyDown()) {
                    onTrait2KeyDown(e);
                }
                
                if(wordTrait3Keybind.isKeyDown()) {
                    onTrait3KeyDown(e);
                }
                
                if(wordTrait4Keybind.isKeyDown()) {
                    onTrait4KeyDown(e);
                }
                
                if(wordTrait5Keybind.isKeyDown()) {
                    onTrait5KeyDown(e);
                }
            }
        }
    }

    private static void onSpellKeyDown(ClientTickEvent e) {
        YggdrasilPacketHandler.CHANNEL.sendToServer(new SpellMessage(new ResourceLocation("spark")));
    }

    private static void onBase1KeyDown(ClientTickEvent e) {
       
    }
    
    private static void onBase2KeyDown(ClientTickEvent e) {
       
    }

    private static void onBase3KeyDown(ClientTickEvent e) {
       
    }
    
    private static void onTrait1KeyDown(ClientTickEvent e) {
       
    }

    private static void onTrait2KeyDown(ClientTickEvent e) {
       
    }
    
    private static void onTrait3KeyDown(ClientTickEvent e) {
       
    }
    
    private static void onTrait4KeyDown(ClientTickEvent e) {
       
    }

    private static void onTrait5KeyDown(ClientTickEvent e) {
       
    }
}
