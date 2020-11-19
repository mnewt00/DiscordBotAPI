package me.joshb.server;

import me.joshb.server.config.Config;
import me.joshb.server.config.LinkedAccounts;
import me.joshb.server.listener.DiscordMessageReceived;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public class DiscordBotAPI extends JavaPlugin {

    public static JDA jda;

    public static DiscordBotAPI plugin;

    public void onEnable(){
        plugin = this;

        Config.getInstance().initialize();
        LinkedAccounts.getInstance().initialize();

        if(Config.getInstance().getConfig().getString("Bot.Token").equals("token_here")){
            getLogger().severe("Plugin Disabled. The bot token is invalid.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        JDABuilder builder = JDABuilder.createDefault(Config.getInstance().getConfig().getString("Bot.Token"));

        try {
            jda = builder.build();
        } catch (LoginException e) {
            getLogger().severe("Plugin Disabled. The bot token is invalid. Reason: " + e.getMessage());
            Bukkit.getPluginManager().disablePlugin(this);
        }
        jda.addEventListener(new DiscordMessageReceived());
    }

    public void onDisable(){
        if(jda != null){
            jda.shutdown();
        }
    }

    public static JDA getJDA(){
        return jda;
    }

    public static AccountManager getAccountManager(){
        return AccountManager.getInstance();
    }

}
