package io.github.jadedchara.pentamorph;


//import foundry.veil.api.quasar.particle.ParticleSystemManager;
import io.github.jadedchara.pentamorph.common.BlockRegistry;
import io.github.jadedchara.pentamorph.common.blockentity.NestCoreBlockEntity;
import io.github.jadedchara.pentamorph.common.command.CyberformCommand;
import io.github.jadedchara.pentamorph.common.EntityRegistry;
//import io.github.jadedchara.pentamorph.common.util.misc.PSMAccess;
import org.quiltmc.qsl.command.api.CommandRegistrationCallback;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Pentamorph implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	
	public static final Logger LOGGER = LoggerFactory.getLogger("Pentamorph");
	public static final String MOD_ID = "pentamorph";


	public static final ParticleType SPORE_PARTICLE = FabricParticleTypes.simple(true);
	//public static final ResonatorShotType RESONATOR_SHOT = (ResonatorShotType) FabricParticleTypes.simple(false);
	public static Identifier id(String path){
		return Identifier.of(MOD_ID,path);
	}
	@Override
	public void onInitialize(ModContainer container) {
		//GeckoLib.initialize();

		BlockRegistry.init();
		EntityRegistry.init();
		//DataTracking.init();
		Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID,"smoke"),SPORE_PARTICLE);
		CommandRegistrationCallback.EVENT.register(((dispatcher, buildContext, environment) -> {
			CyberformCommand.register(dispatcher);
			//DevCommand.register(dispatcher);
		}));

		PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity)->{
			if(blockEntity instanceof NestCoreBlockEntity){
				//BlockPos ncbe = blockEntity.getPos();
				//((NestCoreBlockEntity) blockEntity).getPsm().clear();
				/*try{
					((NestCoreBlockEntity) blockEntity).getPsm().clear();
				}catch(Exception ignored){
					System.out.println(ignored);
				}*/
				try{
					((NestCoreBlockEntity) blockEntity).terminateEmitter();
					System.out.println("Successfully removed Spore Emitter");
				}catch(Exception ignored){
					System.out.println(ignored);
				}

				//System.out.println("Remaining emitters:"+VeilRenderSystem.renderer().getParticleManager()
				// .getEmitterCount());


			}
			return ActionResult.SUCCESS.isAccepted();
		});

		/*ClientBlockEntityEvents.BLOCK_ENTITY_UNLOAD.register((blockEntity, world)->{

			if(blockEntity instanceof NestCoreBlockEntity) {
				try{
					((NestCoreBlockEntity) blockEntity).terminateEmitter();
				}catch(Exception ignored){
					System.out.println(ignored);
				}
			}
		});*/

		//TabRegistry.init();
		//ItemRegistry.init();
		//RoleRegistry.init();

		//Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID,"resonator_shot"),RESONATOR_SHOT);
		//LOGGER.info("Hello Quilt world from {}!");
	}
}
