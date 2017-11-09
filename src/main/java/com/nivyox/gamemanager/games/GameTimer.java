package com.nivyox.gamemanager.games;

import com.nivyox.gamemanager.games.events.GameTimerTickEvent;
import org.bukkit.Bukkit;

/**
 * Created by Niek on 1-7-2017.
 * This is project (DragonMemescape) has been made my Nivyox (https://twitter.com/nivyox).
 * If you want to reuse the code or make something using this code, please inform me. Credits appreciated.
 */
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
