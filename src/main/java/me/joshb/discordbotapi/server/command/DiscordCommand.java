package me.joshb.discordbotapi.server.command;

import org.bukkit.entity.Player;

public abstract class DiscordCommand {

    public abstract String command();

    public abstract void onCommand(Player p, String[] args);
}
