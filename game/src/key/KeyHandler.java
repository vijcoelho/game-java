package key;

import window.GamePanelManager;
import window.enums.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private final GamePanelManager gamePanelManager;
    private final MenuKeyHandler menuKeyHandler;
    private final PauseKeyHandler pauseKeyHandler;

    public KeyHandler(GamePanelManager gamePanelManager) {
        this.gamePanelManager = gamePanelManager;
        this.menuKeyHandler = new MenuKeyHandler(gamePanelManager.getMenuPanel());
        this.pauseKeyHandler = new PauseKeyHandler(gamePanelManager);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (GameState.MENU.equals(gamePanelManager.getGameState())) {
            menuKeyHandler.keyPressed(e);
        }
        else if (GameState.PLAYING.equals(gamePanelManager.getGameState())) {
            pauseKeyHandler.keyPressed(e);

            if (GameState.PLAYING.equals(gamePanelManager.getGameState())) {
                gamePanelManager.getInGamePanel().getPlayerKeyH().keyPressed(e);
            }
        }
        else if (GameState.PAUSE.equals(gamePanelManager.getGameState())) {
            pauseKeyHandler.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (GameState.PLAYING.equals(gamePanelManager.getGameState())) {
            gamePanelManager.getInGamePanel().getPlayerKeyH().keyReleased(e);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

}
