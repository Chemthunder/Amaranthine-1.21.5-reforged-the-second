package net.chemthunder.amaranthine.mixin;

import net.chemthunder.amaranthine.init.ModStatusEffects;
import net.chemthunder.amaranthine.item.ChrysaorItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = {"attack"},
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;addCritParticles(Lnet/minecraft/entity/Entity;)V"
            )}
    )
    private void reapTargetOnCrit(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        ItemStack stack = this.getStackInHand(Hand.MAIN_HAND);
        if (stack.getItem() instanceof ChrysaorItem item) {
            if (target instanceof LivingEntity living) {
                living.addStatusEffect(
                        new StatusEffectInstance(
                                ModStatusEffects.FRACTURED,
                                40,
                                1
                        )
                        );

            }


        }
    }
}