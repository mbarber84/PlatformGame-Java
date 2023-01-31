package com.mycompany.platformgame;

//import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
//import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author mbarb
 */
public class GamePanel extends JPanel{
    
    private MouseInputs mouseInputs;
    private float xDelta = 150, yDelta = 150; // int to float slows speed down in jframe
    //private float xDir = 1f, yDir = 1f; // int to float slows speed down in jframe
    private BufferedImage img;
    
    //private Color color = new Color(160,15,125);
    //private Random random;
    
    public GamePanel(){
        //random = new Random();
        
        mouseInputs = new MouseInputs(this);
        
        importImg();
        
        addKeyListener(new KeyboardInputs(this));
        setPanelSize();
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    
    private void importImg() {
        File f = new File("/player.png");
        
        try {
            img = ImageIO.read(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        
       //updateRectangle();
        //g.setColor(color);
        //g.fillRect((int)xDelta,(int)yDelta, 50, 50); //can only draw using int so converted from float at top
                
        //g.drawImage(null, x, y, null);
    }

   /* private void updateRectangle() {
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
    }*/

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }

    
}
