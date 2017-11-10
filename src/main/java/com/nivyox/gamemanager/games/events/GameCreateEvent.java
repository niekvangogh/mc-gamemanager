package com.nivyox.gamemanager.games.events;

import com.nivyox.gamemanager.games.Game;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class GameCreateEvent extends Event {
    private final Game game;

    public GameCreateEvent(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
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
