package UI;

import GameStates.Gamestate;
import GameStates.Playing;
import Utilities.LoadSave;
import com.mycompany.platformgame.Game;
import java.awt.image.BufferedImage;
import static Utilities.Constants.UI.URMButtons.*;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 *
 * @author mbarb
 */
public class LevelCompletedOverlay {
    
    private Playing playing;
    private UrmButton menu;
    private BufferedImage img;
    private int bgX, bgY, bgW, bgH;
    
    
    public LevelCompletedOverlay(Playing playing){
        this.playing = playing;
        initImg();
        initButtons();
        
    }
     private void initButtons() {
        int menuX = (int)(378 * Game.SCALE);
        int y = (int)(195 * Game.SCALE);
        menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);
    }

    private void initImg() {
        img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_COMPLETED);
        bgW = (int)(img.getWidth() * Game.SCALE);
        bgH = (int)(img.getHeight()* Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int)(75 * Game.SCALE);
    }
    
    public void draw(Graphics g){
        g.drawImage(img, bgX, bgY, bgH, bgH, null);
        menu.draw(g);
    }
    public void update(){
        menu.update();
    }
    
    private boolean isIn(UrmButton b, MouseEvent e){
        return b.getBounds().contains(e.getX(), e.getY());
    }
    
    public void mouseMoved(MouseEvent e){
        menu.setMouseOver(false);
        
        if(isIn(menu, e))
            menu.setMouseOver(true);
    }
    public void mouseReleased(MouseEvent e){
        if(isIn(menu, e))
            if(menu.isMousePressed())
                Gamestate.state = Gamestate.MENU;
        
        menu.resetBools();
    }
    public void mousePressed(MouseEvent e){
        if(isIn(menu, e))
            menu.setMousePressed(true);
    }
}
