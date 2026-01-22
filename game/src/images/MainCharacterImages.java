package images;

public enum MainCharacterImages {
    NO_MOVEMENT("/sprites/character/walking/main-character-stop.png"),
    DOWN_1("/sprites/character/walking/down1.png"),
    DOWN_2("/sprites/character/walking/down2.png"),
    UP_1("/sprites/character/walking/up1.png"),
    UP_2("/sprites/character/walking/up2.png"),
    LEFT_1("/sprites/character/walking/left1.png"),
    LEFT_2("/sprites/character/walking/left2.png"),
    RIGHT_1("/sprites/character/walking/right1.png"),
    RIGHT_2("/sprites/character/walking/right2.png");

    private final String path;

    MainCharacterImages(String path) {
        this.path = path;
    }

    public String getValue() {
        return path;
    }
}
