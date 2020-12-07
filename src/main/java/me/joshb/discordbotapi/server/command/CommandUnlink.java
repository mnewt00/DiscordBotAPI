package me.joshb.discordbotapi.server.command;

import me.joshb.discordbotapi.Permission;
import me.joshb.discordbotapi.server.DiscordBotAPI;
import me.joshb.discordbotapi.server.assets.Assets;
import me.joshb.discordbotapi.server.config.Config;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandUnlink extends DiscordCommand {

    private final String command = Config.getInstance().getConfig().getString("Bot.Command-Prefix");

    @Override
    public String command() {
        return "unlink";
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if(!p.hasPermission(Permission.GAME_DISCORD_UNLINK.getValue())){
            p.sendMessage(Assets.format("Game.Discord.Commands.No-Permission"));
            return;
        }
        if(DiscordBotAPI.getAccountManager().getDiscordID(p.getUniqueId()) == null){
            //Not linked
            List<String> notLinked = Assets.formatStringList("Game.Commands.Discord.Sub-Commands.Unlink.Not-Linked");
            for(String s : notLinked){
                p.sendMessage(s
                        .replaceAll("%player%", p.getName())
                        .replaceAll("%discord_bot_name%", DiscordBotAPI.getJDA().getSelfUser().getName()));
            }
        } else {
            //Linked
            User u = DiscordBotAPI.getJDA().getUserById(DiscordBotAPI.getAccountManager().getDiscordID(p.getUniqueId()));
            List<String> linked = Assets.formatStringList("Game.Commands.Discord.Sub-Commands.Unlink.Linked");
            for(String s : linked){
                p.sendMessage(s
                        .replaceAll("%player%", p.getName())
                        .replaceAll("%discord_bot_name%", DiscordBotAPI.getJDA().getSelfUser().getName())
                        .replaceAll("%discord_author_name%", u.getName())
                        .replaceAll("%discord_author_discriminator%", u.getDiscriminator()));
            }
            DiscordBotAPI.getAccountManager().unlinkAccount(p.getUniqueId());
        }
    }
}
