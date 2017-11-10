package com.nivyox.gamemanager.games.exceptions;

import com.nivyox.gamemanager.GameManager;

public class NoAvailableGamesException extends Throwable {
    public NoAvailableGamesException() {
        GameManager.getInstance().getLogger().warning("There are no Games available!");
    }
}
