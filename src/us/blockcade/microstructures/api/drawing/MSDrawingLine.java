package us.blockcade.microstructures.api.drawing;

import org.bukkit.Location;
import org.bukkit.Material;
import us.blockcade.microstructures.api.Microblock;
import us.blockcade.microstructures.api.Microstructure;
import us.blockcade.microstructures.util.BlockData;
import us.blockcade.microstructures.util.texture.BlockTexture;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MSDrawingLine {

    private String pixelMatrix;

    public MSDrawingLine(String pixelMatrix) {
        this.pixelMatrix = pixelMatrix;
    }

    public String getPixelMatrix() {
        return pixelMatrix;
    }

    public Microstructure build(String name, Map<String, BlockTexture> textures, Location location) {
        List<Microblock> contents = new ArrayList<>();

        String[] pixels = getPixelMatrix().split(" ");
        Location loc = location.clone();

        for (String pixel : pixels) {
            BlockTexture texture = BlockTexture.STONE;
            if (textures.containsKey(pixel)) texture = textures.get(pixel);

            if (!pixel.contains(".")) contents.add(new Microblock(texture, loc));
            loc.add(Microstructure.MicroSpacing.HEAD.getValue(), 0, 0);
        }

        return new Microstructure(name, location, contents);
    }

    public Microstructure build(String name, Map<String, BlockData> materials, Location location, boolean anything) {
        List<Microblock> contents = new ArrayList<>();

        String[] pixels = getPixelMatrix().split(" ");
        Location loc = location.clone();

        for (String pixel : pixels) {
            BlockData texture = new BlockData(Material.STONE, (byte) 0);
            if (materials.containsKey(pixel)) texture = materials.get(pixel);

            if (!pixel.contains(".")) contents.add(new Microblock(texture, loc));
            loc.add(Microstructure.MicroSpacing.BLOCK.getValue(), 0, 0);
        }

        return new Microstructure(name, location, contents);
    }

}
