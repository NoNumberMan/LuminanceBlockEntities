package com.nonumberstudios.luminance_block_entities.mixins;

import com.nonumberstudios.luminance_block_entities.LuminanceBlockEntitiesMod;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Mixin( WorldChunk.class )
public class WorldChunkMixin {

	@Inject( at = @At( value = "RETURN"), method = "getLightSourcesStream" )
	public void getLightSourcesStream( CallbackInfoReturnable<Stream<BlockPos>> cir ) {
		WorldChunk chunk = (WorldChunk)(Object)(this);
		ChunkPos pos = chunk.getPos();

		cir.setReturnValue( StreamSupport.stream(BlockPos.iterate(pos.getStartX(), chunk.getBottomY(), pos.getStartZ(), pos.getEndX(), chunk.getTopY() - 1, pos.getEndZ()).spliterator(), false)
				.filter( blockPos -> LuminanceBlockEntitiesMod.getLuminance( chunk.getWorld(), blockPos ) > 0 ) );
	}
}

