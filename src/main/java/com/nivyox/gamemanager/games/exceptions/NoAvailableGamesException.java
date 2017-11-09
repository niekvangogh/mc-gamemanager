package com.nivyox.gamemanager.games.exceptions;

import com.nivyox.gamemanager.GameManager;

/**
 * Created by Niek on 1-7-2017.
 * This is project (DragonMemescape) has been made my Nivyox (https://twitter.com/nivyox).
 * If you want to reuse the code or make something using this code, please inform me. Credits appreciated.
 */
public class NoAvailableGamesException extends Throwable {
    public NoAvailableGamesException() {
        GameManager.getInstance().getLogger().warning("There are no Games available!");
    }
}
