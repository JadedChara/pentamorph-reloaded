package io.github.jadedchara.pentamorph.common;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import io.github.jadedchara.pentamorph.Pentamorph;
import io.github.jadedchara.pentamorph.common.util.component.RPGComponentInitializer;
import static net.minecraft.server.command.CommandManager.literal;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;


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
						setCharacter(context.getSource().getPlayerOrThrow(),"human",0.6F,1.8F,1.62f);
						System.out.println("[Set to human]");
						return Command.SINGLE_SUCCESS;
					})
				)
				.then(literal("larva")
					.executes(context->{


						setCharacter(context.getSource().getPlayerOrThrow(),"quintlarva",
								0.6F,0.6F,0.5f);
						System.out.println("[Set to larva]");
						return Command.SINGLE_SUCCESS;
					})
				)
		);
	}
	private static void setCharacter(ServerPlayerEntity player, String providedCharacter, float sw, float sh,
									 float eyeHeight){
		RPGComponentInitializer.RPG_COMPONENT.maybeGet(player).get().setProvidedCharacter(player,providedCharacter,
				sh, sw,eyeHeight);
	}

}
