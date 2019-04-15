package us.blockcade.microstructures.util.schematic;

import net.minecraft.server.v1_8_R3.NBTCompressedStreamTools;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import us.blockcade.microstructures.Main;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class SchematicUtils {

    private static File baseSchematicsFile = new File(Main.getInstance().getDataFolder().getParent() + "/WorldEdit/schematics");

    private static List<Schematic> allSchematics = new ArrayList<Schematic>();

    public static void initSchematics(){
        allSchematics.clear();

        try {
            for (File file : baseSchematicsFile.listFiles()) {
                if (file.isDirectory()) {
                    for (File childFile : file.listFiles()) {
                        Schematic schematic = loadSchematic(childFile);
                        if (schematic != null) allSchematics.add(schematic);
                    }

                    Schematic schematic = loadSchematic(file);
                    if (schematic != null) allSchematics.add(schematic);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean doesExist(String name) {
        for(Schematic schem : getAllSchematics()) {
            if(schem.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    public static Schematic getByName(String name) {
        for(Schematic schem : getAllSchematics()) {
            if(schem.getName().equalsIgnoreCase(name)) return schem;
        }
        return null;
    }

    public static List<Schematic> getAllSchematics(){
        return allSchematics;
    }

    public static Schematic loadSchematic(String name) {
        if (!name.endsWith(".schematic"))
            name = name + ".schematic";

        return loadSchematic(new File(baseSchematicsFile + "/" + name));
    }

    public static Schematic loadSchematic(File file) {
        if (!file.exists()) return null;
        if (file.isDirectory()) return null;

        try {
            FileInputStream stream = new FileInputStream(file);
            NBTTagCompound nbtdata = NBTCompressedStreamTools.a(stream);

            short width = nbtdata.getShort("Width");
            short height = nbtdata.getShort("Height");
            short length = nbtdata.getShort("Length");

            byte[] blocks = nbtdata.getByteArray("Blocks");
            byte[] data = nbtdata.getByteArray("Data");

            byte[] addId = new byte[0];

            if (nbtdata.hasKey("AddBlocks")) {
                addId = nbtdata.getByteArray("AddBlocks");
            }

            short[] sblocks = new short[blocks.length];
            for (int index = 0; index < blocks.length; index++) {
                if ((index >> 1) >= addId.length) {
                    sblocks[index] = (short) (blocks[index] & 0xFF);
                } else {
                    if ((index & 1) == 0) {
                        sblocks[index] = (short) (((addId[index >> 1] & 0x0F) << 8) + (blocks[index] & 0xFF));
                    } else {
                        sblocks[index] = (short) (((addId[index >> 1] & 0xF0) << 4) + (blocks[index] & 0xFF));
                    }
                }
            }

            stream.close();
            return new Schematic(file.getName().replace(".schematic", ""), sblocks, data, width, length, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String displayArray(short[] array) {
        String builder = "[short][";

        for (short o : array)
            builder += o + ", ";

        return builder.substring(0, builder.length() - 2) + "]";
    }

    private static String displayArray(byte[] array) {
        String builder = "[byte][";

        for (short o : array)
            builder += o + ", ";

        return builder.substring(0, builder.length() - 2) + "]";
    }

    public static void displaySchematicData(String schematic) {
        Schematic schem = SchematicUtils.loadSchematic(schematic);
        System.out.println(" ");
        System.out.println(schem.getName() + " (H:" + schem.getHeight() + ", W:" + schem.getWidth() + ", L:" + schem.getLength()
                + ")");
        System.out.println("Blocks - " + displayArray(schem.getBlocks()));
        System.out.println("Data - " + displayArray(schem.getData()));
        System.out.println(" ");
    }

}