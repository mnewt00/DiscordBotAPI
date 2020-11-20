package me.joshb.discordbotapi.bungee;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private String fileName = "Config";

    Configuration config;
    ConfigurationProvider configp;

    File file;

    public Config(){ }
    private static Config instance = new Config();
    public static Config getInstance(){
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
        initSections();
        save();
    }

    public void initSections(){
        if(!config.contains("Bot.Token")){
            config.set("Bot.Token", "token_here");
        }
    }

    public void save() {
        try {
            configp = ConfigurationProvider.getProvider(YamlConfiguration.class);
            configp.save(config, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload(){
        try {
            configp = ConfigurationProvider.getProvider(YamlConfiguration.class);
        } catch (Exception e){
            System.out.println("COULD NOT RELOAD FILE: " + fileName);
            e.printStackTrace();
        }
    }
}
