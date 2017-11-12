package com.nivyox.gamemanager.games.events;

import com.nivyox.gamemanager.games.Game;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by Niek on 5-7-2017.
 * This is project (GameManager) has been made my Nivyox (https://twitter.com/nivyox).
 * If you want to reuse the code or make something using this code, please inform me. Credits appreciated.
 */
public class GamePlayerDeathEvent extends Event {
    @Getter
    private final Game game;
    @Getter
    private Player player;
    @Getter
    private PlayerDeathEvent deathEvent;

    public GamePlayerDeathEvent(Game game, Player player, PlayerDeathEvent deathEvent) {
        this.game = game;
        this.player = player;
        this.deathEvent = deathEvent;
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
