package us.blockcade.microstructures.api;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.EulerAngle;
import us.blockcade.microstructures.Main;
import us.blockcade.microstructures.util.BlockData;
import us.blockcade.microstructures.util.texture.BlockTexture;

import java.util.*;

public class Microblock implements Listener {

    private UUID uuid = UUID.randomUUID();
    private static Set<Microblock> microblocks = new HashSet<>();
    public static Set<Microblock> getMicroblocks() { return microblocks; }

    public static Microblock fromUUID(UUID uuid) {
        for (Microblock mb : getMicroblocks())
            if (mb.getUniqueId().equals(uuid)) return mb;

        return null;
    }

    private Material material;
    private Location location;

    private Microstructure structure;
    private ArmorStand stand;

    public Microblock(BlockData data, Location location) {
        this.location = location;

        Location newLoc = location.clone();
        newLoc.setYaw(0F);
        newLoc.setPitch(0F);

        this.stand = location.getWorld().spawn(newLoc, ArmorStand.class);
        stand.setGravity(false);
        stand.setBasePlate(false);
        stand.setVisible(false);
        stand.setSmall(true);

        stand.setHelmet(data.getItem());
        stand.setHeadPose(EulerAngle.ZERO);

        stand.setMetadata("Microblock", new FixedMetadataValue(Main.getInstance(), uuid.toString()));

        microblocks.add(this);
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    public Microblock(BlockTexture texture, Location location) {
        this.location = location;

        Location newLoc = location.clone();
        newLoc.setYaw(0F);
        newLoc.setPitch(0F);

        this.stand = location.getWorld().spawn(newLoc, ArmorStand.class);
        stand.setGravity(false);
        stand.setBasePlate(false);
        stand.setVisible(false);
        stand.setSmall(true);

        stand.setHelmet(texture.getAsSkull());
        stand.setHeadPose(EulerAngle.ZERO);

        stand.setMetadata("Microblock", new FixedMetadataValue(Main.getInstance(), uuid.toString()));

        microblocks.add(this);
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    public UUID getUniqueId() { return uuid; }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public ArmorStand getStand() {
        return stand;
    }

    public Location getLocation() {
        return getStand().getLocation();
    }

    public Microstructure getStructure() {
        return structure;
    }

    public void setStructure(Microstructure structure) {
        this.structure = structure;
    }

    public void remove() {
        stand.remove();
    }

}
