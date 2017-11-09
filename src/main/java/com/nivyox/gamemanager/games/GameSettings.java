package com.nivyox.gamemanager.games;

import com.nivyox.gamemanager.utils.ConfigHandler;

/**
 * Created by Niek on 1-7-2017.
 * This is project (DragonMemescape) has been made my Nivyox (https://twitter.com/nivyox).
 * If you want to reuse the code or make something using this code, please inform me. Credits appreciated.
 */
public class GameSettings {
    public int maxPlayers = (int) ConfigHandler.getValue(ConfigHandler.ConfigPaths.GAME_OPTIONS_MAX_PLAYERS);
    public int minPlayers = (int) ConfigHandler.getValue(ConfigHandler.ConfigPaths.GAME_OPTIONS_MIN_PLAYERS);
    public int pregameCountdown = (int) ConfigHandler.getValue(ConfigHandler.ConfigPaths.GAME_OPTIONS_PREGAMECOUNTDOWN);
    public int endgameTime = (int) ConfigHandler.getValue(ConfigHandler.ConfigPaths.GAME_OPTIONS_ENDGAMETIME);
}
