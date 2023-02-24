package UI;


import GameStates.Gamestate;
import GameStates.Playing;
import Utilities.LoadSave;
import com.mycompany.platformgame.Game;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import static Utilities.Constants.UI.PauseButtons.*;
import static Utilities.Constants.UI.URMButtons.*;
import static Utilities.Constants.UI.VolumeButton.*;

/**
 * "PauseOverlay" class creates a menu screen that appears over a game when it is paused. The menu contains buttons to control the game's music and sound effects, as well as        * buttons to return to the main menu or replay the current level. The code includes methods for creating the buttons and background, updating the menu's state, and handling user   * input via mouse events.
 * 
 */
public class PauseOverlay {
    private Playing playing;
    private int bgX, bgY, bgWidth, bgHeight;
    private BufferedImage backgroundImg;
    private SoundButton musicButton, sfxButton;
    private UrmButton menuBtn, replayBtn, unpauseBtn;
    private VolumeButton volumeButton;
    
    public PauseOverlay(Playing playing){
        this.playing = playing;
        loadBackground();
        createSoundButtons();
        createUrmButtons();
        createVolumeButton();    
    }
    //Constructor method that takes an object of the Playing class as input and initializes the Playing object and calls the loadBackground(), createSoundButtons(), createUrmButtons(), and createVolumeButton() methods.
    
     private void createVolumeButton() {
        int volX = (int)(309 * Game.SCALE);
        int volY = (int)(278 * Game.SCALE);
        volumeButton = new VolumeButton(volX, volY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }
    //Method that creates the volume slider and button for the pause menu screen.
     private void createUrmButtons() {
        int menuX = (int)(313 * Game.SCALE);
        int replayX = (int)(387 * Game.SCALE);
        int unpauseX = (int)(462 * Game.SCALE);
        int btnY = (int)(325 *Game.SCALE);
        
        menuBtn = new UrmButton(menuX, btnY, URM_SIZE, URM_SIZE, 2);
        replayBtn = new UrmButton(replayX, btnY, URM_SIZE, URM_SIZE, 1);
        unpauseBtn = new UrmButton(unpauseX, btnY, URM_SIZE, URM_SIZE, 0);
    }
     //creates the buttons for returning to the main menu, replaying the current level, and unpausing the game
    private void createSoundButtons() {
        int soundX = (int)(450 * Game.SCALE);
        int musicY = (int)(140 * Game.SCALE);
        int sfxY = (int)(186 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }
    //creates the volume slider and button for the pause menu screen.
    
     private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        bgWidth = (int)(backgroundImg.getWidth() * Game.SCALE);
        bgHeight = (int)(backgroundImg.getHeight()* Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 -bgWidth / 2;
        bgY = (int)(25 * Game.SCALE);
    }
     //Method that loads the background image for the pause menu screen using the LoadSave.GetSpriteAtlas() method and sets the position and size of the background image based on the game's width and height.
    
    public void update(){
        musicButton.update();
        sfxButton.update();
        menuBtn.update();
        replayBtn.update();
        unpauseBtn.update();
        volumeButton.update();
    }
    
    public void draw(Graphics g){
        //Pause Background Image
        g.drawImage(backgroundImg, bgX, bgY, bgWidth, bgHeight, null);
        //Pause Menu Sound Buttons
        musicButton.draw(g);
        sfxButton.draw(g);
        //URM buttons
        menuBtn.draw(g);
        replayBtn.draw(g);
        unpauseBtn.draw(g);
        //Volume Slider and Button
        volumeButton.draw(g);
    }
    //draws the pause menu screen by drawing the background image, sound buttons, URM buttons, and volume slider and button using the Graphics object.
    
    public void mouseDragged(MouseEvent e) {
        if(volumeButton.isMousePressed()){
            volumeButton.changeX(e.getX());
        }
    }
    //the mouse drag event by changing the position of the volume slider button.
    
    public void mousePressed(MouseEvent e) {
        if(isIn(e, musicButton))
            musicButton.setMousePressed(true);
        else if(isIn(e, sfxButton))
            sfxButton.setMousePressed(true);
        else if(isIn(e, menuBtn))
            menuBtn.setMousePressed(true);
        else if(isIn(e, replayBtn))
            replayBtn.setMousePressed(true);
        else if(isIn(e, unpauseBtn))
            unpauseBtn.setMousePressed(true);
        else if(isIn(e, volumeButton))
            volumeButton.setMousePressed(true);
                    
    }
    // handles the mouse press event by setting a boolean variable to true if the mouse is pressed within the bounds of a button.

    public void mouseReleased(MouseEvent e) {
        if(isIn(e, musicButton)){
            if(musicButton.isMousePressed()){
                musicButton.setMuted(!musicButton.isMuted());
            }
        }
        else if(isIn(e, sfxButton)){
            if(sfxButton.isMousePressed())
                sfxButton.setMuted(!sfxButton.isMuted());
        } else if(isIn(e, menuBtn)){
            if(menuBtn.isMousePressed()){
                Gamestate.state = Gamestate.MENU;
                playing.unpauseGame();
            }
        }else if(isIn(e, replayBtn)){
            if(replayBtn.isMousePressed()){
                playing.resetAll();
                playing.unpauseGame();
            }
//                System.out.println("Replay Level Action No Completed Yet!!");
        }else if(isIn(e, unpauseBtn)){
            if(unpauseBtn.isMousePressed())
                playing.unpauseGame();
        }
        musicButton.restBools();
        sfxButton.restBools();
        menuBtn.resetBools();
        replayBtn.resetBools();
        unpauseBtn.resetBools();
        volumeButton.resetBools();
    }
    //handles the mouse release event by performing the appropriate action based on which button was pressed and resetting the boolean variables for each button.

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        menuBtn.setMouseOver(false);
        replayBtn.setMouseOver(false);
        unpauseBtn.setMouseOver(false);
        volumeButton.setMouseOver(false);
        
        if(isIn(e, musicButton))
            musicButton.setMouseOver(true);
        else if(isIn(e, sfxButton))
            sfxButton.setMouseOver(true);
        else if(isIn(e, menuBtn))
            menuBtn.setMouseOver(true);
        else if(isIn(e, replayBtn))
            replayBtn.setMouseOver(true);
        else if(isIn(e, unpauseBtn))
            unpauseBtn.setMouseOver(true);
        else if(isIn(e, volumeButton))
            volumeButton.setMouseOver(true);
    }
    //The mouseMoved method is called when the mouse is moved over the game window. It sets the mouseOver flag to false for all game buttons and checks if the mouse is inside the bounds of each button using the isIn method.

    private boolean isIn(MouseEvent e, PauseButton b){
        return b.getBounds().contains(e.getX(), e.getY());
    }
    //The isIn method checks if the given mouse event e is inside the bounds of the PauseButton b by calling the getBounds method of b and checking if it contains the coordinates of the mouse event. If the mouse is inside the bounds of the button, isIn returns true, otherwise it returns false.
}
