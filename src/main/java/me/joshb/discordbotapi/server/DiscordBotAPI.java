package me.joshb.discordbotapi.server;

import me.joshb.discordbotapi.server.config.Config;
import me.joshb.discordbotapi.server.config.LinkedAccounts;
import me.joshb.discordbotapi.server.config.Messages;
import me.joshb.discordbotapi.server.config.command.CommandManager;
import me.joshb.discordbotapi.server.listener.DiscordMessageReceived;
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
        Messages.getInstance().initialize();
        LinkedAccounts.getInstance().initialize();

        CommandManager cm = new CommandManager();
        cm.initializeSubCommands();
        getCommand("discord").setExecutor(cm);

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
