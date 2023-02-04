package UI;

import Utilities.LoadSave;
import com.mycompany.platformgame.Game;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author mbarb
 */
public class PauseOverlay {
    private int bgX, bgY, bgWidth, bgHeight;
    
    private BufferedImage backgroundImg;
    
    public PauseOverlay(){
        
        loadBackground();
        
    }
    
     private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        bgWidth = (int)(backgroundImg.getWidth() * Game.SCALE);
        bgHeight = (int)(backgroundImg.getHeight()* Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 -bgWidth / 2;
        bgY = (int)(25 * Game.SCALE);
    }
    
    public void update(){
        
    }
    
    public void draw(Graphics g){
        g.drawImage(backgroundImg, bgX, bgY, bgWidth, bgHeight, null);
    }
    
    public void mouseDragged(MouseEvent e) {
        
    }
    
    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }
    
}
