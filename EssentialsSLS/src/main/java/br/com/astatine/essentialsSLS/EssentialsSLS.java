package br.com.astatine.essentialsSLS;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import br.com.astatine.essentialsSLS.commands.EchoCommand;
import br.com.astatine.essentialsSLS.commands.PingCommand;
import br.com.astatine.essentialsSLS.commands.SimpleCommand;
import br.com.astatine.essentialsSLS.commands.WarpCommand;

import javax.annotation.Nonnull;

public class EssentialsSLS extends JavaPlugin {

    public EssentialsSLS(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    public void start() {
        // Mensagem no console ao iniciar o plugind
        getLogger().atInfo().log("Iniciando o plugin EssentialsSLS.");

        //Registra os comandos do plugin
        getCommandRegistry().registerCommand(new SimpleCommand());
        getCommandRegistry().registerCommand(new PingCommand());
        getCommandRegistry().registerCommand(new WarpCommand());
        getCommandRegistry().registerCommand(new EchoCommand());
    }

    @Override
    protected void setup() {
        // Registra o evento do player entrar no servidor
        getEventRegistry().registerGlobal(PlayerReadyEvent.class, event -> {
            Player player = event.getPlayer();
            player.sendMessage(Message.raw("Seja muito bem vindo ao servidor Astatine!"));
            // Mensagem no console quando um jogador entra no servidor
            getLogger().atInfo().log("O player " + player.getDisplayName() + " entrou no servidor.");
        });
    }

    @Override
    public void shutdown() {
        getLogger().atInfo().log("Encerrando o plugin EssentialsSLS.");
    }
}