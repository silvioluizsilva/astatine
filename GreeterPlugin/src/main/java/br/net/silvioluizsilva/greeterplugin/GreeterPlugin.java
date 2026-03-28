package br.net.silvioluizsilva.greeterplugin;

import com.hypixel.hytale.event.EventPriority;
import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;

import br.net.silvioluizsilva.greeter.config.JsonConfigManager;
import br.net.silvioluizsilva.greeter.database.MySQLManager;
import br.net.silvioluizsilva.greeterplugin.commands.GreetCommand;
import br.net.silvioluizsilva.greeterplugin.util.ConsoleColor;

import java.io.File;
import java.util.logging.Level;
import javax.annotation.Nonnull;

// Declaração da classe
// Aqui você está criando um plugin chamado GreeterPlugin, herdando de JavaPlugin
public class GreeterPlugin extends JavaPlugin {

    // Guarda uma instância global do plugin
	private static GreeterPlugin instance;

    // Permite acessar o plugin de qualquer lugar
	public static GreeterPlugin getInstance() {
        return instance;
    }
	
	// Conexão com o banco de dados
	private MySQLManager mysql;

	// Você está criando um gerenciador de configuração tipado
	private final Config<GreeterConfig> config;
	
	public static final String PREFIX = ConsoleColor.PURPLE + "[Prefix] " + ConsoleColor.RESET;

    public GreeterPlugin(@Nonnull JavaPluginInit init) {
    	// Inicializa o plugin base
        super(init);
        // Cria o arquivo JSON automaticamente (withConfig)
        // Define como ler e escrever o JSON (GreeterConfig.CODEC)
        this.config = withConfig(GreeterConfig.CODEC);
    }

    @Override
    // Esse método roda quando o plugin está sendo preparado
    protected void setup() {
        // Agora o plugin pode ser acessado globalmente
    	instance = this;

        // Registra o comando /greet e passa o plugin como referência
    	getCommandRegistry().registerCommand(new GreetCommand(this));

        // Registra o evento de um jogador entrar no servidor
    	getEventRegistry().register(
    		// Prioridade padrão
            EventPriority.NORMAL,
            PlayerConnectEvent.class,
            // Método callback
            this::onPlayerConnect
        );

        // Escreve no console a mensagem de log
    	getLogger().at(Level.INFO).log("Inicialização do GreeterPlugin está completa! [01]");
        // Escreve no console a mensagem de log colorida
    	getLogger().at(Level.INFO).log(
    			ConsoleColor.BLUE +
    			"Inicialização do GreeterPlugin está completa! [01]" +
    			ConsoleColor.RESET);
        // Escreve no console a mensagem de log colorida e com prefixo colorido
    	getLogger().at(Level.INFO).log(
    			PREFIX +
    			ConsoleColor.BLUE +
    			"Inicialização do GreeterPlugin está completa! [01]" +
    			ConsoleColor.RESET);
    	
    	// Verifica se o arquivo existe, e se não existir ele cria
    	// File configFile = new File(getDataDirectory().toFile(),"config/greeter.json");
    	// JsonConfigManager.createDefaultConfig(configFile);
    	
    	// Verifica se o arquivo e o diretório existe, e se não existir ele cria
    	File folder = getDataDirectory().toFile();
    	if (!folder.exists()) {
    		folder.mkdirs();
    	}
    	File configFile = new File(folder, "config/greeter.json");
    	JsonConfigManager.createDefaultConfig(configFile);
    	
    	// Envia para o banco de dados as informações
    	mysql = new MySQLManager(
    			 "212.85.3.230",
    			 3306,
    			 "jdbc:mysql://212.85.3.230:3306/u285590045_dev_plugin?useSSL=false",
    			 "u285590045_Hytale",
    			 "Hytale@2026"
    			);
    	mysql.connect();
    }

    @Override
    // Esse método roda quando o plugin é ativado de fato
    protected void start() {
        getLogger().at(Level.INFO).log(
            "Greeter v%s inicializado! Mensagem de Boas Vindas: %s. [02]",
            // Versão do plugin
            getManifest().getVersion(),
            // Operador ternário (condição ? valor1 : valor2)
            getConfig().isEnableWelcome() ? "ativo [03]" : "inativo [04]"
        );
    }

    @Override
 // Esse método roda quando o plugin é desativado
    protected void shutdown() {
        getLogger().at(Level.INFO).log("GreeterPlugin finalizado com sucesso! [05]");
        // Limpa a instância para evitar vazamentos de memória
        instance = null;
        mysql.disconnect();
    }

    @Nonnull
    // Retorna o objeto real da configuração
    public GreeterConfig getConfig() {
        return config.get();
    }

    // Esse método é chamado automaticamente quando um jogador entra
    private void onPlayerConnect(PlayerConnectEvent event) {
    	// Verifica se a ação está habilitada e se não estiver, não faz nada
        if (!getConfig().isEnableWelcome()) {
            return;
        }

        // Cria uma mensagem personalizada
        String welcomeMessage = String.format(
            getConfig().getWelcomeMessage(),
            event.getPlayerRef().getUsername()
        );

        // Mostra no console do servidor a mensagem
        getLogger().at(Level.INFO).log(welcomeMessage);
    }
}