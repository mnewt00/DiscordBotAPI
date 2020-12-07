package me.joshb.discordbotapi;

public enum Permission {

    GAME_DISCORD("discordbotapi.command.discord"),
    GAME_DISCORD_LINK("discordbotapi.command.discord.link"),
    GAME_DISCORD_UNLINK("discordbotapi.command.discord.unlink");

    private final String value;

    Permission(String value) { this.value = value; }

    public String getValue() { return value; }
}
