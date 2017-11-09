package com.nivyox.gamemanager.games;

import org.bukkit.entity.Player;

/**
 * Created by Niek on 30-6-2017.
 * This is project (DragonMemescape) has been made my Nivyox (https://twitter.com/nivyox).
 * If you want to reuse the code or make something using this code, please inform me. Credits appreciated.
 */
public class GamePlayerDetails {
    private final Player players;
    public boolean isAlive;
    public boolean isSpectator;
    
    public GamePlayerDetails(Player player) {
        this.players = player;
    }
}
