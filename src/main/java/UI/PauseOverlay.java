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
 *
 * @author mbarb
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
    
     private void createVolumeButton() {
        int volX = (int)(309 * Game.SCALE);
        int volY = (int)(278 * Game.SCALE);
        volumeButton = new VolumeButton(volX, volY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }
    
     private void createUrmButtons() {
        int menuX = (int)(313 * Game.SCALE);
        int replayX = (int)(387 * Game.SCALE);
        int unpauseX = (int)(462 * Game.SCALE);
        int btnY = (int)(325 *Game.SCALE);
        
        menuBtn = new UrmButton(menuX, btnY, URM_SIZE, URM_SIZE, 2);
        replayBtn = new UrmButton(replayX, btnY, URM_SIZE, URM_SIZE, 1);
        unpauseBtn = new UrmButton(unpauseX, btnY, URM_SIZE, URM_SIZE, 0);
    }
    
    private void createSoundButtons() {
        int soundX = (int)(450 * Game.SCALE);
        int musicY = (int)(140 * Game.SCALE);
        int sfxY = (int)(186 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }
    
     private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        bgWidth = (int)(backgroundImg.getWidth() * Game.SCALE);
        bgHeight = (int)(backgroundImg.getHeight()* Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 -bgWidth / 2;
        bgY = (int)(25 * Game.SCALE);
    }
    
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
    
    public void mouseDragged(MouseEvent e) {
        if(volumeButton.isMousePressed()){
            volumeButton.changeX(e.getX());
        }
    }
    
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

    private boolean isIn(MouseEvent e, PauseButton b){
        return b.getBounds().contains(e.getX(), e.getY());
    }
  
}
