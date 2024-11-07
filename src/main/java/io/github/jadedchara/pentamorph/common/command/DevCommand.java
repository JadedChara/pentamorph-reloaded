package io.github.jadedchara.pentamorph.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import foundry.veil.api.client.render.VeilRenderSystem;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.literal;

public class DevCommand {
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
		dispatcher.register(literal("util")
			.then(literal("stopPSM")
				.executes(context ->{
					try {
						VeilRenderSystem.renderer().getParticleManager().clear();
						System.out.println("Current Emitter Count: " + VeilRenderSystem.renderer().getParticleManager().getEmitterCount());
					}catch(Exception e){
						System.out.println(e);
					}
					return Command.SINGLE_SUCCESS;
				})
			).then(literal("emitterCount")
				.executes(context->{
					System.out.println("Current Emitter Count: " + VeilRenderSystem.renderer().getParticleManager().getEmitterCount());
					return Command.SINGLE_SUCCESS;
				})
			)
		);
	}
}
