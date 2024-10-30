package io.github.jadedchara.pentamorph;

import io.github.jadedchara.pentamorph.common.BlockRegistry;
import io.github.jadedchara.pentamorph.common.CyberformCommand;
import io.github.jadedchara.pentamorph.common.EntityRegistry;
import io.github.jadedchara.pentamorph.common.SubcomponentRegistry;

import io.github.jadedchara.pentamorph.common.particle.ResonatorShotType;
import io.github.jadedchara.pentamorph.common.util.misc.DataTracking;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
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
	public static final ResonatorShotType RESONATOR_SHOT = (ResonatorShotType) FabricParticleTypes.simple(false);
	public static Identifier id(String path){
		return new Identifier(MOD_ID,path);
	}
	@Override
	public void onInitialize(ModContainer mod) {
		GeckoLib.initialize();
		BlockRegistry.init();
		EntityRegistry.init();
		//DataTracking.init();
		CommandRegistrationCallback.EVENT.register(((dispatcher, buildContext, environment) -> {
			CyberformCommand.register(dispatcher);
		}));
		//TabRegistry.init();
		//ItemRegistry.init();
		//RoleRegistry.init();

		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID,"resonator_shot"),RESONATOR_SHOT);
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
	}
}
