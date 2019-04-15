package us.blockcade.microstructures.api.data;

import org.bukkit.Location;
import us.blockcade.microstructures.api.Microstructure;
import us.blockcade.microstructures.util.rotation.MSRotation;

public class MRData {

    private double vertical;
    private double horizontal;
    private double extrude;

    public MRData(double horizontal, double vertical, double extrude) {
        this.vertical = vertical;
        this.horizontal = horizontal;
        this.extrude = extrude;
    }

    public double getVertical() {
        return vertical;
    }

    public double getHorizontal() {
        return horizontal;
    }

    public double getExtrude() {
        return extrude;
    }

    public Location fromLocation(Microstructure.MicroSpacing space, Location location, MSRotation rotation) {
        double spacing = space.getValue();

        if (rotation.equals(MSRotation.EAST))
            return location.add(spacing * extrude, spacing * vertical, spacing * horizontal);
        if (rotation.equals(MSRotation.SOUTH))
            return location.add((spacing * horizontal) * -1, spacing * vertical, spacing * extrude);
        if (rotation.equals(MSRotation.WEST))
            return location.add(spacing * extrude, spacing * vertical, (spacing * horizontal) * -1);

        else return location.add(spacing * horizontal, spacing * vertical, spacing * extrude);
    }

    public Location fromLocation(Microstructure.MicroSpacing space, Location location) {
        return fromLocation(space, location, MSRotation.NORTH);
    }

}
