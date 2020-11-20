package me.joshb.discordbotapi.bungee;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.md_5.bungee.api.plugin.Plugin;

import javax.security.auth.login.LoginException;

public class DiscordBotAPI extends Plugin {

    public static JDA jda;

    public static DiscordBotAPI plugin;

    public void onEnable(){
        plugin = this;

        Config.getInstance().initialize();

        if(Config.getInstance().getConfig().getString("Bot.Token").equals("token_here")){
            getLogger().severe("Plugin Disabled. The bot token is invalid.");
            return;
        }

        JDABuilder builder = JDABuilder.createDefault(Config.getInstance().getConfig().getString("Bot.Token"));

        try {
            jda = builder.build();
        } catch (LoginException e) {
            getLogger().severe("Plugin Disabled. The bot token is invalid. Reason: " + e.getMessage());
        }
    }

    public void onDisable(){
        if(jda != null){
            jda.shutdown();
        }
    }

    public static JDA getJDA(){
        return jda;
    }
}
