package Entities;

import static Utilities.Constants.EnemyConstants.*;
import com.mycompany.platformgame.Game;
import static Utilities.Constants.Direction.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author mbarb
 */
public class Crabby extends Enemy{
    
    //Crabby attack hitbox
    private Rectangle2D.Float attackBox;
    private int attackBoxOffsetX;
    
    public Crabby(float x, float y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
        initHitbox(x, y, (int)(22 * Game.SCALE), (int)(19 * Game.SCALE)); 
        initAttckBOX();
    }
    
     private void initAttckBOX() {
        attackBox = new Rectangle2D.Float(x, y, (int)(82 * Game.SCALE), (int)(19 * Game.SCALE));
        attackBoxOffsetX = (int)(Game.SCALE * 30);
    }
    
    public void update(int[][] lvlData, Character character){
        updateMove(lvlData, character);
        updateAnimationTick();
        updateAttackBox();
        
    }
    
     private void updateAttackBox() {
        attackBox.x = hitbox.x - attackBoxOffsetX;
        attackBox.y = hitbox.y;
    }
    
    private void updateMove(int[][] lvlData, Character character){
        if(firstUpdate)
            firstUpdateCheck(lvlData);
        
        if(inAir)
            updateInAir(lvlData);
        else{
            switch(enemyState){
                case IDLE:
                    newState(RUNNING);
                    break;
                case RUNNING:
                    if(characterDetected(lvlData, character))
                        turnTowardsCharacter(character);
                 if(isCharacterInRangeOfAttack(character))       
                     newState(ATTACK);
                    move(lvlData);
                    break;
            }
        }
    }
    
    public void drawAttackBox(Graphics g, int xlvlOffset){
        g.setColor(Color.red);
        g.drawRect((int)(attackBox.x - xlvlOffset), (int)(attackBox.y), (int)(attackBox.width), (int)(attackBox.height));
    }
    
    public int flipX(){
        if(walkDir == RIGHT)
            return width;
        else
            return 0;
    }
    public int flipW(){
        if(walkDir == RIGHT)
            return -1;
        else
            return 1;
    } 
}
