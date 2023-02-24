package Entities;

import static Utilities.Constants.EnemyConstants.*;
import com.mycompany.platformgame.Game;
import static Utilities.Constants.Direction.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

/**
 *The Crabby class is a subclass of the Enemy class, defining the properties and behaviour of a crab enemy in a 2D platform game. It has a private variable representing the area of   *the crab's attack, several methods to update its behaviour and state, and methods to draw and flip the crab's image horizontally. The constructor initializes the crab's hitbox and  *attack box.
 * 
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
     // Initializes the attackBox and attackBoxOffsetX variables of the Crabby class.
    
    public void update(int[][] lvlData, Character character){
        updateBehaviour(lvlData, character);
        updateAnimationTick();
        updateAttackBox();
    }
    //Updates the behavior and state of the crab, and updates the animation tick and attack box.
    
     private void updateAttackBox() {
        attackBox.x = hitbox.x - attackBoxOffsetX;
        attackBox.y = hitbox.y;
    }
    
    private void updateBehaviour(int[][] lvlData, Character character){
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
                    if(characterDetected(lvlData, character)){
                        turnTowardsCharacter(character);
                    if(isCharacterInRangeOfAttack(character))       
                     newState(ATTACK);
                    }
                    move(lvlData);
                    break;
                case ATTACK:
                    if(aniIndex == 0)
                        attackChecked = false;
                    
                    if(aniIndex == 3 && !attackChecked)
                        checkEnemyHit(attackBox, character);
                    break;
                case HIT:
                    break;
            }
        }
    }
    //Updates the behaviour of the crab based on its current state and the level data and character object passed in as parameters.
   
    public void drawAttackBox(Graphics g, int xlvlOffset){
        g.setColor(Color.red);
        g.drawRect((int)(attackBox.x - xlvlOffset), (int)(attackBox.y), (int)(attackBox.width), (int)(attackBox.height));
    }
    //Draws the attack box of the crab on the screen.
    
    public int flipX(){
        if(walkDir == RIGHT)
            return width;
        else
            return 0;
    }
    //Flips the crab's image horizontally based on its walking direction, and returns the x coordinate of the flipped image.
    public int flipW(){
        if(walkDir == RIGHT)
            return -1;
        else
            return 1;
    } 
    //Flips the crab's image horizontally based on its walking direction, and returns the width of the flipped image.
}
