package Inputs;

import com.mycompany.platformgame.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author mbarb
 */
public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getGame().getCharacter().setUp(true);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getCharacter().setLeft(true);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getCharacter().setDown(true);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getCharacter().setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                gamePanel.getGame().getCharacter().setJump(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getGame().getCharacter().setUp(false);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getCharacter().setLeft(false);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getCharacter().setDown(false);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getCharacter().setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                gamePanel.getGame().getCharacter().setJump(false);
                break;
        }
    }

}
