package UI;

import Utilities.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static Utilities.Constants.UI.VolumeButton.*;

/**
 * Volume class extends from the PauseButton class and represents a volume button in a graphical user interface. It loads images for the button and slider from a sprite atlas file and has methods to update the button's appearance based on user input, draw it on the screen, and change its position. It also has boolean variables to keep track of whether the mouse is hovering over the button or if the button is pressed.
 * 
 */
public class VolumeButton extends PauseButton{
    
    private BufferedImage[] imgs;
    private BufferedImage slider;
    private int index = 0;
    private boolean mouseOver, mousePressed;
    private int btnX, minX, maxX;
    
    public VolumeButton(int x, int y, int width, int height) {
        super(x + width / 2, y, VOLUME_WIDTH, height);
        bounds.x -= VOLUME_WIDTH / 2;
        btnX = x + width / 2;
        this.x = x;
        this.width = width;
        minX = x + VOLUME_WIDTH / 2;
        maxX = x + width - VOLUME_WIDTH / 2;
        loadImgs();
    }
    //constructor for VolumeButton class, initializes button's bounds, position and loads images for the button and slider.
    
        private void loadImgs() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.VOLUME_BUTTONS);
        imgs = new BufferedImage[3];
        for(int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * VOLUME_WIDTH_DEFAULT, 0, VOLUME_WIDTH_DEFAULT, VOLUME_HEIGHT_DEFAULT);
        
        slider = temp.getSubimage(3 * VOLUME_WIDTH_DEFAULT, 0, SLIDER_WIDTH_DEFAULT, VOLUME_HEIGHT_DEFAULT);
    }
    //loads images for the button and slider from a sprite atlas file and stores them in imgs and slider variables.
    
    public void update(){
        index = 0;
        if(mouseOver)
            index = 1;
        if(mousePressed)
            index = 2;
    }
    //updates the button's appearance based on user input (mouse hover and press) and sets the index to 0, 1, or 2 accordingly.
    
    public void draw(Graphics g){
        //Slider
        g.drawImage(slider, x, y, width, height, null);
        //Button
        g.drawImage(imgs[index], btnX - VOLUME_WIDTH / 2, y, VOLUME_WIDTH, height, null);  
    }
    
    public void changeX(int x){
        if(x < minX)
            btnX = minX;
        else if(x > maxX)
            btnX = maxX;
        else
            btnX = x;
        
        bounds.x = btnX - VOLUME_WIDTH / 2;
    }
    // changes the button's x-position within the minimum and maximum limits and updates the button's bounds accordingly.
    
    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }

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
