package window.panels;

import entity.Player;
import key.PlayerKeyHandler;
import tile.TileManager;
import window.GamePanelManager;

import java.awt.*;

public class InGamePanel {

    private final GamePanelManager gamePanelManager;
    private final Player player;
    private final PlayerKeyHandler playerKeyH;
    private final TileManager tileManager;

    public InGamePanel(GamePanelManager gamePanelManager) {
        this.gamePanelManager = gamePanelManager;

        this.playerKeyH = new PlayerKeyHandler();
        this.player = new Player(gamePanelManager, playerKeyH);
        this.tileManager = new TileManager(gamePanelManager);
    }

    public void update() {
        player.update();
    }

    public void paintInGame(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gamePanelManager.getScreenWidth(), gamePanelManager.getScreenHeight());

        tileManager.draw(g2);
        player.draw(g2);
    }

    public PlayerKeyHandler getPlayerKeyH() {
        return playerKeyH;
    }

    public Player getPlayer() {
        return player;
    }
}
