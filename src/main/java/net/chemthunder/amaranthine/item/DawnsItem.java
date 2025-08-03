package net.chemthunder.amaranthine.item;

import net.chemthunder.amaranthine.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import java.util.function.Consumer;

public class DawnsItem extends Item {
    public DawnsItem(Settings settings) {
        super(settings);
    }


    @SuppressWarnings("deprecation")
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.amaranthine.dawn.desc").styled(style -> style.withColor(0x35253B)));
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState state = context.getWorld().getBlockState(context.getBlockPos());
        PlayerEntity user = context.getPlayer();
        if (user != null && user.isSneaking() && state.isOf(Blocks.ENCHANTING_TABLE)) {
            ItemStack stack = user.getMainHandStack();

            if (stack.isOf(ModItems.AMARANTHINE_SHARD)) {
                stack.decrement(1);
                user.giveItemStack(ModItems.DAWNS_LIGHT.getDefaultStack());
                user.playSound(SoundEvents.BLOCK_CONDUIT_ACTIVATE, 0.8F, 1.0F);
            }

            return ActionResult.SUCCESS;
        }

        return super.useOnBlock(context);
    }
}
