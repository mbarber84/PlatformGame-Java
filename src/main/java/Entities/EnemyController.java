package Entities;

import GameStates.Playing;
import Utilities.LoadSave;
import java.awt.image.BufferedImage;
import static  Utilities.Constants.EnemyConstants.*;
import java.awt.Graphics;
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
        addEnemy();
    }
    
     private void addEnemy() {
        crabbies = LoadSave.GetCrabs();
         System.out.println("Crab size = " + crabbies.size());
    }
    
    public void update(int[][] lvlData, Character character){
        for(Crabby c : crabbies)
            c.update(lvlData, character);
    }
    
    public void draw(Graphics g, int xLvlOffset){
        drawCrabs(g, xLvlOffset);
    }
     private void drawCrabs(Graphics g, int xLvlOffset) {
        for(Crabby c : crabbies)
            g.drawImage(crabbyArr[c.getEnemyState()][c.getAniIndex()], (int)c.getHitbox().x - xLvlOffset - CRABBY_DRAWOFFSET_X, (int)c.getHitbox().y - CRABBY_DRAWOFFSET_Y, CRABBY_WIDTH, CRABBY_HEIGHT, null);
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
  
}
