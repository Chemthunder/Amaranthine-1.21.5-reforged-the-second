package net.chemthunder.amaranthine.init;

import net.chemthunder.amaranthine.Amaranthine;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface ModEnchantments {
    RegistryKey<Enchantment> SPLINTER = of("splinter");

    private static RegistryKey<Enchantment> of(String name) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Amaranthine.id(name));
    }
}
