package com.mycompany.platformgame;

import GameStates.Gamestate;
import GameStates.Menu;
import GameStates.Playing;
import java.awt.Graphics;

/**
 *
 *
 *  "Game" is the main component of a platform game. It initializes the game window, game panel, and game thread, and sets the frames per second (FPS) and updates per second (UPS) to 120 and 200 respectively.
 * The game has two states - menu and playing - and has methods to update and render these states. 
 *The game loop updates the game state, calculates the FPS and UPS, and repaints the game panel.
 * The class also has methods to reset the game state when the game window loses focus and to retrieve the game's menu and playing states.
 */
public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Playing playing;
    private Menu menu;

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
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();

        startGameLoop();

    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        switch (Gamestate.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case OPTIONS:
            case QUIT:
            default:
                System.exit(0);
                break;
        }
    }

    public void render(Graphics g) {
        switch (Gamestate.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
        }
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
/*This is a game loop which runs continuously, updating the game state and rendering the game display. The variables FPS_SET and UPS_SET determine the desired number of updates and frames per second. The code calculates the time between each update and frame and keeps track of the number of updates and frames completed. Every second, it prints the number of updates and frames completed and resets the counters. The loop continues to run until the game is closed.*/
    }

    public void windowFocusLost() {
        if (Gamestate.state == Gamestate.PLAYING) {
            playing.getCharacter().resetDirBooleans();
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

}
