package com.nonumberstudios.luminance_block_entities;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GlowBlock extends BlockWithEntity {
	public GlowBlock( Settings settings ) {
		super( settings );
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity( BlockPos pos, BlockState state ) {
		return new GlowBlockEntity( pos, state );
	}

	@Override
	public BlockRenderType getRenderType( BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker( World world, BlockState state, BlockEntityType<T> type) {
		return checkType(type, LuminanceBlockEntitiesMod.GLOW_BLOCK_ENTITY, ( world1, pos, state1, be) -> GlowBlockEntity.tick(world1, pos, state1, be));
	}
}
