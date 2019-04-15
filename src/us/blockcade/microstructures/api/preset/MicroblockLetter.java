package us.blockcade.microstructures.api.preset;

import org.bukkit.Location;
import us.blockcade.microstructures.api.Microblock;
import us.blockcade.microstructures.api.Microstructure;
import us.blockcade.microstructures.api.data.MRData;
import us.blockcade.microstructures.util.rotation.MSRotation;
import us.blockcade.microstructures.util.texture.BlockTexture;

import java.util.ArrayList;
import java.util.List;

public enum MicroblockLetter {

    A(0.3, true, new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(1, 0, 0),
            new MRData(1, 0, 0), new MRData(0, -1, 0),
            new MRData(0, -1, 0), new MRData(-1, 0, 0),
            new MRData(1, -1, 0)),
    B(0.3, true, new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(1, 0 ,0),
            new MRData(1, -0.5, 0), new MRData(-1, -1, 0),
            new MRData(1, -1, 0), new MRData(-1, -0.5, 0)),
    C(0.3, true, new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(1, 0, 0),
            new MRData(1, 0, 0), new MRData(0, -3, 0),
            new MRData(-1, 0, 0)),
    D(0.3, true, new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(1, 0, 0),
            new MRData(1, -1, 0), new MRData(0, -1, 0),
            new MRData(-1, -1, 0), new MRData(-1, 0, 0)),
    E(0.3, true, new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(1, 0, 0),
            new MRData(1, 0, 0), new MRData(0, -3, 0),
            new MRData(-1, 0, 0), new MRData(0, 1.5, 0)),
    F(0.3, true, new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(1, 0, 0),
            new MRData(1, 0, 0), new MRData(-1, -1.5, 0)),
    G(0.3, true, new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(1, 0, 0),
            new MRData(1, 0, 0), new MRData(0, -2, 0),
            new MRData(0, -1, 0), new MRData(-1, 0, 0)),
    H(0.3, true, new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(2, 0, 0),
            new MRData(0, -1, 0), new MRData(0, -1, 0),
            new MRData(0, -1, 0), new MRData(-1, 1.5, 0)),
    I(0.3, true, new MRData(1, 0, 0), new MRData(1, 0, 0),
            new MRData(-1, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0),
            new MRData(1, 0, 0), new MRData(-2, 0, 0)),
    J(0.3, true, new MRData(0, 1, 0), new MRData(1, -1, 0),
            new MRData(1, 0, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(0, 1, 0)),
    K(0.3, true, new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(1, -1.5, 0),
            new MRData(1, 1, 0), new MRData(0, 0.5, 0),
            new MRData(0, -2.5, 0), new MRData(0, -0.5, 0)),
    L(0.3, true, new MRData(1, 0, 0), new MRData(1, 0, 0),
            new MRData(-2, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0)),
    M(0.7, true, new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(1, 0, 0),
            new MRData(1, -1, 0), new MRData(1, 1, 0),
            new MRData(1, 0, 0), new MRData(0, -1, 0),
            new MRData(0, -1, 0), new MRData(0, -1, 0)),
    N(0.3, true, new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(1, 0, 0),
            new MRData(1, 0, 0), new MRData(0, -1, 0),
            new MRData(0, -1, 0), new MRData(0, -1, 0)),
    O(0.3, true, new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(1, 0, 0),
            new MRData(1, 0, 0), new MRData(0, -1, 0),
            new MRData(0, -1, 0), new MRData(0, -1, 0),
            new MRData(-1, 0, 0)),
    P(0.3, true, new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(1, 0, 0),
            new MRData(1, 0, 0), new MRData(0, -1, 0),
            new MRData(0, -1, 0), new MRData(-1, 0, 0)),
    Q(0.3, true, new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(1, 0, 0),
            new MRData(1, 0, 0), new MRData(0, -1, 0),
            new MRData(0, -1, 0), new MRData(-1, -1, 0)),
    R(0.3, true, new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(1, 0, 0),
            new MRData(1, 0, 0), new MRData(0, -1, 0),
            new MRData(-1, -1, 0), new MRData(1, -1, 0)),
    S(0.3, true, new MRData(1, 0, 0), new MRData(1, 0, 0),
            new MRData(0, 1, 0), new MRData(-1, 0.5, 0),
            new MRData(-1, 0.5, 0), new MRData(0, 1, 0),
            new MRData(1, 0, 0), new MRData(1, 0, 0)),
    T(0.3, false, new MRData(1, 0, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(1, 0, 0), new MRData(-2, 0, 0)),
    U(0.3, true, new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(2, 0, 0),
            new MRData(0, -1, 0), new MRData(0, -1, 0),
            new MRData(0, -1, 0), new MRData(-1, 0, 0)),
    V(0.3, false, new MRData(1, 0, 0), new MRData(-1, 1, 0),
            new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(2, 0, 0), new MRData(0, -1, 0),
            new MRData(0, -1, 0)),
    W(0.7, true, new MRData(0, 1, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(1, -3, 0),
            new MRData(1, 1, 0), new MRData(1, -1, 0),
            new MRData(1, 0, 0), new MRData(0, 1, 0),
            new MRData(0, 1, 0), new MRData(0, 1, 0)),
    X(0.3, true, new MRData(0, 0.5, 0), new MRData(1, 0.5, 0),
            new MRData(0, 1, 0), new MRData(-1, 0.5, 0),
            new MRData(0, 0.5, 0), new MRData(2, 0, 0),
            new MRData(0, -0.5, 0), new MRData(0, -2, 0),
            new MRData(0, -0.5, 0)),
    Y(0.3, false, new MRData(1, 0, 0), new MRData(0, 1, 0),
            new MRData(1, 1, 0), new MRData(0, 1, 0),
            new MRData(-2, 0, 0), new MRData(0, -1, 0)),
    Z(0.3, true, new MRData(1, 0, 0), new MRData(1, 0, 0),
            new MRData(-2, 1, 0), new MRData(1, 0.5, 0),
            new MRData(1, 0.5, 0), new MRData(0, 1, 0),
            new MRData(-1, 0, 0), new MRData(-1, 0, 0)),
    QUESTION_MARK(0.4, false, new MRData(1, -0.5, 0), new MRData(0, 1.5, 0),
            new MRData(1, 1, 0), new MRData(0, 1, 0),
            new MRData(-1, 0.5, 0), new MRData(-1, -0.5, 0)),
    EXCLAMATION_POINT(0.2, false, new MRData(1, 0, 0), new MRData(0, 2, 0),
            new MRData(0, 1, 0)),
    PLUS(0.3, false, new MRData(1, 0.5, 0), new MRData(-1, 1, 0),
            new MRData(1, 0, 0), new MRData(1, 0, 0),
            new MRData(-1, 1, 0)),
    DASH(0.3, false, new MRData(0, 1, 0), new MRData(1, 0, 0),
            new MRData(1, 0, 0)),
    SLASH(0.45, true, new MRData(1, 1, 0), new MRData(0, 1, 0),
            new MRData(1, 1, 0)),
    PERIOD(0.3, false, new MRData(1, 0, 0)),
    COLON(0.3, false, new MRData(1, 1, 0), new MRData(0, 2, 0)),
    SPACE(0.2, false);

    private double width;
    private boolean origin;
    private MRData[] relatives;

    MicroblockLetter(double width, boolean origin, MRData... relatives) {
        this.width = width;
        this.origin = origin;
        this.relatives = relatives;
    }

    double spacing = Microstructure.MicroSpacing.HEAD.getValue();

    public double getWidth() {
        return width + spacing;
    }

    public Microstructure build(BlockTexture texture, Location location, MSRotation rotation) {
        List<Microblock> contents = new ArrayList<>();
        Location loc = location.clone();

        if (relatives.length > 0) {
            if (origin) contents.add(new Microblock(texture, loc));

            for (MRData data : relatives) {
                contents.add(new Microblock(texture, data.fromLocation(Microstructure.MicroSpacing.HEAD, loc, rotation)));
            }
        }

        if (rotation.equals(MSRotation.NORTH)) location.add(getWidth(), 0, 0);
        if (rotation.equals(MSRotation.EAST)) location.add(0, 0, getWidth());
        if (rotation.equals(MSRotation.SOUTH)) location.subtract(getWidth(), 0, 0);
        if (rotation.equals(MSRotation.WEST)) location.subtract(0, 0, getWidth());

        return new Microstructure(name(), location, contents);
    }

    public Microstructure build(BlockTexture texture, Location location) {
        return build(texture, location, MSRotation.NORTH);
    }

    public static MicroblockLetter fromText(String text) {
        text = text.substring(0, 1);

        if (text.equals(" ")) return MicroblockLetter.SPACE;
        if (text.equals("?")) return MicroblockLetter.QUESTION_MARK;
        if (text.equals("!")) return MicroblockLetter.EXCLAMATION_POINT;
        if (text.equals(".")) return MicroblockLetter.PERIOD;
        if (text.equals("+")) return MicroblockLetter.PLUS;
        if (text.equals("-")) return MicroblockLetter.DASH;
        if (text.equals("/")) return MicroblockLetter.SLASH;
        if (text.equals(":")) return MicroblockLetter.COLON;

        for (MicroblockLetter letter : MicroblockLetter.values())
            if (letter.name().equalsIgnoreCase(text)) return letter;

        return MicroblockLetter.valueOf(text);
    }

    public static Microstructure buildText(Location location, BlockTexture texture, String text) {
        List<Microstructure> contents = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            String c = Character.toString(text.charAt(i)).toUpperCase();

            MicroblockLetter letter = fromText(c);
            if (letter == null) letter = MicroblockLetter.SPACE;

            MSRotation rotation = MSRotation.getRotation(location.getYaw());
            contents.add(letter.build(texture, location, rotation));

            if (rotation.equals(MSRotation.NORTH)) location.add(letter.getWidth(), 0, 0);
            if (rotation.equals(MSRotation.EAST)) location.add(0, 0, letter.getWidth());
            if (rotation.equals(MSRotation.SOUTH)) location.subtract(letter.getWidth(), 0, 0);
            if (rotation.equals(MSRotation.WEST)) location.subtract(0, 0, letter.getWidth());
        }

        return new Microstructure(text, contents);
    }

}
