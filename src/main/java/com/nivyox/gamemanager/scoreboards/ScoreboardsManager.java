package com.nivyox.gamemanager.scoreboards;

import com.nivyox.gamemanager.games.Game;
import com.nivyox.gamemanager.games.GameSpecifications;
import com.nivyox.gamemanager.utils.ConfigHandler;
import com.nivyox.gamemanager.utils.DateUtilsMeme;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class ScoreboardsManager {

    private final Game game;
    private final Map<String, ArrayList<String>> scoreboardTemplate;

    public ScoreboardsManager(Game game) {
        this.game = game;

        this.scoreboardTemplate = (Map<String, ArrayList<String>>) ConfigHandler.getConfigSection(ConfigHandler.ConfigPaths.GAME_SCOREBOARD_TEMPLATES);
        this.scoreboardTemplate.forEach((s, strings) -> Collections.reverse(strings));


        if (game.getScoreboard().getObjective("sidebar") == null) {
            Objective sidebar = game.getScoreboard().registerNewObjective("sidebar", "dummy");
            sidebar.setDisplayName(ChatColor.YELLOW.toString() + ChatColor.BOLD + GameSpecifications.getGamename().toUpperCase());
            sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
        }
    }

    public void giveScoreboard(Player player) {
        Objective sidebar = game.getScoreboard().getObjective("sidebar");
        sidebar.unregister();
        sidebar = game.getScoreboard().registerNewObjective("sidebar", "dummy");
        sidebar.setDisplayName(ChatColor.YELLOW + GameSpecifications.getGamename());
        sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);

        ArrayList<String> items = scoreboardTemplate.get(game.getGameState().name());

        for (int i = 0; i < items.size(); i++) {
            sidebar.getScore(ChatColor.translateAlternateColorCodes('&', replaceValues(items.get(i), player))).setScore(i);
        }

        player.setScoreboard(game.getScoreboard());
    }

    private String replaceValues(String s, Player player) {
        ArrayList<ScoreboardReplacement> replacements = game.getScoreboardReplacements(player);
        for (ScoreboardReplacement replacement : replacements) {
            s = s.replaceAll(replacement.getSearch(), String.valueOf(replacement.getReplacement()));
        }
        return s;
    }
}
