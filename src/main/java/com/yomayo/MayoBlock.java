package com.yomayo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

/** Майонезный блок: батут без урона от падения, как слизь. */
public class MayoBlock extends Block {
	public MayoBlock(Settings settings) {
		super(settings);
	}

	@Override
	public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		if (entity.bypassesLandingEffects()) {
			super.onLandedUpon(world, state, pos, entity, fallDistance);
		} else {
			entity.handleFallDamage(fallDistance, 0.0f, world.getDamageSources().fall());
		}
	}

	@Override
	public void onEntityLand(BlockView world, Entity entity) {
		if (entity.bypassesLandingEffects()) {
			super.onEntityLand(world, entity);
		} else {
			bounce(entity);
		}
	}

	private void bounce(Entity entity) {
		Vec3d velocity = entity.getVelocity();
		if (velocity.y < 0.0) {
			double factor = entity instanceof LivingEntity ? 1.0 : 0.8;
			entity.setVelocity(velocity.x, -velocity.y * factor, velocity.z);
		}
	}
}
