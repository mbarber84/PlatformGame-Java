package UI;

import GameStates.Gamestate;
import GameStates.Playing;
import com.mycompany.platformgame.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


/**
 *
 * @author mbarb
 */
public class GameOverOverlay {
    
    private Playing playing;
    public GameOverOverlay(Playing playing){
        this.playing = playing;
    }
    
    public void draw(Graphics g){
        g.setColor(new Color(69,18,8, 100));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        
        g.setColor(new Color(242,62,30));
        g.drawString("Arrr, ye be done for matey! Game over!!", Game.GAME_WIDTH / 2, 150);
        g.drawString("Ahoy there! If ye be wantin' to return to the main menu, just give that escape key a good press, ye hear!", Game.GAME_WIDTH / 2, 300);
    }
    
    public void KeyPresses(KeyEvent e){
        if(e.getKeyCode()== KeyEvent.VK_ESCAPE){
            playing.resetAll();
            Gamestate.state = Gamestate.MENU;
        }
    }
}
