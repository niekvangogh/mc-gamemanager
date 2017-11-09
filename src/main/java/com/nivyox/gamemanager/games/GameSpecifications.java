package com.nivyox.gamemanager.games;

import com.nivyox.gamemanager.utils.ConfigHandler;

/**
 * Created by Niek on 2-7-2017.
 * This is project (DragonMemescape) has been made my Nivyox (https://twitter.com/nivyox).
 * If you want to reuse the code or make something using this code, please inform me. Credits appreciated.
 */
public class GameSpecifications {
    private static String gamename;
    private static String gameversion;
    private static String gamedescription;
    private static String gamearenanaming;
    private static int arenasize = 0;

    public static String getGamename() {
        if (gamename == null) {
            gamename = (String) ConfigHandler.getValue(ConfigHandler.ConfigPaths.GAME_NAME);

        }
        return gamename;
    }

    public static String getGameVersion() {
        if (gameversion == null) {
            gameversion = (String) ConfigHandler.getValue(ConfigHandler.ConfigPaths.GAME_VERSION);
        }
        return gameversion;
    }

    public static String getGameDescription() {
        if (gamedescription == null) {
            gamedescription = (String) ConfigHandler.getValue(ConfigHandler.ConfigPaths.GAME_DESCRIPTION);
        }
        return gamedescription;
    }

    public static String getGameArenaNaming() {
        if (gamearenanaming == null) {
            gamearenanaming = (String) ConfigHandler.getValue(ConfigHandler.ConfigPaths.GAME_ARENA_NAMING);
        }
        return gamearenanaming;
    }

    public static int getRequestedArenaSize() {
        if (arenasize == 0) {
            arenasize = (int) ConfigHandler.getValue(ConfigHandler.ConfigPaths.GAME_ARENA_AMOUNT);
        }
        return arenasize;
    }
}