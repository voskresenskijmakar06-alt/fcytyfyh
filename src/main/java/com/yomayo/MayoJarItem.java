package com.yomayo;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class MayoJarItem extends Item {
	public MayoJarItem(Settings settings) {
		super(settings);
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		ItemStack result = super.finishUsing(stack, world, user);

		if (user instanceof PlayerEntity player) {
			if (!world.isClient) {
				player.sendMessage(Text.translatable("message.yomayo.eat"), true);
			}
			if (player.getAbilities().creativeMode) {
				return result;
			}
			// Пустая банка (стеклянная бутылка) возвращается игроку
			ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
			if (result.isEmpty()) {
				return bottle;
			}
			player.getInventory().offerOrDrop(bottle);
		}
		return result;
	}
}
