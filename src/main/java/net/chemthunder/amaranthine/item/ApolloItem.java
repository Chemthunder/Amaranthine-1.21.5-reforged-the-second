package net.chemthunder.amaranthine.item;

import net.acoyt.acornlib.client.particle.SweepParticleEffect;
import net.acoyt.acornlib.item.CustomHitParticleItem;
import net.acoyt.acornlib.item.KillEffectItem;
import net.chemthunder.amaranthine.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class ApolloItem extends Item implements CustomHitParticleItem, KillEffectItem {
    public ApolloItem(Settings settings) {
        super(settings);
    }



    public static final SweepParticleEffect[] EFFECTS = new SweepParticleEffect[]{new SweepParticleEffect(0xFFFFAC, 0xFEFE83)};

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
        textConsumer.accept(Text.translatable("item.amaranthine.apollo.desc").styled(style -> style.withColor(0x35253B)));
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState state = context.getWorld().getBlockState(context.getBlockPos());
        PlayerEntity user = context.getPlayer();
        if (user != null && user.isSneaking() && state.isOf(Blocks.ENCHANTING_TABLE)) {
            ItemStack stack = user.getMainHandStack();
            if (stack.isOf(ModItems.APOLLO)) {
                stack.decrement(1);
                user.giveItemStack(ModItems.CHRYSAOR.getDefaultStack());
            }
            user.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, 0.8F, 1.0F);
            return ActionResult.SUCCESS;
        }

        return super.useOnBlock(context);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack mainStack = user.getMainHandStack();
        ItemStack offStack = user.getOffHandStack();

        assert !user.getItemCooldownManager().isCoolingDown(mainStack);
        if (mainStack.isOf(ModItems.APOLLO)) {
            if (user.isSneaking()) {
                FlightoftheSun(user, offStack);
            } else {
                castFireball(user, mainStack);
            }

            assert !user.getItemCooldownManager().isCoolingDown(offStack);
            if (offStack.isOf(ModItems.APOLLO)) {
                if (user.isSneaking()) {
                    user.addCritParticles(user);
                } else {

                }
            }

        }

        return ActionResult.FAIL;

    }



    public static void castFireball(PlayerEntity player, ItemStack stack) {
        World world = player.getWorld();
        FireballEntity fireball = new FireballEntity(world, player, player.getRotationVec(0).multiply(0F), 5);
        Vec3d pos = player.getPos();
        fireball.updatePosition(pos.x, pos.y + 1.5F, pos.z);
        world.spawnEntity(fireball);

        player.getItemCooldownManager().set(stack, 20);
    }

    public static void FlightoftheSun(PlayerEntity player, ItemStack stack) {
        Vec3d pos = player.getPos();

        Vec3d velocity = player.getVelocity();

        player.setVelocity(velocity.x, 5, velocity.z);
        player.velocityModified = true;
        player.getItemCooldownManager().set(stack, 45);
    }

    @Override
    public void killEntity(World world, ItemStack itemStack, LivingEntity user, LivingEntity victim) {


        victim.setHealth(20f);
        victim.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 999999, 250));
        victim.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 999999, 255));


    }
}
