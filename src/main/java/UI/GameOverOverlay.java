package UI;

import GameStates.Gamestate;
import GameStates.Playing;
import com.mycompany.platformgame.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


/**
 *
 *
 */
public class GameOverOverlay {
    
    private Playing playing;
    public GameOverOverlay(Playing playing){
        this.playing = playing;
    }
    
    public void draw(Graphics g){
        g.setColor(new Color(69,18,8, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        
        g.setColor(new Color(102,255,0));
        g.drawString("Arrr, ye be done for matey! Game over!!", Game.GAME_WIDTH / 3, 150);
        g.drawString("Ahoy there! If ye be wantin' to return to the main menu, just give that escape key a good press, ye hear!", Game.GAME_WIDTH / 3, 300);
    }
    
    public void KeyPresses(KeyEvent e){
        if(e.getKeyCode()== KeyEvent.VK_ESCAPE){
            playing.resetAll();
            Gamestate.state = Gamestate.MENU;
        }
    }
}
