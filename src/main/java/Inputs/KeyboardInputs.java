package Inputs;

import GameStates.Gamestate;
import static GameStates.Gamestate.MENU;
import static GameStates.Gamestate.PLAYING;
import com.mycompany.platformgame.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This Java code defines a class named KeyboardInputs that implements the KeyListener interface. Its constructor takes a GamePanel object as a parameter, and it overrides three     * methods of the KeyListener interface: keyTyped(), keyPressed(), and keyReleased(). The keyPressed() and keyReleased() methods use a switch statement to call methods in the        * Gamestate class based on the current state of the game (either MENU or PLAYING).
 * 
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
        switch(Gamestate.state){
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            default:
                break;
        }
    }
    //Inside the method, there is a switch statement that checks the value of a Gamestate variable. Depending on the value, the method calls the keyPressed() method of either the         Menu or Playing object contained in a GamePanel object.

    @Override
    public void keyReleased(KeyEvent e) {
        switch(Gamestate.state){
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            default:
                break;
        }
    }
    // The method uses a switch statement to determine the current state of the game and call the keyReleased() method of the appropriate object based on the current state.
}
