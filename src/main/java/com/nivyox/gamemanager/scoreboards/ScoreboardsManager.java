package com.nivyox.gamemanager.scoreboards;

import com.nivyox.gamemanager.games.Game;
import com.nivyox.gamemanager.games.GameSpecifications;
import com.nivyox.gamemanager.utils.ConfigHandler;
import com.nivyox.gamemanager.utils.DateUtilsMeme;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Niek on 1-7-2017.
 * This is project (DragonMemescape) has been made my Nivyox (https://twitter.com/nivyox).
 * If you want to reuse the code or make something using this code, please inform me. Credits appreciated.
 */
public class ScoreboardsManager {

    private final Game game;
    private final Map<String, ArrayList<String>> scoreboardTemplate;

    public ScoreboardsManager(Game game) {
        this.game = game;

        this.scoreboardTemplate = (Map<String, ArrayList<String>>) ConfigHandler.getConfigSection(ConfigHandler.ConfigPaths.GAME_SCOREBOARD_TEMPLATES);
        this.scoreboardTemplate.forEach((s, strings) -> Collections.reverse(strings));

        if (game.getScoreboard().getObjective("sidebar") == null) {
            Objective sidebar = game.getScoreboard().registerNewObjective("sidebar", "dummy");
            sidebar.setDisplayName(ChatColor.YELLOW + GameSpecifications.getGamename());
            sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
        }
    }

    public void giveScoreboard(Player player) {
        updateSidebar();

        player.setScoreboard(game.getScoreboard());
    }

    private void updateSidebar() {
        Objective sidebar = game.getScoreboard().getObjective("sidebar");
        sidebar.unregister();
        sidebar = game.getScoreboard().registerNewObjective("sidebar", "dummy");
        sidebar.setDisplayName(ChatColor.YELLOW + GameSpecifications.getGamename());
        sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);

        ArrayList<String> items = scoreboardTemplate.get(game.getGameState().name());

        for (int i = 0; i < items.size(); i++) {
            sidebar.getScore(ChatColor.translateAlternateColorCodes('&', replaceValues(items.get(i)))).setScore(i);
        }
    }

    private String replaceValues(String s) {
        s = s.replaceAll("%time%", DateUtilsMeme.getTimeFromSeconds(game.getGameTimer().getTime()));
        s = s.replaceAll("%players%", String.valueOf(game.getPlayers().size()));
        s = s.replaceAll("%maxplayers%", String.valueOf(game.getGameSettings().maxPlayers));
        s = s.replaceAll("%aliveplayers%", String.valueOf(game.getAlivePlayers().size()));
        s = s.replaceAll("%requiredplayers%", String.valueOf(game.getGameSettings().minPlayers));
        s = s.replaceAll("%onlineplayers%", String.valueOf(game.getOnlinePlayers().size()));
        return s;
    }
}
