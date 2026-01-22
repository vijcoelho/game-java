package window.panels;

import utils.LoadFontCustom;
import window.GamePanelManager;
import window.enums.GameState;

import java.awt.*;

public class PausePanel {

    private final GamePanelManager gamePanelManager;
    private StartMenuPanel startMenuPanel;

    private int selectedOption = 0;

    private final Font titleFont;
    private final Font optionsFont;

    public PausePanel(GamePanelManager gamePanelManager) {
        this.gamePanelManager = gamePanelManager;
        this.startMenuPanel = new StartMenuPanel(gamePanelManager);

        this.titleFont = LoadFontCustom.loadCustomFont("/fonts/PressStart2P-Regular.ttf", 40);
        this.optionsFont = LoadFontCustom.loadCustomFont("/fonts/PressStart2P-Regular.ttf", 25);
    }

    public void update() {}

    public void paintPause(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gamePanelManager.getScreenWidth(), gamePanelManager.getScreenHeight());

        drawTitle(g2);
        drawOptions(g2);
    }

    public void navigate(int direction) {
        selectedOption += direction;

        int totalOptions = 2;
        if (selectedOption < 0) {
            selectedOption = totalOptions - 1;
        } else if (selectedOption >= totalOptions) {
            selectedOption = 0;
        }
    }

    public void execute() {
        switch (selectedOption) {
            case 0 -> gamePanelManager.setGameState(GameState.PLAYING);
            case 1 -> gamePanelManager.setGameState(GameState.MENU);
        }
    }

    private void drawTitle(Graphics2D g2) {
        g2.setColor(new Color(255, 200, 0));
        g2.setFont(titleFont);

        String title = "Pause";
        g2.drawString(title, getXforCenteredText(title, g2), 200);
    }

    private void drawOptions(Graphics2D g2) {
        g2.setFont(optionsFont);

        String[] options = {"Resume", "Quit to Menu"};
        int spacing = 300;
        int totalWidth = (options.length - 1) * spacing;
        int startX = (gamePanelManager.getScreenWidth() / 2) - (totalWidth / 2) - 115;
        int y = 400;

        for (int i = 0; i < options.length; i++) {
            int currentX = startX + (i * spacing);
            if (i == selectedOption) {
                g2.setColor(Color.YELLOW);
                g2.drawString(">", currentX - 40, y);
            } else {
                g2.setColor(Color.WHITE);
            }
            g2.drawString(options[i], currentX, y);
        }
    }

    private int getXforCenteredText(String text, Graphics2D g2) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gamePanelManager.getScreenWidth() / 2 - length / 2;
    }
}
