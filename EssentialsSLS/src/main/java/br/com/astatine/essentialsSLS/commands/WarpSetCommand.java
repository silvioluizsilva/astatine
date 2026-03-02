package br.com.astatine.essentialsSLS.commands;

import javax.annotation.Nonnull;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;

public class WarpSetCommand extends CommandBase {
    public WarpSetCommand() {
        super("set", "essentialssls.commands.warp.set.desc");
    }

    @Override
    protected void executeSync(@Nonnull CommandContext context) {
        context.sendMessage(Message.raw("Warp adicionada com sucesso!"));
    }
}