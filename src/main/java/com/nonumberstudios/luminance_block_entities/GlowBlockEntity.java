package com.nonumberstudios.luminance_block_entities;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class GlowBlockEntity extends LuminanceBlockEntity {
	private final Random rand = new Random();
	private int luminance = 0;

	public GlowBlockEntity( BlockPos pos, BlockState state ) {
		super( LuminanceBlockEntitiesMod.GLOW_BLOCK_ENTITY, pos, state, LuminanceBehavior.IGNORE );
	}

	public int getLuminance() {
		return luminance;
	}

	public static void tick( World world, BlockPos pos, BlockState state, GlowBlockEntity be ) {
		be.luminance = be.rand.nextInt(0, 16);
		be.updateLuminance();
	}

	//no need for serialization, luminance is random anyway (:
}
