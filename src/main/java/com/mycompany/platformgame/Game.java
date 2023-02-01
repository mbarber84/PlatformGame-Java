package com.mycompany.platformgame;

import Entities.Player;
import java.awt.Graphics;

/**
 *
 * @author mbarb
 */
public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Player player;

    public Game() {
        initClasses();
        
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        
        startGameLoop();

    }
    
     private void initClasses() {
       player = new Player(200, 200);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        player.update();
    }
    
    public void render(Graphics g){
        player.render(g);
    }

    public Player getPlayer(){
        return player;
    }
    
    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET; //nanoseconds
        double timePerUpdate = 1000000000.0 / UPS_SET; //nanoseconds
        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0; //U = updates
        double delatF = 0; //F = frames

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            delatF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) { //to help prevent lag and wasted time from FPS game loop
                update();
                updates++;
                deltaU--;
            }

            if (delatF >= 1) {
                gamePanel.repaint();
                frames++;
                delatF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }

        }

    }

    public void windowFocusLost(){
        player.resetDirBooleans();
    }

   
}
