package me.joshb.server.config.command;

import me.joshb.server.DiscordBotAPI;
import me.joshb.server.assets.Assets;
import me.joshb.server.assets.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

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
                
            //Linked
            } else {

            }
        }
        return false;
    }
}
