package Inputs;

import GameStates.Gamestate;
import static GameStates.Gamestate.MENU;
import static GameStates.Gamestate.PLAYING;
import com.mycompany.platformgame.GamePanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * This class that implements both the MouseListener and MouseMotionListener interfaces. It defines methods for handling mouse events such as clicks, presses, releases, and          * movements. It uses a GamePanel object and checks the current state of the game to determine which event handlers to call. If the game is in the PLAYING state, it calls the mouse  * event handlers for the playing state. If it is in the MENU state, it calls the mouse event handlers for the menu state.
 * @author mbarb
 */
public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    //Constructor that initializes a MouseInputs object with a GamePanel object, which is used to access the game's current state.

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (Gamestate.state) {
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseClicked(e);
                break;
            default:
                break;
        }
    }
    //Gets called when the mouse is clicked. It checks the current state of the game and calls the appropriate mouse click handler method for that state.

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().mousePressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;
            default:
                break;
        }
    }
    //Gets called when a mouse button is pressed down. It checks the current state of the game and calls the appropriate mouse press handler method for that state.

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseReleased(e);
                break;
            default:
                break;
        }
    }
    //Gets called when a mouse button is released. It checks the current state of the game and calls the appropriate mouse release handler method for that state.

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (Gamestate.state) {
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseDragged(e);
                break;
            default:
                break;
        }
    }
    //Gets called when the mouse is dragged (i.e. moved while holding down a button). It checks the current state of the game and calls the appropriate mouse drag handler method         for that state.

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseMoved(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseMoved(e);
                break;
            default:
                break;
        }
    }
    //Gets called when the mouse is moved (without holding down any button). It checks the current state of the game and calls the appropriate mouse move handler method for that         state.
}
