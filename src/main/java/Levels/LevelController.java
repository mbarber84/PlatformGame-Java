package Levels;

import Utilities.LoadSave;
import com.mycompany.platformgame.Game;
import static com.mycompany.platformgame.Game.TILES_SIZE;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * LevelController that controls the behaviour of a game level. The LevelController class depends on the LoadSave and Level classes to load the level data and sprites. It also       * depends on the Game class for the TILES_SIZE constant.
 * 
 */
public class LevelController {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public LevelController(Game game) {
        this.game = game;
        importOutsideSprites();
        levelOne = new Level(LoadSave.GetLevelData());
    }
    //Constructor method that takes a Game object as a parameter and initializes the instance variables game, levelSprite, and levelOne. It also calls the importOutsideSprites()        method to load the level sprites from an image file using the LoadSave.GetSpriteAtlas() method and stores them in the levelSprite array.

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }
    //Method that loads the level sprites from an image file using the LoadSave.GetSpriteAtlas() method and stores them in the levelSprite array. It loops through the image and         extracts each 32x32 pixel sprite into a separate image in the levelSprite array.

    public void draw(Graphics g, int lvlOffset) {

        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < levelOne.getLevelData()[0].length; i++) {
                int index = levelOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], TILES_SIZE * i - lvlOffset, TILES_SIZE * j, TILES_SIZE, TILES_SIZE, null);
            }
        }
    }
    //Method that draws the level on the screen. It loops through the level data in levelOne and uses the getSpriteIndex() method of levelOne to get the index of the sprite to draw     at each location. It then uses Graphics.drawImage() to draw the sprite on the screen with the appropriate size and position.

    public void update() {
        //Method that updates the state of the level. Currently, it does nothing.
    }
    
    public Level getCurrentLevel(){
        return levelOne;
    }
}
//Method that returns the Level object levelOne.

