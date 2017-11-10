package com.nivyox.gamemanager.games.arenas.exceptions;

import com.nivyox.gamemanager.GameManager;

public class NoAvailableArenaException extends Throwable {

    public NoAvailableArenaException() {
        GameManager.getInstance().getLogger().warning("Couldn't create game! No arena's available!");
    }
}
