package com.nivyox.gamemanager.games;

import com.nivyox.gamemanager.games.events.GameTimerTickEvent;
import org.bukkit.Bukkit;

public class GameTimer implements Runnable {

    private final Game game;
    private int time;
    public int endTime;

    public GameTimer(Game game) {
        this.game = game;
        time = game.getGameSettings().pregameCountdown;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int i) {
        this.time = i;
    }

    @Override
    public void run() {
        Bukkit.getPluginManager().callEvent(new GameTimerTickEvent(this));
    }

    public Game getGame() {
        return game;
    }

    public int getEndTime() {
        return endTime;
    }
}
