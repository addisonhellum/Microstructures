package us.blockcade.microstructures.util.menu;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import us.blockcade.microstructures.Main;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MenuHandler implements Listener {

    public static Set<Inventory> lockedInventories = new HashSet<>();
    public static Set<MenuGUI> boundMenus = new HashSet<>();

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        ItemStack item = event.getCurrentItem();
        InventoryAction action = event.getAction();

        if (item == null || item.getType() == null) return;

        if (item.getItemMeta() != null) {
            ItemMeta meta = item.getItemMeta();

            if (inv.getTitle().contains(" (Page ")) {
                String title = inv.getTitle().substring(0, inv.getTitle().indexOf(" (Page "));
                UUID uuid = PagedMenu.getUUID(title);

                if (uuid == null) return;
                PagedMenu menu = PagedMenu.fromUUID(uuid);

                if (meta.getDisplayName() != null) {
                    if (meta.getDisplayName().contains("Next Page") || meta.getDisplayName().contains("Previous Page")) {
                        String loreLine = meta.getLore().get(0);
                        int page = Integer.valueOf(loreLine.substring(loreLine.length() - 1, loreLine.length()));

                        menu.display(player, page - 1);
                    }
                }
            }

            if (meta.getDisplayName() != null) {
                if (meta.getDisplayName().contains("Close") ||
                                meta.getDisplayName().contains("Cancel")) {

                    player.playSound(player.getLocation(), Sound.NOTE_SNARE_DRUM, 8, 8);
                    player.closeInventory();
                }
            }
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    for (MenuGUI menu : boundMenus) {
                        if (menu.build().equals(inv)) {
                            if (action.equals(InventoryAction.PICKUP_ALL)) {
                                if (menu.commandBindsLeft.get(item) != null) {
                                    player.performCommand(menu.commandBindsLeft.get(item));
                                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 8, 8);
                                }

                            } else if (action.equals(InventoryAction.PICKUP_HALF)) {
                                if (menu.commandBindsRight.get(item) != null) {
                                    player.performCommand(menu.commandBindsRight.get(item));
                                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 8, 8);
                                }
                            }
                        }
                    }
                } catch (ConcurrentModificationException e) {
                    // Ignore this, it doesn't cause any problems, but does
                    // occasionally occur when a menu is spammed
                }
            }
        }.runTaskLater(Main.getInstance(), 4);

        if (lockedInventories.contains(inv)) event.setCancelled(true);
    }

}
