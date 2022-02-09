package com.nonumberstudios.luminance_block_entities.mixins;

import com.nonumberstudios.luminance_block_entities.LuminanceBlockEntitiesMod;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;

@Mixin( BlockModelRenderer.class )
public class BlockModelRendererMixin {

	@Inject( at = @At( value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;translate(DDD)V", shift = At.Shift.AFTER), locals = LocalCapture.NO_CAPTURE, method = "render(Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/client/render/model/BakedModel;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;ZLjava/util/Random;JI)Z")
	public void render( BlockRenderView world, BakedModel model, BlockState state, BlockPos pos, MatrixStack matrices, VertexConsumer vertexConsumer, boolean cull, Random random, long seed, int overlay, CallbackInfoReturnable<Boolean> cir ) {
		BlockModelRenderer bmr = (BlockModelRenderer) (Object) this;

		boolean bl = MinecraftClient.isAmbientOcclusionEnabled() && state.getLuminance() == 0 && model.useAmbientOcclusion();
		if ( !bl && LuminanceBlockEntitiesMod.getLuminance( world, pos ) == 0 ) {
			try {
				boolean result = bmr.renderSmooth(world, model, state, pos, matrices, vertexConsumer, cull, random, seed, overlay);
				cir.setReturnValue( result );
			}
			catch(Throwable throwable){
				CrashReport crashReport = CrashReport.create(throwable, "Tesselating block model");
				CrashReportSection crashReportSection = crashReport.addElement("Block model being tesselated");
				CrashReportSection.addBlockInfo(crashReportSection, world, pos, state);
				crashReportSection.add("Using AO", bl);
				throw new CrashException(crashReport);
			}
		}
		else cir.cancel();
	}
}

