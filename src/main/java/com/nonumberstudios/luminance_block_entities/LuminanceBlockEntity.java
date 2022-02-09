package com.nonumberstudios.luminance_block_entities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public abstract class LuminanceBlockEntity extends BlockEntity {
	private final LuminanceBehavior luminanceBehavior;

	public LuminanceBlockEntity( BlockEntityType<?> type, BlockPos pos, BlockState state, LuminanceBehavior luminanceBehavior ) {
		super( type, pos, state );
		this.luminanceBehavior = luminanceBehavior;
	}

	/**
	 * @return luminance, a lighting value between 0 and 15 (inclusive).
	 */
	public abstract int getLuminance();

	/**
	 *
	 * @return world luminance, i.e. the luminance of this block according to its LuminanceBehavior
	 */
	public final int getWorldLuminance() {
		BlockState state = world.getBlockState( pos );
		return luminanceBehavior.getLuminance( state, this );
	}

	/**
	 * Updates the world's lighting data with LuminanceBlockEntity's luminance. Only call this if the luminance changed.
	 */
	public final void updateLuminance() {
		world.getProfiler().push("queueCheckLight");
		world.getChunkManager().getLightingProvider().checkBlock(pos);
		world.getProfiler().pop();
	}
}
