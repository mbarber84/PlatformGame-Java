package UI;

import Utilities.LoadSave;
import java.awt.image.BufferedImage;
import static Utilities.Constants.UI.PauseButtons.*;
import java.awt.Graphics;

/**
class called SoundButton that extends the PauseButton class and represents a button in a graphical user interface. It loads images for the button from a sprite atlas file and has methods to update the button's appearance based on user input, draw it on the screen, and reset boolean variables for tracking mouse events. The class also has boolean variables to keep track of whether the mouse is hovering over the button, if the button is pressed, and whether the sound is muted.
 */
public class SoundButton extends PauseButton{
    
    private BufferedImage[][] soundImgs;
    private boolean mouseOver, mousePressed;
    private boolean muted;
    private int rowIndex, colIndex;
    
    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        
        loadSoundImgs();
    }
    //constructor for creating a new instance of the SoundButton class with the given position and dimensions.

    private void loadSoundImgs() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SOUND_BUTTONS);
        soundImgs = new BufferedImage[2][3];
        for(int j = 0; j < soundImgs.length; j++)
            for(int i = 0; i < soundImgs[j].length; i++)
                soundImgs[j][i] = temp.getSubimage(i * SOUND_SIZE_DEFAULT, j * SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
    }
    //a private method that loads the images for the button from a sprite atlas file and stores them in a 2D array.
    
    public void update(){
        if(muted)
            rowIndex = 1;
        else
            rowIndex = 0;
        
        colIndex = 0;
        if(mouseOver)
            colIndex = 1;
        if(mousePressed)
            colIndex = 0;
    }
    // a method that updates the appearance of the button based on user input by setting the row and column index of the image to be displayed.
    
    public void restBools(){
        mouseOver = false;
        mousePressed = false;
    }
    
    public void draw(Graphics g){
        g.drawImage(soundImgs[rowIndex][colIndex], x, y, width, height, null);
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

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }   
}
