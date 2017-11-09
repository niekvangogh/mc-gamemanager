package com.nivyox.gamemanager.commands;

import com.nivyox.gamemanager.games.Game;
import com.nivyox.gamemanager.GameManager;
import com.nivyox.gamemanager.games.GameState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Niek on 2-7-2017.
 * This is project (DragonMemescape) has been made my Nivyox (https://twitter.com/nivyox).
 * If you want to reuse the code or make something using this code, please inform me. Credits appreciated.
 */
public class CommandStartGame implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Game game = GameManager.getGame(player);
            if (player.isOp()) {
                if (game.getGameState() == GameState.WAITING) {
                    game.initStart();
                    player.sendMessage("Force started the game!");
                }
            }
        }
        return false;
    }
}
