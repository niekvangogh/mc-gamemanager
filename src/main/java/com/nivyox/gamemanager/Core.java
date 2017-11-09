package com.nivyox.gamemanager;

import com.nivyox.gamemanager.commands.CommandStartGame;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    public static Core getInstance() {
        return getPlugin(Core.class);
    }

    @Override
    public void onEnable() {
        getCommand("startgame").setExecutor(new CommandStartGame());

        getConfig().options().copyDefaults(true);
        saveConfig();

        getLogger().info("GameManager enabled! Ready to be used.");
    }
}
