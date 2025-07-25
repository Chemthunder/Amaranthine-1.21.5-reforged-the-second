package net.chemthunder.amaranthine.init;

import net.chemthunder.amaranthine.Amaranthine;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface ModDamageSources {
    RegistryKey<DamageType> IMPALED = of("impaled");
    RegistryKey<DamageType> CLEAVER = of("cleaver");

    static DamageSource impaled(LivingEntity entity) {
        return entity.getDamageSources().create(IMPALED);
    }

    static DamageSource cleaver(LivingEntity entity) {
        return entity.getDamageSources().create(CLEAVER);
    }



    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Amaranthine.id(name));
    }
}
