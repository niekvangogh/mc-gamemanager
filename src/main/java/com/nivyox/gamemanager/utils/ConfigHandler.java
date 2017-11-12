package com.nivyox.gamemanager.utils;

import com.nivyox.gamemanager.Core;
import com.nivyox.gamemanager.GameManager;
import com.nivyox.gamemanager.games.arenas.Arena;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigHandler {

    private static FileConfiguration getConfig() {
        return Core.getInstance().getConfig();
    }

    public static List<Location> getLocation(ConfigPaths path, Arena arena) {
        return (List<Location>) getConfig().getList(path.toString().replace("%%arenaname%%", arena.getWorld().getName()), null);
    }

    public static Object getValue(ConfigPaths path) {
        return getConfig().get(path.toString());
    }

    public static Object getConfigSection(ConfigPaths path) {
        if (getConfig().isSet(path.toString())) {
            return getConfig().getConfigurationSection(path.toString()).getValues(false);
        }
        return null;
    }


    public enum ConfigPaths {

        GAME_ARENA_SPAWNLOCATION("arenas.%%arenaname%%.spawnlocations"),

        GAME_NAME("game.gamename"), GAME_VERSION("game.version"), GAME_DESCRIPTION("game.description"), GAME_ARENA_NAMING("game.arenanaming"), GAME_ARENA_AMOUNT("game.arenasize"),

        GAME_OPTIONS_MIN_PLAYERS("game.settings.minplayers"), GAME_OPTIONS_MAX_PLAYERS("game.settings.maxplayers"), GAME_OPTIONS_PREGAMECOUNTDOWN("game.settings.pregamecountdown"),

        GAME_SCOREBOARD_TEMPLATES("game.scoreboards"), GAME_OPTIONS_ENDGAMETIME("game.settings.endgametime"), GAME_MAIN_LOBBY("game.mainlobbylocation");

        private final String path;

        ConfigPaths(String path) {
            this.path = path;
        }

        @Override
        public String toString() {
            return path;
        }
    }
}