package us.blockcade.microstructures.api;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import us.blockcade.microstructures.Main;
import us.blockcade.microstructures.api.events.MicrostructureCreateEvent;
import us.blockcade.microstructures.api.events.MicrostructureDestroyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Microstructure {

    public enum MicroSpacing {
        BLOCK(0.435),
        HEAD(0.40);

        private double space;

        MicroSpacing(double space) {
            this.space = space;
        }

        public double getValue() { return space; }
    }

    private UUID uuid = UUID.randomUUID();

    private String name;
    private Location origin;
    private List<Microblock> microblocks = new ArrayList<>();

    private Microstructure parent;
    private List<Microstructure> children = new ArrayList<>();

    private static List<Microstructure> microstructures = new ArrayList<>();

    public static List<Microstructure> getAllMicrostructures() { return microstructures; }

    public static List<Microstructure> getGrandMicrostructures() {
        List<Microstructure> structures = new ArrayList<>();

        for (Microstructure ms : getAllMicrostructures())
            if (!ms.isChild()) structures.add(ms);

        return structures;
    }

    public static Microstructure fromUUID(UUID uuid) {
        for (Microstructure ms : getAllMicrostructures())
            if (ms.getUniqueId().equals(uuid)) return ms;

        return null;
    }

    public Microstructure(String name, List<Microstructure> childStructures) {
        this.name = name;
        origin = childStructures.get(0).getOrigin();

        for (Microstructure structure : childStructures) {
            for (Microblock microblock : structure.getMicroblocks()) {
                microblocks.add(microblock);
                microblock.setStructure(this);
            }

            structure.setParent(this);
        }

        microstructures.add(this);

        MicrostructureCreateEvent createEvent = new MicrostructureCreateEvent(this, origin);
        Main.getInstance().getServer().getPluginManager().callEvent(createEvent);
    }

    public Microstructure(String name, Location origin, List<Microblock> microblocks) {
        this.name = name;
        this.origin = origin;
        this.microblocks = microblocks;

        for (Microblock microblock : microblocks) microblock.setStructure(this);

        microstructures.add(this);

        MicrostructureCreateEvent createEvent = new MicrostructureCreateEvent(this, origin);
        Main.getInstance().getServer().getPluginManager().callEvent(createEvent);
    }

    public void explode() {
        for (Microblock microblock : getMicroblocks()) {
            ArmorStand stand = microblock.getStand();
            stand.setGravity(true);
            stand.setVelocity(Vector.getRandom().multiply(1));
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                remove();
            }
        }.runTaskLater(Main.getInstance(), 80);
    }

    public void fallApart() {
        for (Microblock microblock : getMicroblocks()) {
            microblock.getStand().setGravity(true);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                remove();
            }
        }.runTaskLater(Main.getInstance(), 50);
    }

    public void remove() {
        for (Microblock microblock : getMicroblocks()) {
            microblock.getStand().remove();
        }

        if (hasChildren()) for (Microstructure child : getChildren()) child.remove();

        microstructures.remove(this);

        MicrostructureDestroyEvent destroyEvent = new MicrostructureDestroyEvent(this);
        Main.getInstance().getServer().getPluginManager().callEvent(destroyEvent);
    }

    public String getName() { return name; }

    public UUID getUniqueId() { return uuid; }

    public void move(double x, double y, double z) {
        for (Microblock mb : getMicroblocks()) {
            ArmorStand stand = mb.getStand();
            stand.teleport(stand.getLocation().add(x, y, z));
        }
    }

    public Location getOrigin() {
        return origin;
    }

    public List<Microblock> getMicroblocks() {
        return microblocks;
    }

    public Microstructure getParent() {
        return parent;
    }

    public void setParent(Microstructure parent) {
        this.parent = parent;
        parent.addChild(this);
    }

    public List<Microstructure> getChildren() {
        return children;
    }

    public void addChild(Microstructure child) {
        children.add(child);
    }

    public boolean isChild() {
        return getParent() != null;
    }

    public boolean hasChildren() {
        return children.size() > 0;
    }

    public Location getCenter() {
        Location pos1 = getMicroblocks().get(0).getLocation();
        Location pos2 = getMicroblocks().get(getMicroblocks().size() - 1).getLocation();

        double x = (pos1.getX() + pos2.getX()) / 2;
        double y = (pos1.getY() + pos2.getY()) / 2;
        double z = (pos1.getZ() + pos2.getZ()) / 2;

        return new Location(pos1.getWorld(), x, y, z);
    }

    public Microblock getClosestTo(Location location) {
        Microblock closest = getMicroblocks().get(0);
        double distance = 99999;

        for (Microblock mb : getMicroblocks()) {
            double dist = mb.getLocation().distanceSquared(location);

            if (mb.getLocation().distanceSquared(location) < distance) {
                distance = dist;
                closest = mb;
            }
        }

        return closest;
    }

}
