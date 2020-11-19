package me.joshb.server.listener;

import me.joshb.server.DiscordBotAPI;
import me.joshb.server.assets.Assets;
import me.joshb.server.config.Config;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordMessageReceived extends ListenerAdapter {

    private final String command = Config.getInstance().getConfig().getString("Bot.Command-Prefix");

    public void onMessageReceived(MessageReceivedEvent e) {
        if(e.getAuthor().isBot() || !e.getMessage().getContentRaw().startsWith(command)){
            return;
        }

        User u = e.getAuthor();

        String[] args = e.getMessage().getContentRaw().split(" ");
        if(args.length == 1){
            //help msgs
        } else if(args.length == 2){
            if(args[1].equalsIgnoreCase("link")){
                if(!e.getChannel().getType().equals(ChannelType.PRIVATE)){
                    e.getMessage().delete().queue();
                    e.getTextChannel().sendMessage(Assets.formatDiscordMessage("Discord.PM-Command-Only", u)).queue();
                    return;
                }
                //link help msgs
            }
        } else {
            if(args[1].equalsIgnoreCase("link")){
                if(!e.getChannel().getType().equals(ChannelType.PRIVATE)){
                    e.getMessage().delete().queue();
                    e.getTextChannel().sendMessage(Assets.formatDiscordMessage("Discord.PM-Command-Only", u)).queue();
                    return;
                }
                String code = args[2];
                if(DiscordBotAPI.getAccountManager().getCode(u.getId()) == null){
                    e.getTextChannel().sendMessage(Assets.formatDiscordMessage("Discord.No-Code-Generated", u)).queue();
                    return;
                }
                if(!DiscordBotAPI.getAccountManager().getCode(u.getId()).equals(code)){
                    e.getTextChannel().sendMessage(Assets.formatDiscordMessage("Discord.Wrong-Code", u)).queue();
                    return;
                }
                e.getTextChannel().sendMessage(Assets.formatDiscordMessage("Discord.Account-Linked", u)).queue();
                DiscordBotAPI.getAccountManager().setDiscordID(u.getId(), code);
            }
        }
    }
}
