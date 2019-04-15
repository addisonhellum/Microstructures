package us.blockcade.microstructures.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import us.blockcade.microstructures.api.Microstructure;

public class PlayerInteractMicrostructureEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private Action action;
    private ItemStack item;
    private Microstructure microstructure;

    private boolean cancelled = false;

    public PlayerInteractMicrostructureEvent(Player player, Action action, ItemStack item, Microstructure microstructure) {
        this.player = player;
        this.action = action;
        this.item = item;
        this.microstructure = microstructure;
    }

    public Player getPlayer() {
        return player;
    }

    public Microstructure getMicrostructure() {
        return microstructure;
    }

    public Action getAction() {
        return action;
    }

    public ItemStack getItem() {
        return item;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
