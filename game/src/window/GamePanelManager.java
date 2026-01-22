package window;

import key.KeyHandler;
import window.enums.GameState;
import window.panels.InGamePanel;
import window.panels.PausePanel;
import window.panels.StartMenuPanel;

import javax.swing.*;
import java.awt.*;

public class GamePanelManager extends JPanel implements Runnable {

    private final int originalTileSize = 16;
    private final int scale = 3;

    private final int tileSize = originalTileSize * scale;

    private final int maxScreenCol = 40;
    private final int maxScreenRow = 22;
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;

    private final int maxWorldCol = 10;
    private final int maxWorldRow = 10;
    private final int worldWidth = tileSize * maxWorldCol;
    private final int worldHeight = tileSize * maxWorldRow;

    private final StartMenuPanel menuPanel;
    private final InGamePanel inGamePanel;
    private final PausePanel pausePanel;

    private Thread gameThread;

    private GameState gameState = GameState.MENU;

    public GamePanelManager() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setOpaque(true);

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.menuPanel = new StartMenuPanel(this);
        this.inGamePanel = new InGamePanel(this);
        this.pausePanel = new PausePanel(this);

        this.addKeyListener(new KeyHandler(this));
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        switch (gameState) {
            case MENU -> menuPanel.update();
            case PLAYING -> inGamePanel.update();
            case PAUSE ->  pausePanel.update();
        }
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / 60;
        double renderInterval = 1000000000.0 / 180;

        double deltaUpdate = 0;
        double deltaRender = 0;

        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            timer += (currentTime - lastTime);

            deltaUpdate += (currentTime - lastTime) / drawInterval;
            deltaRender += (currentTime - lastTime) / renderInterval;
            lastTime = currentTime;

            if (deltaUpdate >= 1) {
                update();
                deltaUpdate--;
            }
            if (deltaRender >= 1) {
                repaint();
                Toolkit.getDefaultToolkit().sync();
                deltaRender--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

            try {
                long remainingTime = (long) ((renderInterval - (System.nanoTime() - lastTime)) / 1000000);
                if (remainingTime > 0) {
                    Thread.sleep(remainingTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        switch (gameState) {
            case MENU -> menuPanel.paintStartMenu(g2);
            case PLAYING -> inGamePanel.paintInGame(g2);
            case PAUSE -> {
                inGamePanel.paintInGame(g2);
                pausePanel.paintPause(g2);
            }
        }
        g2.dispose();
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public InGamePanel getInGamePanel() {
        return inGamePanel;
    }

    public StartMenuPanel getMenuPanel() {
        return menuPanel;
    }

    public PausePanel getPausePanel() {
        return pausePanel;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }
}
