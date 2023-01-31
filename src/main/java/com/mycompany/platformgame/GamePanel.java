package com.mycompany.platformgame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import static Utilities.Constants.PlayerConstants.*;
import static Utilities.Constants.Direction.*;
/**
 *
 * @author mbarb
 */
public class GamePanel extends JPanel{
    
    private MouseInputs mouseInputs;
    private float xDelta = 150, yDelta = 150; // int to float slows speed down in jframe
    private BufferedImage img, subImg;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;
    
    public GamePanel(){
        
        mouseInputs = new MouseInputs(this);
        
        importImg();
        loadAnimations();
        
        addKeyListener(new KeyboardInputs(this));
        setPanelSize();
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    
    private void loadAnimations() {
        animations = new BufferedImage[9][6];
        
        for(int j = 0; j < animations.length; j++)
            for(int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
    }
    
    private void importImg() {
        File f = new File("Res/player2.png");
        
        try {
            img = ImageIO.read(f);
        } catch (IOException e) {
            e.printStackTrace();
        }//try catch .close() 
    }
    
    private void setAnimation() {
        if(moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
    }
    
    private void updatePos() {
        if(moving){
            switch(playerDir){
                case LEFT:
                    xDelta -= 5;
                    break;
                case UP:
                    yDelta -= 5;
                    break;
                case RIGHT:
                    xDelta += 5;
                    break;
                case DOWN:
                    yDelta += 5;
                    break;
                
            }
        }
    }
    
    //paintComponent never gets called directly - it is call when the play button is used.
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        updateAnimationTick();
        setAnimation();
        updatePos();
                
        g.drawImage(animations[playerAction][aniIndex], (int)xDelta, (int)yDelta, 256, 160, null);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }
    
    public void setDirection(int direction){
        this.playerDir = direction;
        moving = true;
    }
    
    public void setMoving(boolean moving){
        this.moving = moving;
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
           }
        }
        
    }
    
}
