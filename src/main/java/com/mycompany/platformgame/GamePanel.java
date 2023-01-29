package com.mycompany.platformgame;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author mbarb
 */
public class GamePanel extends JPanel{
    
    private MouseInputs mouseInputs;
    private int xDelta = 150, yDelta = 150;
    
    public GamePanel(){
        
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    
    public void changeXDelta(int value){
        this.xDelta += value;
        repaint();
    }
    
    public void changeYDelta(int value){
        this.yDelta += value;
        repaint();
    }
    
    public void setRectPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
        repaint();
    }
    
    //paintComponent never gets called directly - it is call when the play button is used.
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.fillRect(xDelta,yDelta, 50, 50);
        
    }
}
