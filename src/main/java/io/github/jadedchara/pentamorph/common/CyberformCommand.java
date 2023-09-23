package io.github.jadedchara.pentamorph.common;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;

import io.github.jadedchara.pentamorph.common.util.component.RPGComponentInitializer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.player.PlayerEntity;
import static net.minecraft.server.command.CommandManager.literal;
//import static net.minecraft.server.command.CommandManager.
import net.minecraft.server.command.ServerCommandSource;


public class CyberformCommand {
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
		dispatcher.register(literal("cyberform")
				/*.then(argument("character", StringArgumentType.string()))
				.executes(context -> {
					setCharacter(context.getSource().getPlayerOrThrow(), context.getInput());
					return 1;
				})*/
				.then(literal("human")
					.executes(context->{
						setCharacter(context.getSource().getPlayerOrThrow(),"human",EntityDimensions.changing(0.6f,
								1.8f),1.62f,1.5f,0.6f,0.6f);
						System.out.println("[Set to human]");
						return Command.SINGLE_SUCCESS;
					})
				)
				.then(literal("larva")
					.executes(context->{


						setCharacter(context.getSource().getPlayerOrThrow(),"quintlarva",
								EntityDimensions.changing(0.6f,0.6f),0.5f,0.5f,0.6f,0.6f);
						System.out.println("[Set to larva]");
						return Command.SINGLE_SUCCESS;
					})
				)
		);
	}
	private static void setCharacter(PlayerEntity player, String providedCharacter, EntityDimensions dimensions,
									 float eyeHeight, float crouchHeight, float swimHeight, float swimWidth){
		//RPGManage.setProvidedCharacter(player,providedCharacter);
		//player.getComponent()
		RPGComponentInitializer.RPG_COMPONENT.maybeGet(player).get().setProvidedCharacter(player,providedCharacter,
				dimensions, crouchHeight, swimHeight, swimWidth, eyeHeight);
	}
}
