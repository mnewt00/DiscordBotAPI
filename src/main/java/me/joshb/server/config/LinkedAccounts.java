package me.joshb.server.config;

import me.joshb.server.DiscordBotAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class LinkedAccounts {

    private String fileName = "linked_accounts";

    FileConfiguration config;

    File file;

    public LinkedAccounts(){ }
    private static LinkedAccounts instance = new LinkedAccounts();
    public static LinkedAccounts getInstance(){
        return instance;
    }

    public FileConfiguration getConfig(){
        return config;
    }

    public void save(){
        try {
            config.save(file);
        } catch (Exception e){
            System.out.println("COULD NOT SAVE FILE: " +fileName);
            e.printStackTrace();
        }
    }

    public void reload(){
        try {
            config.load(file);
        } catch (Exception e){
            System.out.println("COULD NOT RELOAD FILE: " + fileName);
            e.printStackTrace();
        }
    }

    public void initialize(){
        if (!DiscordBotAPI.plugin.getDataFolder().exists()) {
            DiscordBotAPI.plugin.getDataFolder().mkdir();
        }

        file = new File(DiscordBotAPI.plugin.getDataFolder(), fileName + ".yml");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);

    }
}
