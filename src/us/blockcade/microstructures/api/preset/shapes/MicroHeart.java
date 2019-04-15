package us.blockcade.microstructures.api.preset.shapes;

import org.bukkit.Location;
import us.blockcade.microstructures.api.Microblock;
import us.blockcade.microstructures.api.Microstructure;
import us.blockcade.microstructures.api.data.MRData;
import us.blockcade.microstructures.api.preset.MicrostructurePreset;
import us.blockcade.microstructures.util.texture.BlockTexture;

import java.util.ArrayList;
import java.util.List;

public class MicroHeart implements MicrostructurePreset {

    public MRData[] getRelatives() {
        return new MRData[] {
                new MRData(0, 1, 0), new MRData(1, 0, 0),
                new MRData(-2, 0, 0), new MRData(-1, 1, 0),
                new MRData(1, 0, 0), new MRData(1, 0, 0),
                new MRData(1, 0, 0), new MRData(1, 0, 0),
                new MRData(1, 1, 0), new MRData(-1, 0, 0),
                new MRData(-1, 0, 0), new MRData(-1, 0, 0),
                new MRData(-1, 0, 0), new MRData(-1, 0, 0),
                new MRData(-1, 0, 0), new MRData(0, 1, 0),
                new MRData(1, 0, 0), new MRData(1, 0, 0),
                new MRData(1, 0, 0), new MRData(1, 0, 0),
                new MRData(1, 0, 0), new MRData(1, 0, 0),
                new MRData(-1, 1, 0), new MRData(-1, 0, 0),
                new MRData(-2, 0, 0), new MRData(-1, 0, 0)
        };
    }

    public boolean useOrigin() { return true; }

    public Microstructure build(BlockTexture texture, Location location) {
        List<Microblock> contents = new ArrayList<>();
        Location loc = location.clone();

        if (getRelatives().length > 0) {
            if (useOrigin()) contents.add(new Microblock(texture, loc));

            for (MRData data : getRelatives()) {
                contents.add(new Microblock(texture, data.fromLocation(Microstructure.MicroSpacing.HEAD, loc)));
            }
        }

        return new Microstructure("Heart", location, contents);
    }

}
