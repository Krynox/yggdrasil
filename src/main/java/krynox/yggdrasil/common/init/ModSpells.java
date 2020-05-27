package krynox.yggdrasil.common.init;

import krynox.yggdrasil.Yggdrasil;
import krynox.yggdrasil.common.spell.Spell;
import krynox.yggdrasil.common.spell.elementalist.EmberSpell;
import krynox.yggdrasil.common.spell.elementalist.FrostbiteSpell;
import krynox.yggdrasil.common.spell.elementalist.MagicMissileSpell;
import krynox.yggdrasil.common.spell.elementalist.SparkSpell;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Yggdrasil.MODID, bus = Bus.MOD)
@ObjectHolder("yggdrasil")
public class ModSpells {

    //objectholders auto-filled by back magic
    public static final Spell EMBER = null;
    public static final Spell SPARK = null;
    public static final Spell FROSTBITE = null;
    public static final Spell MAGIC_MISSILE = null;

    @SubscribeEvent
    public static void registerSpells(RegistryEvent.Register<Spell> e) {
        e.getRegistry().registerAll(
                new EmberSpell().setRegistryName(Yggdrasil.MODID, "ember"),
                new SparkSpell().setRegistryName(Yggdrasil.MODID, "spark"),
                new FrostbiteSpell().setRegistryName(Yggdrasil.MODID, "frostbite"),
                new MagicMissileSpell().setRegistryName(Yggdrasil.MODID, "magic_missile")
        );
    }
    
}
