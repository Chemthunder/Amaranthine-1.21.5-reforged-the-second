package net.chemthunder.amaranthine.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;

import static net.chemthunder.amaranthine.init.ModDamageSources.cleaver;

public class DisorientatedEffect extends StatusEffect {
    public DisorientatedEffect() {
        super(StatusEffectCategory.HARMFUL, 0x000000);
    }



    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        entity.swingHand(Hand.MAIN_HAND);
        entity.tiltScreen(150, 150);
        entity.damage(world, cleaver(entity), 5f);

        return super.applyUpdateEffect(world, entity, amplifier);
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 30;
        return duration % i == 0;
    }
}
