package krynox.yggdrasil.common.util;

import java.util.Optional;

import krynox.yggdrasil.Yggdrasil;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class RegistryHelper<T extends IForgeRegistryEntry<T>> {

    private Class<T> clazz;
    private IForgeRegistry<T> registry = null;

    public RegistryHelper(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void init() {
        if (registry == null) {
            registry = GameRegistry.findRegistry(clazz);
        } else {
            Yggdrasil.LOGGER.fatal("Called RegistryHelper::init after it was already initialised.");
        }
    }

    public Optional<T> get(String registryName) {
        return Optional.ofNullable(new ResourceLocation(registryName)).map((resloc) -> registry.getValue(resloc));
    }

}
