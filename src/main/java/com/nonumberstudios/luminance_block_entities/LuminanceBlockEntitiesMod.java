package com.nonumberstudios.luminance_block_entities;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class LuminanceBlockEntitiesMod implements ModInitializer {
	public static final String MODID = "luminance_block_entities";
	//public static final Logger LOGGER = LogManager.getLogger( MODID );

	public static Block GLOW_BLOCK;
	public static BlockEntityType<GlowBlockEntity> GLOW_BLOCK_ENTITY;

	@Override
	public void onInitialize() {
		//initializeExample();
	}

	/**
	 * Call this method in the initialization stage to load the example LuminanceBlockEntity (GlowBlock).
	 */
	public static void initializeExample() {
		GLOW_BLOCK = new GlowBlock( FabricBlockSettings.of( Material.METAL).strength(4.0f));
		GLOW_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier( MODID, "glow_block_entity" ), FabricBlockEntityTypeBuilder.create( GlowBlockEntity::new, GLOW_BLOCK ).build());

		Registry.register(Registry.BLOCK, new Identifier(MODID, "glow_block"), GLOW_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MODID, "glow_block"), new BlockItem(GLOW_BLOCK, new FabricItemSettings().group( ItemGroup.MISC)));
	}

	/**
	 *
	 * @param blockView
	 * @param pos
	 * @return The luminance value at pos for world lighting
	 */
	public static final int getLuminance( BlockView blockView, BlockPos pos ) {
		BlockEntity be = blockView.getBlockEntity( pos );
		if ( be != null && be instanceof LuminanceBlockEntity ) {
			return ( ( LuminanceBlockEntity ) be ).getWorldLuminance();
		}
		else return blockView.getBlockState(pos).getLuminance();
	}
}
