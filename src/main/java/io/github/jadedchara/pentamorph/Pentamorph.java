package io.github.jadedchara.pentamorph;

import io.github.jadedchara.pentamorph.common.BlockRegistry;
import io.github.jadedchara.pentamorph.common.CyberformCommand;
import io.github.jadedchara.pentamorph.common.EntityRegistry;
import io.github.jadedchara.pentamorph.common.SubcomponentRegistry;

import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.command.api.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;



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
		SubcomponentRegistry.init();
		CommandRegistrationCallback.EVENT.register(((dispatcher, buildContext, environment) -> {
			CyberformCommand.register(dispatcher);
		}));
		//TabRegistry.init();
		//ItemRegistry.init();
		//RoleRegistry.init();
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
	}
}
