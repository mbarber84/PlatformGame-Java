package Utilities;

import Entities.Crabby;
import static Utilities.Constants.EnemyConstants.CRABBY;
import com.mycompany.platformgame.Game;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *This Java code defines a utility class called LoadSave that provides methods for loading and saving game assets such as sprite images and level data. The class includes several    * public constants that represent the filenames of various assets, such as player sprites, menu buttons, and sound effects. 
 * 
 */
public class LoadSave {

    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "level_one_data_long.png";
    public static final String MENU_BUTTONS = "button_atlas.png";
    public static final String MENU_BACKGROUND = "menu_background.png";
    public static final String PAUSE_BACKGROUND = "pause_menu.png";
    public static final String SOUND_BUTTONS = "sound_button.png";
    public static final String URM_BUTTONS = "urm_buttons.png";
    public static final String VOLUME_BUTTONS = "volume_buttons.png";
    public static final String START_MENU_BACKGROUND = "game_menu_landscape.png";
    public static final String ENERMY_CRABBY = "crabby_sprite.png";
    public static final String STATUS_BAR = "health_power_bar.png";
    public static final String LEVEL_COMPLETED = "completed_sprite.png";

    public static BufferedImage GetSpriteAtlas(String filename) {
        BufferedImage img = null;
        File f = new File("Res/" + filename);

        try {
            img = ImageIO.read(f);
        } catch (IOException e) {
            e.printStackTrace();
        }//try catch .close() 
        return img;
    }
    //GetSpriteAtlas(String filename): This method takes a filename string as input and returns a BufferedImage object representing the sprite atlas image located in the "Res/"        directory with the given filename. If the image file cannot be read, the method prints a stack trace and returns null.
    
    public static ArrayList<Crabby> GetCrabs(){
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        ArrayList<Crabby> list = new ArrayList<>();
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getGreen();
                if (value == CRABBY)
                    list.add(new Crabby(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
            }
        }
        return list;
    }
    //a method named GetCrabs that returns an ArrayList of Crabby objects. The purpose of the method is to read an image file called LEVEL_ONE_DATA using the GetSpriteAtlas method     and search for pixels that have a specific color.

    public static int[][] GetLevelData() {   
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];

        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48) {
                    value = 0;
                }
                lvlData[j][i] = value;
            }
        }
        return lvlData;
    }
    //GetLevelData(): This method reads the level data image file "level_one_data_long.png" using the GetSpriteAtlas method and returns a two-dimensional integer array representing       the tile values of the level map. Each element of the array represents a tile in the level, with a value of 0 indicating an empty tile and a value of 1 indicating a solid           tile. The method uses the red component of each pixel in the level data image to determine the tile value
}
