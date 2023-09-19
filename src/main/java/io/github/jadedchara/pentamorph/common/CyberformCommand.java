package io.github.jadedchara.pentamorph.common;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.entity.player.PlayerEntity;
import static net.minecraft.server.command.CommandManager.literal;
//import static net.minecraft.server.command.CommandManager.
import static net.minecraft.server.command.CommandManager.argument;
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
						setCharacter(context.getSource().getPlayerOrThrow(),"human");
						return Command.SINGLE_SUCCESS;
					})
				)
				.then(literal("larva")
					.executes(context->{
						setCharacter(context.getSource().getPlayerOrThrow(),"quintlarva");
						return Command.SINGLE_SUCCESS;
					})
				)
		);
	}
	private static void setCharacter(PlayerEntity player, String providedCharacter){
		SubcomponentRegistry.setProvidedCharacter(player,providedCharacter);
	}
}
