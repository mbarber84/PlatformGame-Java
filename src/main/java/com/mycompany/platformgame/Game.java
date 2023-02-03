package com.mycompany.platformgame;

import Entities.Character;
import Levels.LevelController;
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
    private Character character;
    private LevelController levelController;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 2f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    public Game() {
        initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();

    }

    private void initClasses() {
        levelController = new LevelController(this);
        character = new Character(200, 200, (int)(64 * SCALE), (int)(40 * SCALE));
        character.loadLvlData(levelController.getCurrentLevel().getLevelData());
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        character.update();
        levelController.update();
    }

    public void render(Graphics g) {
        levelController.draw(g);
        character.render(g);
        
    }

    public Character getCharacter() {
        return character;
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

    public void windowFocusLost() {
        character.resetDirBooleans();
    }

}
