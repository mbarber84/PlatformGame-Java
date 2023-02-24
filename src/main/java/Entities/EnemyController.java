package Entities;

import GameStates.Playing;
import Utilities.LoadSave;
import java.awt.image.BufferedImage;
import static  Utilities.Constants.EnemyConstants.*;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;



/**
 *class EnemyController that manages a group of Crabby enemies in a game. It contains methods to load images of the Crabby enemies, update their positions and states, draw them on   *the screen, and check for collisions with the player's attacks. It also has a method to reset all the Crabby enemies. The class uses other classes and utilities such as Playing,   *LoadSave, BufferedImage, Rectangle2D, and ArrayList.
 * 
 */
public class EnemyController {
    
    private Playing playing;
    private BufferedImage[][] crabbyArr;
    private ArrayList<Crabby> crabbies = new ArrayList<>();
    
    public EnemyController(Playing playing){
        this.playing = playing;
        loadEnemyImgs();
        loadEnemy();
    }
    
     private void loadEnemy() {
        crabbies = LoadSave.GetCrabs();
         System.out.println("Crab size = " + crabbies.size());
    }
     // loads the enemies from a saved file using the LoadSave.GetCrabs() method and stores them in an ArrayList crabbies.
    
    public void update(int[][] lvlData, Character character){
        boolean isEnemyActive = false;
        for(Crabby c : crabbies)
            if(c.isActive()){
            c.update(lvlData, character);
            isEnemyActive = true;
            }
        if(!isEnemyActive)
            playing.setLevelCompleted(true);
    }
    //updates the state of the active crabbies in the crabbies list by calling the update() method of each Crabby object.
    
    public void draw(Graphics g, int xLvlOffset){
        drawCrabs(g, xLvlOffset);
    }
    //draws all the active crabbies on the screen by calling the drawCrabs() method for each Crabby object.
    private void drawCrabs(Graphics g, int xLvlOffset) {
        for(Crabby c : crabbies)
            if(c.isActive()){
            g.drawImage(crabbyArr[c.getEnemyState()][c.getAniIndex()],(int)c.getHitbox().x - xLvlOffset - CRABBY_DRAWOFFSET_X + c.flipX(), (int)c.getHitbox().y - CRABBY_DRAWOFFSET_Y, CRABBY_WIDTH * c.flipW(),CRABBY_HEIGHT, null);
//        c.drawAttackBox(g, xLvlOffset);
        }
    }
    // draws a single Crabby object by getting its image from the crabbyArr array and calling its drawImage() method.
    
    public void checkEnemyHit(Rectangle2D.Float attackBox){
        for(Crabby c : crabbies)
            if(c.isActive())
            if(attackBox.intersects(c.getHitbox())){
                c.hurt(10);
                return;
            }
    }
    // checks if any active Crabby object's hitbox intersects with the attack box passed as parameter and calls its hurt() method if true.

    private void loadEnemyImgs() {
        crabbyArr = new BufferedImage[5][9];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.ENERMY_CRABBY);
        for (int j = 0; j < crabbyArr.length; j++) {
            for (int i = 0; i < crabbyArr[j].length; i++) {
                crabbyArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
            }
        }
    }
    //loads all the images required to draw the Crabby object by calling the GetSpriteAtlas() method from LoadSave utility class and storing the images in the crabbyArr array.
    
    public void resetAllEnemies(){
        for(Crabby c : crabbies)
            c.resetEnemy();
    }
    //resets all the Crabby objects in the crabbies list by calling their resetEnemy() method.
}
