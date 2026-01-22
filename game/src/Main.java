import window.panels.MainPanel;

void main() {
    System.setProperty("sun.java2d.opengl", "true");
    System.setProperty("sun.java2d.noddraw", "true");

    var mainPanel = new MainPanel();
    mainPanel.startGame();
}
