package key;

import window.GamePanelManager;
import window.enums.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PauseKeyHandler implements KeyListener {

    private final GamePanelManager gamePanelManager;

    public PauseKeyHandler(GamePanelManager gamePanelManager) {
        this.gamePanelManager = gamePanelManager;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        var gameState = gamePanelManager.getGameState();

        if (gameState == GameState.PLAYING) {
            if (code == KeyEvent.VK_ESCAPE) {
                gamePanelManager.setGameState(GameState.PAUSE);
            }
        } else if (gameState == GameState.PAUSE) {
            if (code == KeyEvent.VK_RIGHT) {
                gamePanelManager.getPausePanel().navigate(1);
            }
            if (code == KeyEvent.VK_LEFT) {
                gamePanelManager.getPausePanel().navigate(-1);
            }
            if (code == KeyEvent.VK_ENTER) {
                gamePanelManager.getPausePanel().execute();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
