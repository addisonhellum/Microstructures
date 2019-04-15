package us.blockcade.microstructures.api.events.handler;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import us.blockcade.microstructures.Main;
import us.blockcade.microstructures.api.Microblock;
import us.blockcade.microstructures.api.events.PlayerInteractMicrostructureEvent;

import java.util.UUID;

public class EventAssigner implements Listener {

    @EventHandler
    public void onMicrostructureInteract(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if (!(entity instanceof ArmorStand)) return;
        ArmorStand stand = (ArmorStand) entity;

        if (!stand.hasMetadata("Microblock")) return;

        UUID uuid = UUID.fromString(stand.getMetadata("Microblock").get(0).asString());
        Microblock clicked = Microblock.fromUUID(uuid);

        event.setCancelled(true);

        if (clicked.getStructure() == null) return;

        PlayerInteractMicrostructureEvent interactEvent = new PlayerInteractMicrostructureEvent(player,
                Action.RIGHT_CLICK_BLOCK, player.getItemInHand(), clicked.getStructure());
        Main.getInstance().getServer().getPluginManager().callEvent(interactEvent);
    }

    @EventHandler
    public void onMicrostructureDamage(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();

        if (!(entity instanceof ArmorStand)) return;
        if (!(damager instanceof Player)) return;

        ArmorStand stand = (ArmorStand) entity;

        if (!stand.hasMetadata("Microblock")) return;
        Player player = (Player) damager;

        UUID uuid = UUID.fromString(stand.getMetadata("Microblock").get(0).asString());
        Microblock clicked = Microblock.fromUUID(uuid);

        event.setCancelled(true);

        if (clicked.getStructure() == null) return;

        PlayerInteractMicrostructureEvent interactEvent = new PlayerInteractMicrostructureEvent(player,
                Action.LEFT_CLICK_BLOCK, player.getItemInHand(), clicked.getStructure());
        Main.getInstance().getServer().getPluginManager().callEvent(interactEvent);
    }

}
