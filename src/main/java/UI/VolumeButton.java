package UI;

import Utilities.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static Utilities.Constants.UI.VolumeButton.*;

/**
 *
 * @author mbarb
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
    
        private void loadImgs() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.VOLUME_BUTTONS);
        imgs = new BufferedImage[3];
        for(int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * VOLUME_WIDTH_DEFAULT, 0, VOLUME_WIDTH_DEFAULT, VOLUME_HEIGHT_DEFAULT);
        
        slider = temp.getSubimage(3 * VOLUME_WIDTH_DEFAULT, 0, SLIDER_WIDTH_DEFAULT, VOLUME_HEIGHT_DEFAULT);
    }
    
    public void update(){
        index = 0;
        if(mouseOver)
            index = 1;
        if(mousePressed)
            index = 2;
    }
    
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
