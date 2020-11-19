package me.joshb.server.assets;

import me.joshb.server.config.Messages;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Assets {

    public static String formatDiscordMessage(String location, User user){
        return Messages.getInstance().getConfig().getString(location)
                .replaceAll("%author_@%", user.getAsMention())
                .replaceAll("%author_name%", user.getName())
                .replaceAll("%author_id%", user.getId());
    }

    public static String format(String location){
        return ChatColor.translateAlternateColorCodes('&',
                Messages.getInstance().getConfig().getString(location).replaceAll("%prefix%",
                        Messages.getInstance().getConfig().getString("Game.Prefix")));
    }

    public static List<String> formatStringList(String location){
        List<String> newList = new ArrayList<>();
        for(String s : Messages.getInstance().getConfig().getStringList(location)){
            newList.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        return newList;
    }

}
