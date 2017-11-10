package com.nivyox.gamemanager.games;

import com.nivyox.gamemanager.utils.ConfigHandler;

public class GameSettings {
    public int maxPlayers = (int) ConfigHandler.getValue(ConfigHandler.ConfigPaths.GAME_OPTIONS_MAX_PLAYERS);
    public int minPlayers = (int) ConfigHandler.getValue(ConfigHandler.ConfigPaths.GAME_OPTIONS_MIN_PLAYERS);
    public int pregameCountdown = (int) ConfigHandler.getValue(ConfigHandler.ConfigPaths.GAME_OPTIONS_PREGAMECOUNTDOWN);
    public int endgameTime = (int) ConfigHandler.getValue(ConfigHandler.ConfigPaths.GAME_OPTIONS_ENDGAMETIME);
}
