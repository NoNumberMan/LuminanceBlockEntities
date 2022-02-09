package com.nonumberstudios.luminance_block_entities.mixins;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import com.nonumberstudios.luminance_block_entities.LuminanceBlockEntitiesMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin( WorldRenderer.class )
public class WorldRendererMixin {

	@Inject( at = @At( value = "TAIL" ), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true, method = "getLightmapCoordinates(Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;)I")
	private static void getLightmapCoordinates( BlockRenderView world, BlockState state, BlockPos pos, CallbackInfoReturnable<Integer> cir, int i, int j ) {
		int k = LuminanceBlockEntitiesMod.getLuminance( world, pos );
		if ( j < k ) j = k;
		cir.setReturnValue( ( i << 20 | j << 4 ) );
	}
}