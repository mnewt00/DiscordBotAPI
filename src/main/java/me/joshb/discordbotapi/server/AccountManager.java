package me.joshb.discordbotapi.server;

import me.joshb.discordbotapi.server.config.LinkedAccounts;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;

public class AccountManager {

    public AccountManager(){}
    private static AccountManager instance = new AccountManager();
    public static AccountManager getInstance(){
        return instance;
    }

    public void setCode(UUID uuid, String code){
        getConfig().set(uuid.toString() + ".Code", code);
        LinkedAccounts.getInstance().save();
    }

    public String getCode(UUID uuid){
        return getConfig().getString(uuid.toString() + ".Code");
    }

    public boolean matchCode(String code){
        for(String uuid : getConfig().getKeys(false)){
            if(getConfig().getString(uuid + ".Code").equals(code)) {
                return true;
            }
        }
        return false;
    }

    public void setDiscordID(String discordID, String code){
        for(String uuid : getConfig().getKeys(false)){
            if(getConfig().getString(uuid + ".Code").equals(code)) {
                getConfig().set(uuid + ".Discord-ID", discordID);
                LinkedAccounts.getInstance().save();
            }
        }
    }

    public String getDiscordID(UUID uuid){
        return getConfig().getString(uuid.toString() + ".Discord-ID");
    }

    public UUID getUUID(String discordID){
        for(String uuid : getConfig().getKeys(false)){
            if(getConfig().getString(uuid + ".Discord-ID").equals(discordID)){
                return UUID.fromString(uuid);
            }
        }
        return null;
    }

    public void unlinkAccount(UUID uuid){
        getConfig().set(uuid.toString(), null);
        LinkedAccounts.getInstance().save();
    }

    public void unlinkAccount(String discordID){
        for(String uuid : getConfig().getKeys(false)){
            if(getConfig().getString(uuid + ".Discord-ID").equals(discordID)) {
                getConfig().set(uuid, null);
                LinkedAccounts.getInstance().save();
            }
        }
    }

    private FileConfiguration getConfig(){
        return LinkedAccounts.getInstance().getConfig();
    }
}
