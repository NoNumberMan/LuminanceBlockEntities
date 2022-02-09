package com.nonumberstudios.luminance_block_entities;

import net.minecraft.block.BlockState;

public enum LuminanceBehavior {
	/**
	 * Ignore BlockState luminance when calculating world lighting.
	 */
	IGNORE( ( ( state, luminanceBlockEntity ) -> luminanceBlockEntity.getLuminance() ) ),
	/**
	 * Only use BlockState luminance when LuminanceBlockEntity luminance is zero.
	 */
	FALLBACK( ( ( state, luminanceBlockEntity ) -> {
		int luminance = luminanceBlockEntity.getLuminance();
		return luminance > 0 ? luminance : state.getLuminance();
	} ) ),
	/**
	 * Only use LuminanceBlockEntity luminance when BlockState luminance is zero.
	 */
	PRIORITY( ( ( state, luminanceBlockEntity ) -> {
		int luminance = state.getLuminance();
		return luminance > 0 ? luminance : luminanceBlockEntity.getLuminance();
	} ) );


	private final BlockStateLuminanceFunction blockStateLuminanceFunction;

	LuminanceBehavior( BlockStateLuminanceFunction blockStateLuminanceFunction ) {
		this.blockStateLuminanceFunction = blockStateLuminanceFunction;
	}

	public int getLuminance( BlockState state, LuminanceBlockEntity luminanceBlockEntity ) {
		return blockStateLuminanceFunction.getLuminance( state, luminanceBlockEntity );
	}


	private interface BlockStateLuminanceFunction {
		int getLuminance( BlockState state, LuminanceBlockEntity luminanceBlockEntity );
	}
}
