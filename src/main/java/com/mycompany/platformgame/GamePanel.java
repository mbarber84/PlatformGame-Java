package com.mycompany.platformgame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author mbarb
 */
public class GamePanel extends JPanel{
    
    private MouseInputs mouseInputs;
    private float xDelta = 150, yDelta = 150; // int to float slows speed down in jframe
    private float xDir = 1f, yDir = 1f; // int to float slows speed down in jframe
    private int frames = 0;
    private long lastCheck = 0;
    
    private Color color = new Color(160,15,125);
    private Random random;
    
    public GamePanel(){
        random = new Random();
        
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    
    public void changeXDelta(int value){
        this.xDelta += value;
        
    }
    
    public void changeYDelta(int value){
        this.yDelta += value;
        
    }
    
    public void setRectPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
        
    }
    
    //paintComponent never gets called directly - it is call when the play button is used.
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        updateRectangle();
        
        g.setColor(color);
        
        g.fillRect((int)xDelta,(int)yDelta, 50, 50); //can only draw using int so converted from float at top
        
        
        
    }

    private void updateRectangle() {
        xDelta += xDir;
        if(xDelta > 500 || xDelta < 0){
           xDir*= -1;
           color = getRndColor();
        }
        
        yDelta += yDir;
        if(yDelta > 500 || yDelta < 0){
           yDir*= -1;
           color = getRndColor();
        }
            
        
        
    }

    private Color getRndColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        
        return new Color(r, g, b);
    }
}
