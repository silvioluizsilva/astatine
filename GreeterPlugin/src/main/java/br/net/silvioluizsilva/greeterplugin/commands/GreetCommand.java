package br.net.silvioluizsilva.greeterplugin.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import br.net.silvioluizsilva.greeterplugin.GreeterPlugin;

import javax.annotation.Nonnull;

// Declaração da classe, só pode ser executada por um jogador 
public class GreetCommand extends AbstractPlayerCommand {

	// Guarda uma referência ao plugin principal
    private final GreeterPlugin plugin;
    // Define um argumento obrigatório do comando
    // Argumento: PlayerRef
    private final RequiredArg<PlayerRef> targetArg;

    // Esse construtor é chamado quando o comando é registrado no plugin
    public GreetCommand(@Nonnull GreeterPlugin plugin) {
    	// Definição do comando
        super("greeter", "Cumprimenta outro jogador. [08]");
        // Salva a referência do plugin dentro da classe
        this.plugin = plugin;

        this.targetArg = withRequiredArg("target", "O jogador para cumprimentar. [09]", ArgTypes.PLAYER_REF);
    }

    @Override
    // Esse método é chamado quando o jogador usa o comando
    protected void execute(
            @Nonnull CommandContext context,
            @Nonnull Store<EntityStore> store,
            // Referência da entidade que executou o comando
            @Nonnull Ref<EntityStore> ref,
            // Representa o jogador que executou o comando
            @Nonnull PlayerRef playerRef,
            // O mundo onde o comando foi executado
            @Nonnull World world) {

        // Aqui o código pega o argumento digitado no comando
    	PlayerRef target = context.get(targetArg);

        // Verifica se o jogador existe
    	if (target == null) {
            context.sendMessage(Message.raw("Jogador não encontrado! [10]"));
            return;
        }

        // Aqui o código verifica se o jogador tentou cumprimentar a si mesmo
    	if (target.getUuid().equals(playerRef.getUuid())) {
            context.sendMessage(Message.raw("Você não pode cumprimentar a si mesmo! [11]"));
            return;
        }

        // Pega a mensagem da configuração
    	String message = String.format(
            plugin.getConfig().getGreetMessage(),
            playerRef.getUsername(),
            target.getUsername()
        );

        // Envia a mensagem para o jogador que executou o comando
    	context.sendMessage(Message.raw(message));
    }
}