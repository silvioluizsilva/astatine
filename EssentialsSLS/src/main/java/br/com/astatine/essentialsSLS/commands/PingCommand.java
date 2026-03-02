package br.com.astatine.essentialsSLS.commands;

import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import javax.annotation.Nonnull;

public class PingCommand extends CommandBase {
    public PingCommand() {
        // Second parameter is a description/translation key.
        super("ping", "EssentialsSLS.commands.ping.desc");
    }

    @Override
    protected void executeSync(@Nonnull CommandContext context) {
        context.sendMessage(Message.raw("Pong!"));
    }
}

/*
getCommandRegistry().registerCommand(...) associa o proprietário do comando ao seu plugin.
Use CommandManager.get().registerSystemCommand(...) somente para comandos de nível de sistema.
*/