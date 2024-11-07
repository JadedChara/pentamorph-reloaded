package io.github.jadedchara.pentamorph;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.quasar.particle.ParticleEmitter;
import foundry.veil.api.quasar.particle.ParticleSystemManager;
import io.github.jadedchara.pentamorph.common.BlockRegistry;
import io.github.jadedchara.pentamorph.common.command.CyberformCommand;
import io.github.jadedchara.pentamorph.common.EntityRegistry;

import io.github.jadedchara.pentamorph.common.blockentity.NestCoreBlockEntity;
import io.github.jadedchara.pentamorph.common.command.DevCommand;
import io.github.jadedchara.pentamorph.common.util.misc.PSMAccess;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientBlockEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerBlockEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.command.api.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

import java.util.ConcurrentModificationException;
import java.util.Objects;


public class Pentamorph implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final Logger LOGGER = LoggerFactory.getLogger("Pentamorph");
	public static final String MOD_ID = "pentamorph";
	//public static final ResonatorShotType RESONATOR_SHOT = (ResonatorShotType) FabricParticleTypes.simple(false);
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
			//DevCommand.register(dispatcher);
		}));
		PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity)->{
			if(blockEntity instanceof NestCoreBlockEntity){
				BlockPos ncbe = blockEntity.getPos();
				ParticleSystemManager psm = VeilRenderSystem.renderer().getParticleManager();
				//((NestCoreBlockEntity) blockEntity).markDeleted();
				try{
					for(ParticleEmitter pe : ((PSMAccess)psm).getParticleEmitters()) {
						if (Objects.equals(pe.getPosition(), new Vector3d(
							(double) ncbe.getX() + 0.5D,
							(double) ncbe.getY() + 0.4D,
							(double) ncbe.getZ() + 0.5D
						))) {

							((PSMAccess) psm).erase(pe);
						}
					}
				}catch(ConcurrentModificationException err){
					System.out.println("SYNCING ERROR");
				}


				System.out.println("Successfully removed Spore Emitter");
				System.out.println("Remaining emitters:"+VeilRenderSystem.renderer().getParticleManager().getEmitterCount());


			}
			return ActionResult.SUCCESS.isAccepted();
		});

		ServerBlockEntityEvents.BLOCK_ENTITY_LOAD.register((blockEntity, serverWorld)->{
			if(blockEntity instanceof NestCoreBlockEntity) {
				try {
					ParticleEmitter pe =
						VeilRenderSystem.renderer().getParticleManager().createEmitter(NestCoreBlockEntity.SPORE_PARTICLE);

				pe.setPosition(new Vec3d(
					(double) blockEntity.getPos().getX() + 0.5D,
					(double) blockEntity.getPos().getY() + 0.4D,
					(double) blockEntity.getPos().getZ() + 0.5D
				));
				VeilRenderSystem.renderer().getParticleManager().addParticleSystem(pe);
				}catch(NullPointerException e){
					System.out.println("TICKING ERROR");
				}
			}
		});

		//TabRegistry.init();
		//ItemRegistry.init();
		//RoleRegistry.init();

		//Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID,"resonator_shot"),RESONATOR_SHOT);
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
	}
}
