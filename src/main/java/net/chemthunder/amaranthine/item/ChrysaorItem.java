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
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class ChrysaorItem extends Item implements CustomHitParticleItem, KillEffectItem {
    public ChrysaorItem(Settings settings) {
        super(settings);
    }



    public static final SweepParticleEffect[] EFFECTS = new SweepParticleEffect[]{new SweepParticleEffect(0xd9a3ff, 0xbe63ff), new SweepParticleEffect(0x602a87, 0x4a2763)};

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
        textConsumer.accept(Text.translatable("item.amaranthine.chrysaor.desc").styled(style -> style.withColor(0x35253B)));
    }


    public void killEntity(World world, ItemStack stack, LivingEntity user, LivingEntity victim) {
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.END_ROD, victim.getPos().x, victim.getPos().y, victim.getPos().z, 16, 0, 0.6, 0, 0.2);
        }
    }

    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

    }




    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState state = context.getWorld().getBlockState(context.getBlockPos());
        PlayerEntity user = context.getPlayer();
        if (user != null && user.isSneaking() && state.isOf(Blocks.ANVIL)) {
            ItemStack stack = user.getMainHandStack();
            if (stack.isOf(ModItems.CHRYSAOR)) {
                stack.decrement(1);
                user.giveItemStack(ModItems.BLIND_OBEDIENCE.getDefaultStack());
                user.playSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 0.8F, 1.0F);
            }
            return ActionResult.SUCCESS;
        }
        return super.useOnBlock(context);
    }


}
