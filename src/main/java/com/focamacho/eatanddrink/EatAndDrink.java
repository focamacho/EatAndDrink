package com.focamacho.eatanddrink;

import com.focamacho.eatanddrink.item.ItemDrinkMe;
import com.focamacho.eatanddrink.item.ItemEatMe;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EatAndDrink implements ModInitializer {

	public static Item drinkMe;
	public static Item eatMe;

	@Override
	public void onInitialize() {
		drinkMe = new ItemDrinkMe(new Item.Settings().food(new FoodComponent.Builder().alwaysEdible().build()).group(ItemGroup.BREWING));
		eatMe = new ItemEatMe(new Item.Settings().food(new FoodComponent.Builder().alwaysEdible().build()).group(ItemGroup.FOOD));

		Registry.register(Registry.ITEM, new Identifier("eatanddrink", "drinkme"), drinkMe);
		Registry.register(Registry.ITEM, new Identifier("eatanddrink", "eatme"), eatMe);

		LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
			if(id.equals(new Identifier("minecraft", "gameplay/piglin_bartering"))) {
				supplier.withPool(FabricLootPoolBuilder.builder().rolls(UniformLootTableRange.between(-5, 1)).with(ItemEntry.builder(drinkMe).weight(5)).with(ItemEntry.builder(eatMe).weight(5)).build());
			}
		});
	}
}
