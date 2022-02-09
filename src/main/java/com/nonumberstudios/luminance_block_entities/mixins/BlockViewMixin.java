package com.nonumberstudios.luminance_block_entities.mixins;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import com.nonumberstudios.luminance_block_entities.LuminanceBlockEntitiesMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin( BlockView.class )
public interface BlockViewMixin {

	@Inject( at = @At( value = "RETURN"), method = "getLuminance", cancellable = true )
	default void getLuminance( BlockPos pos, CallbackInfoReturnable<Integer> cir ) {
		BlockView blockView = (BlockView)(Object)(this);
		cir.setReturnValue( LuminanceBlockEntitiesMod.getLuminance( blockView, pos ) );
	}
}
