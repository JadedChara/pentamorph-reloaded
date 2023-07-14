package io.github.jadedchara.pentamorph;

import io.github.jadedchara.pentamorph.common.BlockRegistry;
import io.github.jadedchara.pentamorph.common.EntityRegistry;

import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

public class Pentamorph implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Pentamorph");
	public static final String MOD_ID = "pentamorph";
	public static Identifier id(String path){
		return new Identifier(MOD_ID,path);
	}
	@Override
	public void onInitialize(ModContainer mod) {
		GeckoLib.initialize();
		BlockRegistry.init();
		EntityRegistry.init();
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
	}
}
