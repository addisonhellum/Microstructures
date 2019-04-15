package us.blockcade.microstructures.api.preset;

import org.bukkit.Location;
import us.blockcade.microstructures.api.Microstructure;
import us.blockcade.microstructures.api.data.MRData;
import us.blockcade.microstructures.util.texture.BlockTexture;

public interface MicrostructurePreset {

    MRData[] getRelatives();
    Microstructure build(BlockTexture texture, Location location);
    boolean useOrigin();

}
