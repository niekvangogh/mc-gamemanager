package com.nivyox.gamemanager.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemStackUtils {

    public enum ArmorTypes {
        CHESTPLATE, LEGGINGS, BOOTS, HELMET, NOT_ARMOR
    }

    public static ArmorTypes isArmor(ItemStack item) {
        if (item == null) {
            return ArmorTypes.NOT_ARMOR;
        }
        Material type = item.getType();
        boolean chestplate = type.name().toLowerCase().contains("chestplate"),
                leggings = type.name().toLowerCase().contains("leggings"),
                boots = type.name().toLowerCase().contains("boots"),
                helmet = type.name().toLowerCase().contains("helmet");
        if (chestplate) return ArmorTypes.CHESTPLATE;
        if (leggings) return ArmorTypes.LEGGINGS;
        if (boots) return ArmorTypes.BOOTS;
        if (helmet) return ArmorTypes.HELMET;
        return ArmorTypes.NOT_ARMOR;
    }
}
