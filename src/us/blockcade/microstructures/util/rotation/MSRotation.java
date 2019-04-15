package us.blockcade.microstructures.util.rotation;

public enum MSRotation {

    NORTH(1, 1),
    SOUTH(-1, 1),
    EAST(1, 1),
    WEST(1, -1);

    private double x;
    private double z;

    MSRotation(double xMult, double zMult) {
        this.x = xMult;
        this.z = zMult;
    }

    public double getXMultiplier() {
        return x;
    }

    public double getZMultiplier() {
        return z;
    }

    public static MSRotation getRotation(float yaw) {
        int degrees = (Math.round(yaw) + 360) % 360;

        if (degrees <= 56) return MSRotation.SOUTH;
        if (degrees <= 135) return MSRotation.WEST;
        if (degrees <= 225) return MSRotation.NORTH;
        if (degrees <= 315) return MSRotation.EAST;
        if (degrees <= 359) return MSRotation.SOUTH;

        return null;
    }

}
