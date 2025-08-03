package net.chemthunder.amaranthine.init;

import net.chemthunder.amaranthine.Amaranthine;
import net.chemthunder.amaranthine.item.*;
import net.chemthunder.amaranthine.item.cookie.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Rarity;

import java.util.function.Function;

import static net.acoyt.acornlib.util.ItemUtils.modifyItemNameColor;

public interface ModItems {
    Item AMARANTHINE_GREATSWORD = create("amaranthine_greatsword", AmaranthineGreatswordItem::new, new Item.Settings()
            .sword(ToolMaterial.NETHERITE, 3.5F, -2.7F)
            .rarity(Rarity.UNCOMMON)
            .fireproof()
    );

    Item AMARANTHINE_SHARD = create("amaranthine_shard", Item::new, new Item.Settings()
            .fireproof()
    );

    Item CHEM_COOKIE = create("chem_cookie", ChemCookie::new, new Item.Settings()
            .food(new FoodComponent(5, 4, true))
    );

    Item ARC_COOKIE = create("arc_cookie", ArcCookie::new, new Item.Settings()
            .food(new FoodComponent(5, 4, true))
    );

    Item ARC_COOKIE_BREEZE = create("arc_cookie_breeze", BreezeCookie::new, new Item.Settings()
            .food(new FoodComponent(5, 4, true)
            )
            .maxCount(16)
    );

    Item INKWELL_COOKIE = create("inkwell_cookie", InkwellCookie::new, new Item.Settings()
            .food(new FoodComponent(5, 4, true)
            )
            .maxCount(16)
            );

    Item INTEL_COOKIE = create("intel_cookie", IntelCookie::new, new Item.Settings()
            .food(new FoodComponent(5, 4, true))
            .maxCount(16));

    Item CHRYSAOR = create("chrysaor", ChrysaorItem::new, new Item.Settings()
            .sword(ToolMaterial.NETHERITE, 4.0F, -2.7F)
            .rarity(Rarity.EPIC)
            .fireproof()
    );

    Item AMARANTHINE_CLEAVER = create("amaranthine_cleaver", CleaverItem::new, new Item.Settings()
            .axe(ToolMaterial.NETHERITE, 3.0f, -2.5f)
            .rarity(Rarity.COMMON)
            .fireproof()
    );

    Item AMARANTHINE_DUST = create("amaranthine_dust", Item::new, new Item.Settings()
            .food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .build()));

    Item BLIND_OBEDIENCE = create("blind_obedience", BlindObedienceItem::new, new Item.Settings()
            .sword(ToolMaterial.NETHERITE, 3.5f, -2.7f)
    );

    Item CAPTAINS_CUTLASS = create("captains_cutlass", CutlassItem::new, new Item.Settings()
            .sword(ToolMaterial.NETHERITE, 2.5f, -2.3f)
            .maxCount(1)
            .maxDamage(99999)
            .fireproof()
    );


    Item DAWNS_LIGHT = create("dawns_light", DawnsItem::new, new Item.Settings()
            .maxCount(1)
            .maxDamage(1)
    );


    static Item create(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return Items.register(RegistryKey.of(RegistryKeys.ITEM, Amaranthine.id(name)), factory, settings);
    }

    static void init() {
        modifyItemNameColor(AMARANTHINE_CLEAVER, 0x90403e);
        modifyItemNameColor(AMARANTHINE_GREATSWORD, 0xAE8448);
        modifyItemNameColor(AMARANTHINE_SHARD, 0xE29242);
        modifyItemNameColor(AMARANTHINE_DUST, 0x985DCE);
        modifyItemNameColor(CHRYSAOR, 0xA008D8);
        modifyItemNameColor(BLIND_OBEDIENCE, 0x0a0a0a);
        modifyItemNameColor(CAPTAINS_CUTLASS, 0x93E9BE);
        modifyItemNameColor(DAWNS_LIGHT, 0xFEF268);


        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addCombatEntries);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addIngredientEntries);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addFoodEntries);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addToolsEntries);

    }

    private static void addCombatEntries(FabricItemGroupEntries entries) {
        entries.add(AMARANTHINE_GREATSWORD);
        entries.add(CHRYSAOR);
        entries.add(AMARANTHINE_CLEAVER);
        entries.add(CAPTAINS_CUTLASS);

    }



    private static void addToolsEntries(FabricItemGroupEntries entries) {
    }

    private static void addIngredientEntries(FabricItemGroupEntries entries) {
        entries.add(AMARANTHINE_SHARD);
        entries.add(AMARANTHINE_DUST);
    }

    private static void addFoodEntries(FabricItemGroupEntries entries) {
        entries.add(CHEM_COOKIE);
        entries.add(ARC_COOKIE);
        entries.add(ARC_COOKIE_BREEZE);
        entries.add(INKWELL_COOKIE);
        entries.add(INTEL_COOKIE);
    }
}
