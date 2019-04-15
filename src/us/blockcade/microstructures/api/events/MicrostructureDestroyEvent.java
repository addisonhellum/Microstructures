package us.blockcade.microstructures.api.events;

import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import us.blockcade.microstructures.api.Microstructure;

public class MicrostructureDestroyEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private Microstructure microstructure;

    private boolean cancelled = false;

    public MicrostructureDestroyEvent(Microstructure microstructure) {
        this.microstructure = microstructure;
    }

    public Microstructure getMicrostructure() {
        return microstructure;
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
