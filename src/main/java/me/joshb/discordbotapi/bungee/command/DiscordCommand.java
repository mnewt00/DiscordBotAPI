package me.joshb.discordbotapi.bungee.command;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public abstract class DiscordCommand {

    public abstract String command();

    public abstract void onCommand(ProxiedPlayer p, String[] args);
}
