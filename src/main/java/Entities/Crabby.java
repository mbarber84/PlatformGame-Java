package Entities;

import static Utilities.Constants.EnemyConstants.*;
import com.mycompany.platformgame.Game;

/**
 *
 * @author mbarb
 */
public class Crabby extends Enemy{
    
    public Crabby(float x, float y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
        initHitbox(x, y, (int)(22 * Game.SCALE), (int)(19 * Game.SCALE)); 
    }
    
    public void update(int[][] lvlData, Character character){
        updateMove(lvlData, character);
        updateAnimationTick();
        
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
    
}
