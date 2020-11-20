package me.joshb.discordbotapi.server.assets;

public enum Permission {

    GAME_DISCORD("discordbotapi.command.discord"),
    GAME_DISCORD_LINK("discordbotapi.command.discord.link");

    private final String value;

    Permission(String value) { this.value = value; }

    public String getValue() { return value; }
}
