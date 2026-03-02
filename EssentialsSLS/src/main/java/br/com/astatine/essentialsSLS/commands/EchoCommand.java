package br.com.astatine.essentialsSLS.commands;

import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.DefaultArg;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import javax.annotation.Nonnull;

public class EchoCommand extends CommandBase {
    private final RequiredArg<String> textArg =
        this.withRequiredArg("texto", "EssentialsSLS.commands.echo.text", ArgTypes.STRING);
    private final DefaultArg<Integer> timesArg =
        this.withDefaultArg("vezes", "EssentialsSLS.commands.echo.times", ArgTypes.INTEGER, 1, "1");

    public EchoCommand() {
        super("echo", "EssentialsSLS.commands.echo.desc");
    }

    @Override
    protected void executeSync(@Nonnull CommandContext context) {
        String texto = textArg.get(context);
        int vezes = timesArg.get(context);
        for (int i = 0; i < vezes; i++) {
            context.sendMessage(Message.raw(texto));
        }
    }
}