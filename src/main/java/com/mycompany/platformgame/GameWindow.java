package com.mycompany.platformgame;

import javax.swing.JFrame;

/**
 *
 * @author mbarb
 */
public class GameWindow {
    
    private JFrame jframe;
    public GameWindow(GamePanel gamePanel){
        
        jframe = new JFrame();
        
        jframe.setSize(500, 500);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }
}
