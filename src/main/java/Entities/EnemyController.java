package Entities;

import GameStates.Playing;
import Utilities.LoadSave;
import java.awt.image.BufferedImage;
import static  Utilities.Constants.EnemyConstants.*;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;



/**
 *
 * @author mbarb
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
    
    public void draw(Graphics g, int xLvlOffset){
        drawCrabs(g, xLvlOffset);
    }
    private void drawCrabs(Graphics g, int xLvlOffset) {
        for(Crabby c : crabbies)
            if(c.isActive()){
            g.drawImage(crabbyArr[c.getEnemyState()][c.getAniIndex()],(int)c.getHitbox().x - xLvlOffset - CRABBY_DRAWOFFSET_X + c.flipX(), (int)c.getHitbox().y - CRABBY_DRAWOFFSET_Y, CRABBY_WIDTH * c.flipW(),CRABBY_HEIGHT, null);
//        c.drawAttackBox(g, xLvlOffset);
        }
    }
    
    public void checkEnemyHit(Rectangle2D.Float attackBox){
        for(Crabby c : crabbies)
            if(c.isActive())
            if(attackBox.intersects(c.getHitbox())){
                c.hurt(10);
                return;
            }
    }

    private void loadEnemyImgs() {
        crabbyArr = new BufferedImage[5][9];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.ENERMY_CRABBY);
        for (int j = 0; j < crabbyArr.length; j++) {
            for (int i = 0; i < crabbyArr[j].length; i++) {
                crabbyArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
            }
        }
    }
    
    public void resetAllEnemies(){
        for(Crabby c : crabbies)
            c.resetEnemy();
    }
  
}
