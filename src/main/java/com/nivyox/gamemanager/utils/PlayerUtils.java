package com.nivyox.gamemanager.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.nivyox.gamemanager.Core;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PlayerUtils {

    public static void setSkin(Player player, SkinType type) {
        CraftPlayer craftPlayer = ((CraftPlayer) player);
        GameProfile profile = craftPlayer.getProfile();
        profile.getProperties().clear();
        profile.getProperties().put("textures", new Property("textures", type.getValue(), type.getSignature()));
        for (Player onlineplayer : Bukkit.getOnlinePlayers()) {
            onlineplayer.hidePlayer(player);
            Bukkit.getScheduler().runTaskLater(Core.getInstance(), () -> onlineplayer.showPlayer(player), 20);
        }
    }
}