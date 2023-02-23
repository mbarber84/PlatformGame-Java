package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * This Java code defines an interface named "Statemethods" with several method signatures that define the behavior of a game state. Each implementation of the "Statemethods"         * interface will provide its own implementation of these methods to define its behavior as a game state.
 * 
 */
public interface Statemethods {
    
    public void update();//updates the state of the game.
    public void draw(Graphics g);//draws the state of the game on the screen using a graphics object.
    public void mouseClicked(MouseEvent e);//
    public void mousePressed(MouseEvent e);
    public void mouseReleased(MouseEvent e);
    public void mouseMoved(MouseEvent e);
    public void keyPressed(KeyEvent e);
    public void keyReleased(KeyEvent e);
}
