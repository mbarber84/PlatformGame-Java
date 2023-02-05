package Entities;

import static Utilities.Constants.Direction.*;
import static Utilities.Constants.EnemyConstants.*;
import static Utilities.HelpMethods.*;
import com.mycompany.platformgame.Game;


/**
 *
 * @author mbarb
 */
public abstract class Enemy extends Entity{
    
    protected int aniIndex, enemyState, enemyType;
    protected int aniTick, aniSpeed = 25;
    protected boolean firstUpdate = true;
    protected boolean inAir;
    protected float fallSpeed;
    protected float gravity = 0.04f * Game.SCALE;
    protected float walkSpeed = 0.35f * Game.SCALE;
    protected int walkDir = LEFT;
    protected int tileY;
    protected float attackDistance = Game.TILES_SIZE;
    
    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x, y, width, height);
    }
    
    protected void firstUpdateCheck(int[][] lvlData){
        if(!IsEntityOnGround(hitbox, lvlData)) 
               inAir = true;
           firstUpdate = false;
    }
    
    protected void updateInAir(int[][] lvlData){
        if(CanMoveHere(hitbox.x, hitbox.y + fallSpeed, hitbox.width, hitbox.height, lvlData)){
                hitbox.y += fallSpeed;
                fallSpeed += gravity;
            }else{
                inAir = false;
                hitbox.y = GetEntityYPosUdrRoofOrAbvFloor(hitbox, fallSpeed);
                tileY = (int)(hitbox.y / Game.TILES_SIZE);
            }
    }
    
    protected void move(int[][] lvlData){
        float xSpeed = 0;
                    
                    if(walkDir == LEFT)
                        xSpeed = -walkSpeed;
                    else
                        xSpeed = walkSpeed;
                    
                    if(CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
                        if(IsGround(hitbox, xSpeed, lvlData)){
                        hitbox.x += xSpeed;
                        return;
                        }
                    changeWalkDir();
    }
    
    protected void turnTowardsCharacter(Character character){
        if(character.hitbox.x > hitbox.x)
            walkDir = RIGHT;
        else
            walkDir = LEFT;
    }
    
    protected boolean characterDetected(int[][] lvlData, Character character){
        int characterTileY = (int)(character.getHitbox().y / Game.TILES_SIZE);
        if(characterTileY == tileY)
            if(isCharacterInRange(character)){
                if(IsClearSight(lvlData, hitbox, character.hitbox, tileY))
                    return true;
            }
        
        return false;
    }
    
    protected boolean isCharacterInRange(Character character){
        int absValue = (int)Math.abs(character.hitbox.x - hitbox.x);
        return absValue <= attackDistance * 5;
    }
    
    protected boolean isCharacterInRangeOfAttack(Character character){
        int absValue = (int)Math.abs(character.hitbox.x - hitbox.x);
        return absValue <= attackDistance;
    }
    
    protected void newState(int enemyState){
        this.enemyState = enemyState;
        aniTick = 0;
        aniIndex = 0;
    }
    
    protected void updateAnimationTick(){
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(enemyType, enemyState)) {
                aniIndex = 0;
                if(enemyState == ATTACK)
                    enemyState = IDLE;
            }
        }
    }

    protected void changeWalkDir(){
        if(walkDir == LEFT)
            walkDir = RIGHT;
        else
            walkDir = LEFT;
    }
    
    public int getAniIndex(){
        return aniIndex;
    }
    public int getEnemyState(){
        return enemyState;
    }

    
}
