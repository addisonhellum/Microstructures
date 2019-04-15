package us.blockcade.microstructures.api.events;

import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import us.blockcade.microstructures.api.Microstructure;

public class MicrostructureCreateEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private Microstructure microstructure;
    private Location location;

    private boolean cancelled = false;

    public MicrostructureCreateEvent(Microstructure microstructure, Location location) {
        this.microstructure = microstructure;
        this.location = location;
    }

    public Microstructure getMicrostructure() {
        return microstructure;
    }

    public Location getLocation() {
        return location;
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
