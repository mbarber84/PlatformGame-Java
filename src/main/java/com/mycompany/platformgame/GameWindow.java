package com.mycompany.platformgame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

/**
 *
 *
 *The GameWindow class in the package com.mycompany.platformgame, which creates a window for displaying the game using the JFrame class from the javax.swing package.
 * The GameWindow constructor takes a GamePanel object as an argument, adds it to the frame, sets various properties such as the close operation, re-sizability, location and visibility, and adds a WindowFocusListener to listen for focus changes.
 * The listener has two methods, windowGainedFocus and windowLostFocus, which are called when the window gains or loses focus respectively.
 * The windowLostFocus method calls the windowFocusLost method on the game object that is obtained from the gamePanel.
 */
public class GameWindow {

    private JFrame jframe;

    public GameWindow(GamePanel gamePanel) {

        jframe = new JFrame();

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }
        });
    }
}
