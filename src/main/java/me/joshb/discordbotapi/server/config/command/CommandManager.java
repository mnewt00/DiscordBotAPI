package me.joshb.discordbotapi.server.config.command;

import me.joshb.discordbotapi.server.DiscordBotAPI;
import me.joshb.discordbotapi.server.assets.Assets;
import me.joshb.discordbotapi.server.assets.Permission;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommandManager implements CommandExecutor {

    private List<DiscordCommand> commands = new ArrayList<>();

    public void initializeSubCommands(){

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmdLabel, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(Assets.format("Game.Commands.Player-Only-Command"));
            return false;
        }
        Player p = (Player) sender;
        if(!p.hasPermission(Permission.GAME_DISCORD.getValue())){
            p.sendMessage(Assets.format("Game.Commands.No-Permission"));
            return false;
        }
        if(args.length == 0){
            //Not Linked
            if(DiscordBotAPI.getAccountManager().getDiscordID(p.getUniqueId()) == null){
                List<String> notLinked = Assets.formatStringList("Game.Commands.Discord.No-Args.Not-Linked");
                for(String s : notLinked){
                    p.sendMessage(s
                            .replaceAll("%player%", p.getName()));
                }
            //Linked
            } else {
                User u = DiscordBotAPI.getJDA().getUserById(DiscordBotAPI.getAccountManager().getDiscordID(p.getUniqueId()));
                List<String> linked = Assets.formatStringList("Game.Commands.Discord.Sub-Commands.Link.Linked");
                for(String s : linked){
                    p.sendMessage(s
                            .replaceAll("%player%", p.getName())
                            .replaceAll("%discord_bot_name%", DiscordBotAPI.getJDA().getSelfUser().getName())
                            .replaceAll("%discord_author_name%", u.getName())
                            .replaceAll("%discord_author_discriminator%", u.getDiscriminator()));
                }
            }
        }
        return false;
    }
}
