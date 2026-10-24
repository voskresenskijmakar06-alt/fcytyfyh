package com.yomayo;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
	public static final FoodComponent MAYO_FOOD = new FoodComponent.Builder()
			.hunger(6)
			.saturationModifier(0.8f)
			.alwaysEdible()
			.statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20 * 30, 1), 1.0f)
			.statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20 * 30, 1), 1.0f)
			.statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 20 * 30), 1.0f)
			.build();

	public static final Item MAYO_JAR = new MayoJarItem(
			new FabricItemSettings().food(MAYO_FOOD).maxCount(16));

	public static final Block MAYO_BLOCK = new MayoBlock(
			FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)
					.mapColor(MapColor.YELLOW)
					.sounds(BlockSoundGroup.SLIME)
					.strength(0.5f));

	public static final Item MAYO_BLOCK_ITEM = new BlockItem(MAYO_BLOCK, new FabricItemSettings());

	private static Identifier id(String path) {
		return new Identifier(YomayoMod.MOD_ID, path);
	}

	public static void register() {
		Registry.register(Registries.ITEM, id("mayo_jar"), MAYO_JAR);
		Registry.register(Registries.BLOCK, id("mayo_block"), MAYO_BLOCK);
		Registry.register(Registries.ITEM, id("mayo_block"), MAYO_BLOCK_ITEM);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
				.register(entries -> entries.add(MAYO_JAR));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
				.register(entries -> entries.add(MAYO_BLOCK_ITEM));
	}
}
