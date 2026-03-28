package br.net.silvioluizsilva.greeter.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.net.silvioluizsilva.greeterplugin.GreeterConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonConfigManager {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static void createDefaultConfig(File file) {

        try {

            if (!file.exists()) {

                file.getParentFile().mkdirs();
                file.createNewFile();

                GreeterConfig config = new GreeterConfig();

                try (FileWriter writer = new FileWriter(file)) {
                    GSON.toJson(config, writer);
                }

                System.out.println("Config file created: " + file.getPath());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
