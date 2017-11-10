package com.nivyox.gamemanager.games.events;

import com.nivyox.gamemanager.games.Game;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Niek on 14-8-2017.
 * This is project (GameManager) has been made my Nivyox (https://twitter.com/nivyox).
 * If you want to reuse the code or make something using this code, please inform me. Credits appreciated.
 */
public class GamePlayerDamageEvent extends Event {
    private final EntityDamageByEntityEvent damageEvent;
    private Game game;

    public GamePlayerDamageEvent(EntityDamageByEntityEvent event, Game game) {
        this.damageEvent = event;
        this.game = game;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    private static HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public EntityDamageByEntityEvent getDamageEvent() {
        return damageEvent;
    }

    public Game getGame() {
        return game;
    }
}
