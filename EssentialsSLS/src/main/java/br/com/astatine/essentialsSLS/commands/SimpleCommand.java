package br.com.astatine.essentialsSLS.commands;

import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.Message;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nonnull;

public class SimpleCommand extends AbstractCommand{
//public class SimpleCommand extends AbstractPlayerCommand{

    public SimpleCommand() {
        super("EssentialsSLS", "Mostra uma mensagem de boas-vindas.");
    }

    @Override
    protected CompletableFuture<Void> execute(@Nonnull CommandContext context) {
        context.sender().sendMessage(Message.raw("Parabéns! Seu commando foi executado com sucesso!"));
        return null;
    }

}


/*
// CORRECT - AbstractPlayerCommand provides thread-safe context
public class MyCommand extends AbstractPlayerCommand {
    protected void execute(Ref<EntityStore> ref, Store<EntityStore> store, CommandContext ctx) {
        // Safe - runs on world thread
        store.getComponent(ref, CustomComponent.getComponentType());
    }
}
*/