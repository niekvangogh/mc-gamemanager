package com.nivyox.gamemanager.games.events;

import com.nivyox.gamemanager.games.GameTimer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class GameTimerTickEvent extends Event {
    private final GameTimer gameTimer;

    public GameTimerTickEvent(GameTimer gameTimer) {
        this.gameTimer = gameTimer;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    private static HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
