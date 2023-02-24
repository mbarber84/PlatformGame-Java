package Entities;

import static Utilities.Constants.Direction.*;
import static Utilities.Constants.EnemyConstants.*;
import static Utilities.HelpMethods.*;
import com.mycompany.platformgame.Game;
import java.awt.geom.Rectangle2D;


/**
 *  a Java abstract class called "Enemy" that extends from another class called "Entity". It contains various protected fields and methods that are used by subclasses to represent  *  different types of enemies in a game. These fields and methods include things like movement behaviour, health, animation, and attack mechanics.
 *
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
    protected int maxHealth;
    protected int currentHealth;
    protected boolean active = true;
    protected boolean attackChecked;
    
    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x, y, width, height);
        maxHealth = GetMaxHealth(enemyType);
        currentHealth = maxHealth;
    }
    
    protected void firstUpdateCheck(int[][] lvlData){
        if(!IsEntityOnGround(hitbox, lvlData)) 
               inAir = true;
           firstUpdate = false;
    }
    //A protected method that checks if the entity is on the ground or not. If it is not on the ground, sets the inAir variable to true.
    
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
    //A protected method that updates the position of the entity when it is in the air. If it can move down, it moves down and falls faster. Otherwise, it sets the position of the entity on the ground.
    
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
    //A protected method that moves the entity to the left or right depending on the direction it is facing. If it can move in that direction, it moves. Otherwise, it changes the direction it is facing.
    
    protected void turnTowardsCharacter(Character character){
        if(character.hitbox.x > hitbox.x)
            walkDir = RIGHT;
        else
            walkDir = LEFT;
    }
    //A protected method that turns the entity towards the character it is facing.
    
    protected boolean characterDetected(int[][] lvlData, Character character){
        int characterTileY = (int)(character.getHitbox().y / Game.TILES_SIZE);
        if(characterTileY == tileY)
            if(isCharacterInRange(character)){
                if(IsClearSight(lvlData, hitbox, character.hitbox, tileY))
                    return true;
            }
        return false;
    }
    //A protected method that checks if the character is in the range of the entity's detection. If the character is in range, checks if there is a clear line of sight between the character and the entity. Returns true if the character is detected.
    
    protected boolean isCharacterInRange(Character character){
        int absValue = (int)Math.abs(character.hitbox.x - hitbox.x);
        return absValue <= attackDistance * 5;
    }
    //A protected method that checks if the character is in the range of the entity's detection. Returns true if the character is in range.
    
    protected boolean isCharacterInRangeOfAttack(Character character){
        int absValue = (int)Math.abs(character.hitbox.x - hitbox.x);
        return absValue <= attackDistance;
    }
    //A protected method that checks if the character is in the range of the entity's attack. Returns true if the character is in range.
    
    protected void newState(int enemyState){
        this.enemyState = enemyState;
        aniTick = 0;
        aniIndex = 0;
    }
    //A protected method that sets the state of the entity to the given state, sets the animation tick to 0 and the animation index to 0.
     
    protected void checkEnemyHit(Rectangle2D.Float attackBox, Character character) {
        if(attackBox.intersects(character.hitbox))
            character.changeHealth(-GetEnemyDmg(enemyType));
        attackChecked = true;
    }
    //A protected method that checks if the entity's attack box intersects with the character's hitbox. If they intersect, decreases the character's health by the enemy's damage.
    
    public void hurt(int amount){
        currentHealth -= amount;
        if(currentHealth <= 0)
            newState(DEAD);
        else
            newState(HIT);
    }
    //A public method that decreases the entity's health by the given amount. If the entity's health is less than or equal to 0, sets the state of the entity to "DEAD". Otherwise, sets the state to "HIT".
    
    protected void updateAnimationTick(){
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(enemyType, enemyState)) {
                aniIndex = 0;
                
                switch(enemyState){
                    case ATTACK,HIT -> enemyState = IDLE;
                    case DEAD -> active = false;
                }
                //OLD VERSION REPLACED WITH ONE ABOVE
//                if(enemyState == ATTACK)
//                    enemyState = IDLE;
//                else if(enemyState == HIT)
//                        enemyState = IDLE;
//                else if(enemyState == DEAD)
//                    active = false;
            }
        }
    }
    //A protected method that updates the animation tick and animation index of the entity. If the animation index is greater than or equal to the sprite amount of the current enemy state, it sets the animation index to 0 and changes the state of the entity depending on its current state.

    protected void changeWalkDir(){
        if(walkDir == LEFT)
            walkDir = RIGHT;
        else
            walkDir = LEFT;
    }
    //changes the direction that the entity is walking in.
    
    public void resetEnemy(){
        hitbox.x = x;
        hitbox.y = y;
        firstUpdate = true;
        currentHealth = maxHealth;
        newState(IDLE);
        active = true;
        fallSpeed = 0;
    }
    //resets the position, state, and health of the entity to its initial state.
    
    public int getAniIndex(){
        return aniIndex;
    }
    public int getEnemyState(){
        return enemyState;
    }
    public boolean isActive(){
        return active;
    }
}
