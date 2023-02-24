package UI;

import Utilities.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static Utilities.Constants.UI.URMButtons.*;

/**(URM - Unpaused, Restart, Menu)
 * class UrmButton extends the PauseButton class and represents a button in a graphical user interface. It loads images for the button from a sprite atlas file and has methods to update the button's appearance based on user input, draw it on the screen, and reset boolean variables for tracking mouse events. The class also has boolean variables to keep track of whether the mouse is hovering over the button or if the button is pressed.
 * 
 */
public class UrmButton extends PauseButton{
    
    private BufferedImage[] imgs;
    private int rowIndex, index;
    private boolean mouseOver, mousePressed;
    
    public UrmButton(int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height);
        this.rowIndex = rowIndex;
        loadImgs();
    }
    //a constructor method that initializes the button's position and size and calls loadImgs() to load the button's images from a sprite atlas file.
    
    private void loadImgs() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.URM_BUTTONS);
        imgs = new BufferedImage[3];
        for(int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * URM_SIZE_DEFAULT, rowIndex * URM_SIZE_DEFAULT, URM_SIZE_DEFAULT, URM_SIZE_DEFAULT);
    }
    // a private method that loads the button's images from a sprite atlas file and stores them in an array of BufferedImages.
    
    public void update(){
        index = 0;
        if(mouseOver)
            index = 1;
        if(mousePressed)
            index = 2;
    }
    //a method that updates the appearance of the button based on user input. It sets the index of the button's current image based on whether the mouse is hovering over the button or if the button is pressed.
    
    public void draw(Graphics g){
        
        g.drawImage(imgs[index], x, y, URM_SIZE, URM_SIZE, null);
    }
    
    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }
    //a method that resets the mouseOver and mousePressed boolean variables to false.

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }  
}
