package window.panels;

import utils.LoadFontCustom;
import window.GamePanelManager;
import window.enums.GameState;

import java.awt.*;

public class StartMenuPanel {

    private final GamePanelManager gamePanelManager;
    private int selectedOption = 0;

    private final int midX;
    private final int midY;

    private final Font titleFont;
    private final Font creditsFont;
    private final Font optionsFont;

    public StartMenuPanel(GamePanelManager gamePanelManager) {
        this.gamePanelManager = gamePanelManager;

        this.midX = gamePanelManager.getScreenWidth() / 2;
        this.midY = gamePanelManager.getScreenHeight() / 2;

        this.titleFont = LoadFontCustom.loadCustomFont("/fonts/PressStart2P-Regular.ttf", 40);
        this.creditsFont = LoadFontCustom.loadCustomFont("/fonts/PressStart2P-Regular.ttf", 10);
        this.optionsFont = LoadFontCustom.loadCustomFont("/fonts/PressStart2P-Regular.ttf", 25);
    }

    public void update() {
    }

    public void paintStartMenu(Graphics2D g2) {
        drawTitle(g2);
        drawCredits(g2);
        drawOptions(g2);
    }

    public void navigate(int direction) {
        selectedOption += direction;

        int totalOptions = 3;
        if (selectedOption < 0) {
            selectedOption = totalOptions - 1;
        } else if (selectedOption >= totalOptions) {
            selectedOption = 0;
        }
    }

    public void executeOption() {
        switch (selectedOption) {
            case 0 -> {
                gamePanelManager.setGameState(GameState.PLAYING);
                System.out.println("Starting new game...");
            }
            case 1 -> System.out.println("Continuing game...");
            case 2 -> {
                System.out.println("Exiting game...");
                System.exit(0);
            }
        }
    }

    private void drawTitle(Graphics2D g2) {
        g2.setColor(new Color(255, 200, 0));
        g2.setFont(titleFont);

        var fm = g2.getFontMetrics();
        int height = fm.getHeight();

        String title = "Java 2D-RPG Game";
        int titleWidth = fm.stringWidth(title);
        g2.drawString(title, midX - (titleWidth / 2) , midY - height * 3);
    }

    private void drawCredits(Graphics2D g2) {
        g2.setColor(new Color(240, 240, 240));
        g2.setFont(creditsFont);

        var fm = g2.getFontMetrics();
        int height = fm.getHeight();

        String creator = "Creator: Vitor José Coelho";
        int creatorWidth = fm.stringWidth(creator);
        g2.drawString(creator, gamePanelManager.getScreenWidth() - creatorWidth - 10 , gamePanelManager.getScreenHeight() - (height * 3));

        String date = "Created at: 21/01/2026";
        int dateWidth = fm.stringWidth(date);
        g2.drawString(date, gamePanelManager.getScreenWidth() - dateWidth - 10, gamePanelManager.getScreenHeight() - height);
    }

    private void drawOptions(Graphics2D g2) {
        g2.setColor(new Color(240, 240, 240));
        g2.setFont(optionsFont);

        var fm = g2.getFontMetrics();
        int height = fm.getHeight();

        String[] options = {"New game", "Continue", "Exit"};

        for (int i = 0; i < options.length; i++) {
            int optionWidth = fm.stringWidth(options[i]);
            int yPosition = midY - (height / 2) + height * (2 + i * 2);

            if (i == selectedOption) {
                g2.setColor(Color.YELLOW);
                String arrow = "> ";
                int arrowWidth = fm.stringWidth(arrow);
                g2.drawString(arrow, midX - (optionWidth / 2) - arrowWidth - 10, yPosition);
            } else {
                g2.setColor(Color.WHITE);
            }

            g2.drawString(options[i], midX - (optionWidth / 2), yPosition);
        }
    }
}
