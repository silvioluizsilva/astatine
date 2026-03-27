package br.net.silvioluizsilva.greeterplugin;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import javax.annotation.Nonnull;

// Essa classe representa a configuração do plugin
public class GreeterConfig {

    @Nonnull
    // Classe que permite construir um codec complexo usando padrão Builder
    // Aqui é criado um codec estático que descreve como salvar e carregar a configuração
    public static final BuilderCodec<GreeterConfig> CODEC = BuilderCodec
    	// Criação do BuilderCodec
    	// Classe que será serializada (GreeterConfig.class)
    	// Cria uma nova instância (GreeterConfig::new)
        .builder(GreeterConfig.class, GreeterConfig::new)
        // Define um campo específico da configuração ("MensagemBoasVindas": "Bem vindo ao servidor!")
        // Adiciona um campo no codec
        .append(
        	// Nome no arquivo ("MsgBoasVindas")
        	// Tipo de dado (Codec.String)
            new KeyedCodec<>("MsgBoasVindas", Codec.STRING),
            // Função de escrita (Lambda Expression, setter)(Quando carregar o arquivo, pegue o valor e coloque em config.mensagemBoasVindas
            (config, value) -> config.mensagemBoasVindas = value,
            // Pega o valor da classe para salvar no arquivo
            config -> config.mensagemBoasVindas
        )
        // Confirma a adição desse campo no builder
        .add()
        // Adiciona um campo no codec
        .append(
           	// Nome no arquivo ("MsgCumprimento")
           	// Tipo de dado (Codec.String)
            new KeyedCodec<>("MsgCumprimento", Codec.STRING),
            (config, value) -> config.msgCumprimento = value,
            config -> config.msgCumprimento
        )
        // Confirma a adição desse campo no builder
        .add()
        .append(
           	// Nome no arquivo ("StatusMensagens")
           	// Tipo de dado (Codec.BOOLEAN)
            new KeyedCodec<>("StatusMensagens", Codec.BOOLEAN),
            (config, value) -> config.statusMensagens = value,
            config -> config.statusMensagens
        )
        // Confirma a adição desse campo no builder
        .add()
        // Constrói o codec final
        .build();

    // Atribui valores iniciais para os campos. Se não existir um arquivo de configuração esses valores serão utilizados
    private String mensagemBoasVindas = "Bem vindo ao servidor, %s! [06]";
    private String msgCumprimento = "%s diz Olá para %s! [07]";
    private boolean statusMensagens = true;

    // Retorna a mensagem de boas-vindas
    public String getWelcomeMessage() {
        return mensagemBoasVindas;
    }

    // Retorna a mensagem de saudação
    public String getGreetMessage() {
        return msgCumprimento;
    }

    // Retorna se o sistema está ativo
    public boolean isEnableWelcome() {
        return statusMensagens;
    }
}