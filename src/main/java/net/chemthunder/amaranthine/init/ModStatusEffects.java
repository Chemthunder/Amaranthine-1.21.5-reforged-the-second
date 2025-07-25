package net.chemthunder.amaranthine.init;

import net.chemthunder.amaranthine.Amaranthine;
import net.chemthunder.amaranthine.effect.DisorientatedEffect;
import net.chemthunder.amaranthine.effect.FracturedEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public interface ModStatusEffects {
    RegistryEntry<StatusEffect> FRACTURED = create("fractured", new FracturedEffect());
    RegistryEntry<StatusEffect> DISORIENTATED = create("disorientated", new DisorientatedEffect());


    private static RegistryEntry<StatusEffect> create(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Amaranthine.id(name), statusEffect);
    }

    static void init() {

    }
}
