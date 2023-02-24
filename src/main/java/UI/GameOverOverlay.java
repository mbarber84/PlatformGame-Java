package UI;

import GameStates.Gamestate;
import GameStates.Playing;
import com.mycompany.platformgame.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


/**
 * class GameOverOverlay provides methods to draw the game over screen and handle the escape key press event. It takes an instance of the Playing class as input, which is used to    * reset the game state and return to the menu. The draw method draws a semi-transparent red rectangle as the background and displays two messages in green colour on top.
 *
 */
public class GameOverOverlay {
    
    private Playing playing;
    public GameOverOverlay(Playing playing){
        this.playing = playing;
    }
    // a constructor that takes an instance of the Playing class as input and assigns it to the playing field.
    
    public void draw(Graphics g){
        g.setColor(new Color(69,18,8, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        g.setColor(new Color(102,255,0));
        g.drawString("Arrr, ye be done for matey! Game over!!", Game.GAME_WIDTH / 3, 150);
        g.drawString("Ahoy there! If ye be wantin' to return to the main menu, just give that escape key a good press, ye hear!", Game.GAME_WIDTH / 3, 300);
    }
    //draws the game over screen. It fills the background with a semi-transparent red rectangle and displays two strings of text in green colour.
    
    public void KeyPresses(KeyEvent e){
        if(e.getKeyCode()== KeyEvent.VK_ESCAPE){
            playing.resetAll();
            Gamestate.state = Gamestate.MENU;
        }
    }
    //a method that handles the escape key press event. If the escape key is pressed, it calls the resetAll() method of the Playing instance assigned to playing, and sets the game state to MENU using the state field of the Gamestate class.
}
