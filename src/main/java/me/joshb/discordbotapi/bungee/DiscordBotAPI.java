package me.joshb.discordbotapi.bungee;

import me.joshb.discordbotapi.bungee.config.Config;
import me.joshb.discordbotapi.bungee.config.LinkedAccounts;
import me.joshb.discordbotapi.bungee.config.Messages;
import me.joshb.discordbotapi.bungee.command.CommandManager;
import me.joshb.discordbotapi.bungee.listener.DiscordMessageReceived;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import javax.security.auth.login.LoginException;

public class DiscordBotAPI extends Plugin {

    public JDA jda;

    public static DiscordBotAPI plugin;

    public void onEnable(){
        plugin = this;

        Config.getInstance().initialize();
        LinkedAccounts.getInstance().initialize();
        Messages.getInstance().initialize();

        if(Config.getInstance().getConfig().getString("Bot.Token").equals("token_here")){
            getLogger().severe("Plugin Disabled. The bot token is invalid.");
            return;
        }

        CommandManager cm = new CommandManager();
        cm.initializeSubCommands();
        ProxyServer.getInstance().getPluginManager().registerCommand(this, cm);

        JDABuilder builder = JDABuilder.create(Config.getInstance().getConfig().getString("Bot.Token"), GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGE_REACTIONS);

        try {
            jda = builder.build();
        } catch (LoginException e) {
            getLogger().severe("Plugin Disabled. The bot token is invalid. Reason: " + e.getMessage());
        }
        jda.addEventListener(new DiscordMessageReceived());
    }

    public void onDisable(){
        if(jda != null){
            jda.shutdown();
        }
    }

    public JDA getJDA(){
        return jda;
    }

    public static AccountManager getAccountManager() {
        return AccountManager.getInstance();
    }

}
