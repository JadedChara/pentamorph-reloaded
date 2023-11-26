package io.github.jadedchara.pentamorph.common.util.misc;

import io.github.jadedchara.pentamorph.Pentamorph;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.entity.networking.api.tracked_data.QuiltTrackedDataHandlerRegistry;

import java.util.Optional;

public final class DataTracking {
	public static final TrackedDataHandler<Optional<Float>> RPGHANDLER = new TrackedDataHandler<Optional<Float>>() {
		@Override
		public void write(PacketByteBuf buf, Optional<Float> value) {
			buf.writeBoolean(value.isPresent());
			value.ifPresent(flot -> {
				buf.writeFloat(flot);
			});
		}
		@Override
		public Optional<Float> read(PacketByteBuf buf) {
			return(buf.readBoolean() ? Optional.of(Float.valueOf(
					buf.readFloat()
			)):Optional.empty());

		}
		@Override
		public Optional<Float> copy(Optional<Float> value) {
			return value;
		}


	};
	public static void init() {
		QuiltTrackedDataHandlerRegistry.register(new Identifier(Pentamorph.MOD_ID),RPGHANDLER);
	}
}
