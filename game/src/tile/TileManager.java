package tile;

import images.TileImages;
import window.GamePanelManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    private final GamePanelManager gamePanelManager;
    private Tile[] tiles;
    private int[][] mapTileNum;

    public TileManager(GamePanelManager gamePanelManager) {
        this.gamePanelManager = gamePanelManager;
        tiles = new Tile[10];
        mapTileNum = new int[gamePanelManager.getMaxWorldCol()][gamePanelManager.getMaxWorldRow()];

        getTileImage();
        loadMap("/maps/map-001.txt");
    }

    public void getTileImage() {
        setupTileImage(0, TileImages.GRASS_001.getValue(), false);
        setupTileImage(1, TileImages.GRASS_002.getValue(), false);
        setupTileImage(2, TileImages.GRASS_003.getValue(), false);
        setupTileImage(3, TileImages.SAND_001.getValue(), false);
        setupTileImage(4, TileImages.SAND_002.getValue(), false);
        setupTileImage(5, TileImages.TREE_001.getValue(), false);
        setupTileImage(6, TileImages.WATER_001.getValue(), false);
        setupTileImage(7, TileImages.WATER_002.getValue(), false);
    }

    public void loadMap(String mapFile) {
        try {
            var inputStream = getClass().getResourceAsStream(mapFile);
            var bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while (col < gamePanelManager.getMaxWorldCol() && row < gamePanelManager.getMaxWorldRow()) {
                String line = bufferedReader.readLine();

                while (col < gamePanelManager.getMaxWorldCol()) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gamePanelManager.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        var player = gamePanelManager.getInGamePanel().getPlayer();
        int tileSize = gamePanelManager.getTileSize();

        int startCol = Math.max(0, (player.getWorldX() - player.getScreenX()) / tileSize - 1);
        int endCol = Math.min(
                gamePanelManager.getMaxWorldCol(),
                (player.getWorldX() - player.getScreenX() + gamePanelManager.getScreenWidth()) / tileSize + 2);

        int startRow = Math.max(0, (player.getWorldY() - player.getScreenY()) / tileSize - 1);
        int endRow = Math.min(
                gamePanelManager.getMaxWorldRow(),
                (player.getWorldY() - player.getScreenY() + gamePanelManager.getScreenHeight()) / tileSize + 2);

        for (int col = startCol; col < endCol; col++) {
            for (int row = startRow; row < endRow; row++) {
                int tileNum = mapTileNum[col][row];

                int worldX = col * tileSize;
                int worldY = row * tileSize;
                int screenX = worldX - player.getWorldX() + player.getScreenX();
                int screenY = worldY - player.getWorldY() + player.getScreenY();

                if (tiles[tileNum] != null) {
                    g2.drawImage(tiles[tileNum].getImage(), screenX, screenY, null);
                }
            }
        }
    }

    private BufferedImage setupTileImage(int index, String tilePath, boolean collision) {
        try {
            var image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(tilePath)));
            var scaledImage = new BufferedImage(gamePanelManager.getTileSize(), gamePanelManager.getTileSize(), BufferedImage.TYPE_INT_ARGB);
            var g2 = scaledImage.createGraphics();
            g2.drawImage(image, 0, 0, gamePanelManager.getTileSize(), gamePanelManager.getTileSize(), null);
            g2.dispose();

            tiles[index] = new Tile();
            tiles[index].setImage(scaledImage);
            tiles[index].setCollision(collision);
            return tiles[index].getImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

