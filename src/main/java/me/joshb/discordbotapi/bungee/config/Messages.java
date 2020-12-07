package me.joshb.discordbotapi.bungee.config;

import me.joshb.discordbotapi.bungee.DiscordBotAPI;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;

public class Messages {

    private String fileName = "Messages";

    Configuration config;
    ConfigurationProvider configp;

    File file;

    public Messages(){ }
    private static Messages instance = new Messages();
    public static Messages getInstance(){
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
                copy(DiscordBotAPI.plugin.getResourceAsStream(fileName + ".yml"), file);
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
        save();
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

    private void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
