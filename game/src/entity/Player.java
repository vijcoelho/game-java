package entity;

import images.MainCharacterImages;
import key.PlayerKeyHandler;
import window.GamePanelManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    private final GamePanelManager gamePanelManager;
    private final PlayerKeyHandler keyH;

    private final int screenX;
    private final int screenY;

    public Player(GamePanelManager gamePanelManager,
                  PlayerKeyHandler playerKeyHandler) {
        this.gamePanelManager = gamePanelManager;
        this.keyH = playerKeyHandler;

        this.screenX = gamePanelManager.getScreenWidth() / 2 - (gamePanelManager.getTileSize() / 2);
        this.screenY = gamePanelManager.getScreenHeight() / 2 - (gamePanelManager.getTileSize() / 2);

        setDefaultValues();
        getPlayerImages();
    }

    public void update() {
        if (keyH.isUpPressed() || keyH.isDownPressed() || keyH.isLeftPressed() || keyH.isRightPressed()) {
            if (keyH.isUpPressed()) {
                setWorldY(getWorldY() - getSpeed());
                setDirection("up");
            } else if (keyH.isDownPressed()) {
                setWorldY(getWorldY() + getSpeed());
                setDirection("down");
            } else if (keyH.isLeftPressed()) {
                setWorldX(getWorldX() - getSpeed());
                setDirection("left");
            } else if (keyH.isRightPressed()) {
                setWorldX(getWorldX() + getSpeed());
                setDirection("right");
            }
        } else {
            setDirection("stop");
        }

        setSpriteCounter(getSpriteCounter() + 1);
        if (getSpriteCounter() > 12) {
            if (getSpriteNum() == 1) setSpriteNum(2);
            else if (getSpriteNum() == 2) setSpriteNum(1);
            setSpriteCounter(0);
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (getDirection()) {
            case "up" -> {
                if (getSpriteNum() == 1) image = getUp1();
                if (getSpriteNum() == 2) image = getUp2();
            }
            case "down" -> {
                if (getSpriteNum() == 1) image = getDown1();
                if (getSpriteNum() == 2) image = getDown2();
            }
            case "left" -> {
                if (getSpriteNum() == 1) image = getLeft1();
                if (getSpriteNum() == 2) image = getLeft2();
            }
            case "right" -> {
                if (getSpriteNum() == 1) image = getRight1();
                if (getSpriteNum() == 2) image = getRight2();
            }
            case "stop" -> image = getStop();
        }
        g2.drawImage(image, screenX, screenY, null);
    }

    public void setDefaultValues() {
        setWorldX(gamePanelManager.getTileSize() * (gamePanelManager.getMaxWorldCol() / 2));
        setWorldY(gamePanelManager.getTileSize() * (gamePanelManager.getMaxWorldRow() / 2));
        setSpeed(4);
        setDirection("down");
    }

    public void getPlayerImages() {
        setStop(setup(MainCharacterImages.NO_MOVEMENT.getValue()));
        setUp1(setup(MainCharacterImages.UP_1.getValue()));
        setUp2(setup(MainCharacterImages.UP_2.getValue()));
        setDown1(setup(MainCharacterImages.DOWN_1.getValue()));
        setDown2(setup(MainCharacterImages.DOWN_2.getValue()));
        setLeft1(setup(MainCharacterImages.LEFT_1.getValue()));
        setLeft2(setup(MainCharacterImages.LEFT_2.getValue()));
        setRight1(setup(MainCharacterImages.RIGHT_1.getValue()));
        setRight2(setup(MainCharacterImages.RIGHT_2.getValue()));
    }

    private BufferedImage setup(String imagePath) {
        try {
            var image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            var scaledImage = new BufferedImage(gamePanelManager.getTileSize(), gamePanelManager.getTileSize(), image.getType());
            var g2 = scaledImage.createGraphics();
            g2.drawImage(image, 0, 0, gamePanelManager.getTileSize(), gamePanelManager.getTileSize(), null);
            g2.dispose();
            return scaledImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }
}
