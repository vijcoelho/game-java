package images;

public enum TileImages {
    GRASS_001("/sprites/tiles/grass-001.png"),
    GRASS_002("/sprites/tiles/grass-002.png"),
    GRASS_003("/sprites/tiles/grass-003.png"),
    SAND_001("/sprites/tiles/sand-001.png"),
    SAND_002("/sprites/tiles/sand-002.png"),
    TREE_001("/sprites/tiles/tree-001.png"),
    WATER_001("/sprites/tiles/water-001.png"),
    WATER_002("/sprites/tiles/water-002.png");

    private final String path;

    TileImages(String path) {
        this.path = path;
    }

    public String getValue() {
        return path;
    }
}
