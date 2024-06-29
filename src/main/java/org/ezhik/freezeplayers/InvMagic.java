package org.ezhik.freezeplayers;

import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class InvMagic {
    private static Map<String, ReturnableInv> usersinvs = new java.util.HashMap<>();
    public static void clearitems(Player player) {
        if (!usersinvs.containsKey(player.getName()))usersinvs.put(player.getName(), new ReturnableInv());
        for (int i = 0; i < 36; i++) {
            usersinvs.get(player.getName()).items[i] = player.getInventory().getItem(i);
            player.getInventory().setItem(i, null);
        }
        usersinvs.get(player.getName()).off_hand = player.getEquipment().getItem(EquipmentSlot.OFF_HAND);
        player.getEquipment().setItem(EquipmentSlot.OFF_HAND, null);
        usersinvs.get(player.getName()).head = player.getEquipment().getItem(EquipmentSlot.HEAD);
        player.getEquipment().setItem(EquipmentSlot.HEAD, null);
        usersinvs.get(player.getName()).feet = player.getEquipment().getItem(EquipmentSlot.FEET);
        player.getEquipment().setItem(EquipmentSlot.FEET, null);
        usersinvs.get(player.getName()).chest = player.getEquipment().getItem(EquipmentSlot.CHEST);
        player.getEquipment().setItem(EquipmentSlot.CHEST, null);
        usersinvs.get(player.getName()).legs = player.getEquipment().getItem(EquipmentSlot.LEGS);
        player.getEquipment().setItem(EquipmentSlot.LEGS, null);




        usersinvs.get(player.getName()).invreturn = false;
    }
    public static void returnitems(Player player) {
        if (!usersinvs.get(player.getName()).invreturn) {
            for (int i = 0; i < 36; i++) {
                ItemStack item = usersinvs.get(player.getName()).items[i];
                if (item != null)player.getInventory().setItem(i, item);
            }
            player.getEquipment().setItem(EquipmentSlot.OFF_HAND, usersinvs.get(player.getName()).off_hand);
            player.getEquipment().setItem(EquipmentSlot.HEAD, usersinvs.get(player.getName()).head);
            player.getEquipment().setItem(EquipmentSlot.FEET, usersinvs.get(player.getName()).feet);
            player.getEquipment().setItem(EquipmentSlot.CHEST, usersinvs.get(player.getName()).chest);
            player.getEquipment().setItem(EquipmentSlot.LEGS, usersinvs.get(player.getName()).legs);
            usersinvs.get(player.getName()).invreturn = true;
        }
    }



}

