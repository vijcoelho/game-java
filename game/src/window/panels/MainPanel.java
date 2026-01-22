package window.panels;

import window.GamePanelManager;

import javax.swing.*;
import java.awt.*;

public class MainPanel {

    public void startGame() {
        var window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Java 2D-RPG");

        var gamePanel = new GamePanelManager();
        gamePanel.requestFocusInWindow();

        window.add(gamePanel);
        window.setUndecorated(true);

        var gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(window);
        } else {
            window.setExtendedState(JFrame.MAXIMIZED_BOTH);
            window.setVisible(true);
        }

        gamePanel.startGameThread();
    }
}
