package net.chemthunder.amaranthine.item;

import net.acoyt.acornlib.client.particle.SweepParticleEffect;
import net.acoyt.acornlib.item.CustomHitParticleItem;
import net.chemthunder.amaranthine.init.ModStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CleaverItem extends Item implements CustomHitParticleItem {
    public CleaverItem(Settings settings) {
        super(settings);
    }


    public static final SweepParticleEffect[] EFFECTS = new SweepParticleEffect[]{new SweepParticleEffect(0x141313, 0x000000), new SweepParticleEffect(0x45140e, 0x2b0b07)};



    public void spawnHitParticles(PlayerEntity player) {
        double deltaX = -MathHelper.sin((float) (player.getYaw() * (Math.PI / 180.0F)));
        double deltaZ = MathHelper.cos((float) (player.getYaw() * (Math.PI / 180.0F)));
        World var7 = player.getWorld();
        if (var7 instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    EFFECTS[player.getRandom().nextInt(EFFECTS.length)],
                    player.getX() + deltaX,
                    player.getBodyY(0.5F),
                    player.getZ() + deltaZ,
                    0, deltaX, 0.0F, deltaZ, 0.0F
            );
        }
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        target.addStatusEffect(new StatusEffectInstance(ModStatusEffects.DISORIENTATED, 200, 0));

        super.postHit(stack, target, attacker);
    }
}
