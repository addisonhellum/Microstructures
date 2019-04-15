package us.blockcade.microstructures.util.texture;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum BlockTexture {

    STONE("eyJ0aW1lc3RhbXAiOjE1MzA0MTQyNzM5ODAsInByb2ZpbGVJZCI6IjcyMzcxYWRmMmU4ZDRjOTJhNzM0YTkzYzgyNzlkZWI5IiwicHJvZmls" +
            "ZU5hbWUiOiJTdG9uZSIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dX" +
            "Jlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTZkYTczNWM4OGUwOGI4MGJkZjgxNDBiMGFkZDhkMTg2OTBiODViNGY5MDI1YmI4ODFkN2Ex" +
            "OTEzNDBmM2IzMSJ9fX0=", Material.STONE, 0),
    RED_WOOL("eyJ0aW1lc3RhbXAiOjE1MzA2NjcxNzU1NDIsInByb2ZpbGVJZCI6IjU1OTE5ZTMxZDEzZTQ1Mzg4ZDY0YzgwMDA5ZTI3ZjdmIiwicHJvZ" +
            "mlsZU5hbWUiOiJLYXp5S2F6b28iLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8" +
            "vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQzNGQ2NmVjOGQ1NTMwNmFkNjQyNzFmNTg3NWQxYzliNTIyOTVjYTA4MjUzMGRjO" +
            "WEzZTNiMmM0MGJjMzg5ZDgifX19", Material.WOOL, 14),
    BLUE_WOOL("eyJ0aW1lc3RhbXAiOjE1MzA2Njc5NzIxMzQsInByb2ZpbGVJZCI6IjU1OTE5ZTMxZDEzZTQ1Mzg4ZDY0YzgwMDA5ZTI3ZjdmIiwicHJv" +
            "ZmlsZU5hbWUiOiJLYXp5S2F6b28iLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi" +
            "8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ZiYTExNDUyYjg0MGRkNGQwZDQxMmZkMzk3OTViZTUzNDQwZGI1ZjU3MzhhN2Fl" +
            "MmI4NmRmMmRiNWZkYTQyZmQifX19", Material.WOOL, 11),
    WHITE("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzY2YTVjOTg5MjhmYTVk" +
            "NGI1ZDViOGVmYjQ5MDE1NWI0ZGRhMzk1NmJjYWE5MzcxMTc3ODE0NTMyY2ZjIn19fQ==", Material.WOOL, 0);

    private String val;
    private Material mat;
    private int data;

    BlockTexture(String value, Material material, int data) {
        this.val = value;
        this.mat = material;
        this.data = data;
    }

    public String getTextureValue() {
        return this.val;
    }

    public Material getMaterial() {
        return mat;
    }

    public ItemStack getAsSkull() {
        return SkullCreator.fromBase64(SkullCreator.Type.ITEM, getTextureValue());
    }

    public static BlockTexture fromMaterial(Material material) {
        try {
            return BlockTexture.valueOf(material.name());
        } catch (NullPointerException e) {
            for (BlockTexture bt : BlockTexture.values()) {
                if (bt.getMaterial().equals(material)) return bt;
            }

            return null;
        }
    }

}
