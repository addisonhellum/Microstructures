package us.blockcade.microstructures.api.drawing;

import org.bukkit.Location;
import org.bukkit.Material;
import us.blockcade.microstructures.api.Microstructure;
import us.blockcade.microstructures.util.texture.BlockTexture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MSDrawing {

    private String name;
    private MSDrawingLine[] lines;
    private Map<String, BlockTexture> symbols = new HashMap<>();

    public MSDrawing(String name, MSDrawingLine[] lines) {
        this.name = name;
        this.lines = lines;
    }

    public String getName() { return name; }

    public MSDrawingLine[] getLines() {
        return lines;
    }

    public Microstructure build(BlockTexture texture, Location location) {
        List<Microstructure> contents = new ArrayList<>();

        Location loc = location.clone();
        loc.add(0, Microstructure.MicroSpacing.HEAD.getValue() * getLines().length, 0);

        for (int i = 0; i < getLines().length; i++) {
            MSDrawingLine line = getLines()[i];

            contents.add(line.build(getName() + i, symbols, loc));
            loc.subtract(0, Microstructure.MicroSpacing.HEAD.getValue(), 0);
        }

        return new Microstructure(getName(), contents);
    }

    public Microstructure build(Material material, Location location) {
        List<Microstructure> contents = new ArrayList<>();

        Location loc = location.clone();
        loc.add(0, Microstructure.MicroSpacing.BLOCK.getValue() * getLines().length, 0);

        for (int i = 0; i < getLines().length; i++) {
            MSDrawingLine line = getLines()[i];

            contents.add(line.build(getName() + i, symbols, loc));
            loc.subtract(0, Microstructure.MicroSpacing.BLOCK.getValue(), 0);
        }

        return new Microstructure(getName(), contents);
    }

    public MSDrawing setTexture(String symbol, BlockTexture texture) {
        symbols.put(symbol, texture);
        return this;
    }

}
