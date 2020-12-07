package me.joshb.discordbotapi.bungee.config;

import me.joshb.discordbotapi.bungee.DiscordBotAPI;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LinkedAccounts {

    private String fileName = "linked_accounts";

    Configuration config;
    ConfigurationProvider configp;

    File file;

    public LinkedAccounts(){ }
    private static LinkedAccounts instance = new LinkedAccounts();
    public static LinkedAccounts getInstance(){
        return instance;
    }

    public Configuration getConfig(){
        return config;
    }


    public void initialize(){
        if (!DiscordBotAPI.plugin.getDataFolder().exists()) {
            DiscordBotAPI.plugin.getDataFolder().mkdir();
        }

        file = new File(DiscordBotAPI.plugin.getDataFolder(), fileName + ".yml");

        try{
            if(!file.exists()){
                file.createNewFile();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        configp = ConfigurationProvider.getProvider(YamlConfiguration.class);
        try{
            config = configp.load(file);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void save() {
        try {
            configp.save(config, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload(){
        try {
            config = configp.load(file);
        } catch (IOException e){
            System.out.println("COULD NOT RELOAD FILE: " + fileName);
            e.printStackTrace();
        }
    }
}
