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
 * LevelCompletedOverlay class that provides methods for drawing the level completed overlay and handling mouse events. The class has instance variables for playing, a menu button,  * a background image, and its position and dimensions. 
 * 
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
    //The constructor initializes the instance variables, initializes the background image and initializes the menu button.
     private void initButtons() {
        int menuX = (int)(378 * Game.SCALE);
        int y = (int)(195 * Game.SCALE);
        menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);
    }
     //initializes the menu button with its position and size.

    private void initImg() {
        img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_COMPLETED);
        bgW = (int)(img.getWidth() * Game.SCALE);
        bgH = (int)(img.getHeight()* Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int)(75 * Game.SCALE);
    }
    //The initImg method loads the background image for the overlay and sets its dimensions and position.
    
    public void draw(Graphics g){
        g.drawImage(img, bgX, bgY, bgH, bgH, null);
        menu.draw(g);
    }
    //draws the background image and the menu button using the given graphics object.
    public void update(){
        menu.update();
    }
    //updates the state of the menu button.
    
    private boolean isIn(UrmButton b, MouseEvent e){
        return b.getBounds().contains(e.getX(), e.getY());
    }
    //checks whether the given mouse event occurred within the bounds of the given UrmButton.
    public void mouseMoved(MouseEvent e){
        menu.setMouseOver(false); 
        if(isIn(menu, e))
            menu.setMouseOver(true);
    }
    //sets the mouseOver boolean of the menu button based on whether the mouse event is inside the button's bounds.
    public void mouseReleased(MouseEvent e){
        if(isIn(menu, e))
            if(menu.isMousePressed())
                Gamestate.state = Gamestate.MENU;
        menu.resetBools();
    }
    //hecks whether the mouse event occurred inside the menu button and if the button was previously pressed, sets the game state to MENU and resets the button's booleans.
    public void mousePressed(MouseEvent e){
        if(isIn(menu, e))
            menu.setMousePressed(true);
    }
    //mousePressed method sets the mousePressed boolean of the menu button if the mouse event occurred inside the button's bounds.
}
