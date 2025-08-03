package net.chemthunder.amaranthine.item.cookie;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.function.Consumer;

public class ArcCookie extends Item {
    public ArcCookie(Settings settings) {
        super(settings);
    }

    public Text getName(ItemStack stack) {
        return Text.translatable("item.amaranthine.infused_cookie");
    }

    @SuppressWarnings("deprecation")
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.amaranthine.cookie.arc").styled(style -> style.withColor(0xFD9947)));
    }
}
