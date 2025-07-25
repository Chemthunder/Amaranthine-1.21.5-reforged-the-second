package net.chemthunder.amaranthine.item;

import net.acoyt.acornlib.client.particle.SweepParticleEffect;
import net.acoyt.acornlib.item.CustomHitParticleItem;
import net.acoyt.acornlib.item.KillEffectItem;
import net.chemthunder.amaranthine.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.function.Consumer;

//
public class BlindObedienceItem extends Item implements KillEffectItem, CustomHitParticleItem {
    public static final SweepParticleEffect[] EFFECTS = new SweepParticleEffect[]{new SweepParticleEffect(0x0c0912, 0x231a36), new SweepParticleEffect(0x8145ff, 0x5200ff)};

    public BlindObedienceItem(Settings settings) {
        super(settings);
    }

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


    @SuppressWarnings("deprecation")
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.amaranthine.blind_obedience.desc").styled(style -> style.withColor(0x35253B)));
    }


    @Override
    public void killEntity(World world, ItemStack itemStack, LivingEntity livingEntity, LivingEntity livingEntity1) {
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState state = context.getWorld().getBlockState(context.getBlockPos());
        PlayerEntity user = context.getPlayer();
        if (user != null && user.isSneaking() && state.isOf(Blocks.ENCHANTING_TABLE)) {
            ItemStack stack = user.getMainHandStack();
            ItemStack stack_offhand = user.getOffHandStack();

            if (stack.isOf(ModItems.BLIND_OBEDIENCE) && (stack_offhand.isOf(ModItems.AMARANTHINE_SHARD))) {
                stack.decrement(1);
                user.giveItemStack(ModItems.CHRYSAOR.getDefaultStack());
                stack_offhand.decrement(0);
                user.playSound(SoundEvents.ENTITY_WITHER_DEATH, 0.8F, 1.0F);
            }

            return ActionResult.SUCCESS;
        }

        return super.useOnBlock(context);
    }
}
