package com.nivyox.gamemanager.games;

import org.bukkit.entity.Player;

public class GamePlayerDetails {
    private final Player players;
    public boolean isAlive;
    public boolean isSpectator;
    
    public GamePlayerDetails(Player player) {
        this.players = player;
    }
}
