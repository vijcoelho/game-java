package key;

import window.panels.StartMenuPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuKeyHandler implements KeyListener {

    private final StartMenuPanel menuPanel;

    public MenuKeyHandler(StartMenuPanel menuPanel) {
        this.menuPanel = menuPanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) menuPanel.navigate(-1);
        if (code == KeyEvent.VK_DOWN) menuPanel.navigate(1);
        if (code == KeyEvent.VK_ENTER) menuPanel.executeOption();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
