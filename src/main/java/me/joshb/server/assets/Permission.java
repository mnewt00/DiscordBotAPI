package me.joshb.server.assets;

public enum Permission {

    GAME_DISCORD("discordbotapi.command.discord");

    private final String value;

    Permission(String value) { this.value = value; }

    public String getValue() { return value; }
}
