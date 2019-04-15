package us.blockcade.microstructures.util.schematic;

import org.bukkit.Location;
import org.bukkit.Material;
import us.blockcade.microstructures.Main;
import us.blockcade.microstructures.api.Microblock;
import us.blockcade.microstructures.api.Microstructure;
import us.blockcade.microstructures.util.BlockData;
import us.blockcade.microstructures.util.rotation.MSRotation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Schematic {

    private String name;
    private short[] blocks;
    private byte[] data;
    private short width;
    private short length;
    private short height;

    public Schematic(String name, short[] blocks, byte[] data, short width, short length, short height) {
        this.name = name;
        this.blocks = blocks;
        this.data = data;
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public short[] getBlocks() {
        return blocks;
    }

    public byte[] getData() {
        return data;
    }

    public short getWidth() {
        return width;
    }

    public short getLength() {
        return length;
    }

    public short getHeight() {
        return height;
    }

    public File getFile() {
        return new File(Main.getInstance().getDataFolder().getParent() + "/WorldEdit/schematics/" + getName() + ".schematic");
    }

    public List<BlockData> getBlockList() {
        List<BlockData> blockDataList = new ArrayList<>();

        for (int i = 0; i < blocks.length; i++) {
            short block = blocks[i];
            short value = data[i];

            blockDataList.add(new BlockData(Material.getMaterial(block), (byte) value));
        }

        return blockDataList;
    }

    public void pasteFlat(Location location) {
        Location loc = location.clone();

        for (BlockData bdata : getBlockList()) {
            loc.getBlock().setType(bdata.getMaterial());
            loc.getBlock().setData(bdata.getData());

            loc.add(1, 0, 0);
        }
    }

    public Microstructure buidMicrostructure(String name, Location location, MSRotation rotation) {
        Location loc = location.clone();
        double modifier = Microstructure.MicroSpacing.BLOCK.getValue();

        List<Microblock> microblocks = new ArrayList<>();

        int index = 0;
        for (int k = 0; k < getHeight(); k++) {
            for (int j = 0; j < getLength(); j++) {
                for (int i = 0; i < getWidth(); i++) {
                    if (blocks[index] != 0) {
                        BlockData blockData = new BlockData(Material.getMaterial(blocks[index]), data[index]);
                        microblocks.add(new Microblock(blockData, loc));
                    }

                    if (rotation.equals(MSRotation.NORTH)) loc.add(modifier, 0, 0);
                    if (rotation.equals(MSRotation.EAST)) loc.add(0, 0, modifier);
                    if (rotation.equals(MSRotation.SOUTH)) loc.add(modifier * -1, 0, 0);
                    if (rotation.equals(MSRotation.WEST)) loc.add(0, 0, modifier * -1);
                    index++;
                }

                if (rotation.equals(MSRotation.NORTH)) loc.add((getWidth() * modifier) * -1, 0, modifier);
                if (rotation.equals(MSRotation.EAST)) loc.add(modifier * -1, 0, (getWidth() * modifier) * -1);
                if (rotation.equals(MSRotation.SOUTH)) loc.add((getWidth() * modifier), 0, modifier * -1);
                if (rotation.equals(MSRotation.WEST)) loc.add(modifier, 0, (getWidth() * modifier));
            }

            if (rotation.equals(MSRotation.NORTH)) loc.add(0, modifier, (getLength() * modifier) * -1);
            if (rotation.equals(MSRotation.EAST)) loc.add((getLength() * modifier), modifier, 0);
            if (rotation.equals(MSRotation.SOUTH)) loc.add(0, modifier, (getLength() * modifier));
            if (rotation.equals(MSRotation.WEST)) loc.add((getWidth() * modifier) * -1, modifier, 0);
        }

        return new Microstructure(name, location, microblocks);
    }

}