package io.github.jadedchara.pentamorph.common.util.misc;

import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;

import java.util.Optional;

public final class DataTracking {
	public static final TrackedDataHandler<Optional<Float>> RPGHANDLER = new TrackedDataHandler<Optional<Float>>() {
		@Override
		public PacketCodec<? super RegistryByteBuf, Optional<Float>> codec() {
			return null;
		}

		@Override
		public Optional<Float> copy(Optional<Float> object) {
			return Optional.empty();
		}

		//@Override
		public void write(PacketByteBuf buf, Optional<Float> value) {
			buf.writeBoolean(value.isPresent());
			value.ifPresent(flot -> {
				buf.writeFloat(flot);
			});
		}
		//@Override
		public Optional<Float> read(PacketByteBuf buf) {
			return(buf.readBoolean() ? Optional.of(Float.valueOf(
					buf.readFloat()
			)):Optional.empty());

		}


	};
	public static void init() {
		TrackedDataHandlerRegistry.register(RPGHANDLER);
	}
}
