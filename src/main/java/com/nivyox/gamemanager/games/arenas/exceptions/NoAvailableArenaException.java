package com.nivyox.gamemanager.games.arenas.exceptions;

import com.nivyox.gamemanager.GameManager;

/**
 * Created by Niek on 1-7-2017.
 * This is project (DragonMemescape) has been made my Nivyox (https://twitter.com/nivyox).
 * If you want to reuse the code or make something using this code, please inform me. Credits appreciated.
 */
public class NoAvailableArenaException extends Throwable {

    public NoAvailableArenaException() {
        GameManager.getInstance().getLogger().warning("Couldn't create game! No arena's available!");
    }
}
