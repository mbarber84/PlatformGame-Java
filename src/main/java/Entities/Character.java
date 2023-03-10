package Entities;

import GameStates.Playing;
import static Utilities.Constants.CharacterConstants.*;
import static Utilities.HelpMethods.*;
import Utilities.LoadSave;
import com.mycompany.platformgame.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *  character class with various properties and actions such as speed, animations, health bar, and attack functionality. It imports libraries for graphics rendering and custom      *  classes for accessing game instances. The class extends the Entity class and contains methods for updating the character's position, animation, health bar, and attack box, as   *  well as rendering it on the screen.
 * 
 */
public class Character extends Entity {

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int characterAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down, jump;
    private float characterSpeed = 1.0f * Game.SCALE;
    private int[][] lvlData;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;

    // Jumping and Gravity Variables
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAftCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;

    //Health Bar
    private BufferedImage statusBarImg;

    private int statusBarWidth = (int) (192 * Game.SCALE);
    private int statusBarHeight = (int) (58 * Game.SCALE);
    private int statusBarX = (int) (10 * Game.SCALE);
    private int statusBarY = (int) (10 * Game.SCALE);

    private int healthBarWidth = (int) (150 * Game.SCALE);
    private int healthBarHeight = (int) (4 * Game.SCALE);
    private int healthBarXStart = (int) (34 * Game.SCALE);
    private int healthBarYStart = (int) (14 * Game.SCALE);

    private int maxHealth = 100;
    private int currentHealth = maxHealth;
    private int healthWidth = healthBarWidth;

    //Attack hitbox
    private Rectangle2D.Float attackBox;
    private int flipX = 0; //simple way to flip character to face opposite direction without sprite images
    private int flipW = 1; //simple way to flip character to face opposite direction without sprite images
    
    private boolean attackChecked;
    private Playing playing;

    public Character(float x, float y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
        loadAnimations();
        initHitbox(x, y, (int) (20 * Game.SCALE), (int) (27 * Game.SCALE));
        initAttackBox();

    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int) (20 * Game.SCALE), (int) (20 * Game.SCALE));
    }
    // Initializes the attack box for the character.

    public void update() {
        updateHealthBar();
        
        if(currentHealth <= 0){
            playing.setGameOver(true);
            return;
        }
        updateAttachkBox(); 
        updatePos();
        if(attacking)
            checkAttack();
        updateAnimationTick();
        setAnimation();
    }
    //Updates the character's health bar, attack box, position, animation tick, and animation.
    
    private void checkAttack() {
        if(attackChecked || aniIndex != 1)
            return;
        attackChecked = true;
        playing.checkEnemyHit(attackBox);
    }
    // Checks if the character is attacking and if it has hit an enemy.

    private void updateAttachkBox() {
        if (right) {
            attackBox.x = hitbox.x + hitbox.width + (int) (Game.SCALE * 10);
        } else if (left) {
            attackBox.x = hitbox.x - hitbox.width - (int) (Game.SCALE * 10);
        }
        attackBox.y = hitbox.y + (int) (Game.SCALE * 10);
    }
    //Updates the position of the attack box based on the direction the character is facing.

    private void updateHealthBar() {
        healthWidth = (int) ((currentHealth / (float) maxHealth) * healthBarWidth);
    }
    //Updates the health bar based on the current health of the character.

    public void render(Graphics g, int lvlOffset) {
        g.drawImage(animations[characterAction][aniIndex], 
                (int) (hitbox.x - xDrawOffset) - lvlOffset + flipX, 
                (int) (hitbox.y - yDrawOffset), 
                width * flipW, height, null);
//        drawHitbox(g, xLvlOffset);
//        drawAttaclBox(g, lvlOffset);
        drawUI(g);
    }
    //Renders the character on the screen, including its animation, hitbox, attack box, and health bar.

    private void drawAttaclBox(Graphics g, int lvlOffsetX) {
        g.setColor(Color.red);
        g.drawRect((int) attackBox.x - lvlOffsetX, (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
    }
    //Draws the attack box on the screen.

    private void drawUI(Graphics g) {
        g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
        g.setColor(Color.red);
        g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);
    }
    //Draws the health bar and status bar on the screen.

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(characterAction)) {
                aniIndex = 0;
                attacking = false;
                attackChecked = false;
            }
        }
    }
    //Updates the animation tick for the character.

    private void setAnimation() {
        int startAni = characterAction;

        if (moving) {
            characterAction = RUNNING;
        } else {
            characterAction = IDLE;
        }
        if (inAir) {
            if (airSpeed < 0) {
                characterAction = JUMP;
            } else {
                characterAction = FALLING;
            }
        }
        if (attacking) {
            characterAction = ATTACK;
            if (startAni != ATTACK) {
                aniIndex = 1;
                aniTick = 0;
                return;
            }
        }
        if (startAni != characterAction) {
            resetAniTick();
        }
    }
    //Sets the animation for the character based on whether it is moving or not, and whether it is attacking or not

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {
        moving = false;
        if (jump) {
            jump();
        }
        if (!inAir) {
            if ((!left && !right) || (right && left)) {
                return;
            }
        }
        float xSpeed = 0;
        if (left) {
            xSpeed -= characterSpeed;
            flipX = width;
            flipW = -1;
        }
        if (right) {
            xSpeed += characterSpeed;
            flipX = 0;
            flipW = 1;
        }
        if (!inAir) {
            if (!IsEntityOnGround(hitbox, lvlData)) {
                inAir = true;
            }
        }
        if (inAir) {
            if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            } else {
                hitbox.y = GetEntityYPosUdrRoofOrAbvFloor(hitbox, airSpeed);
                if (airSpeed > 0) {
                    resetInAir();
                } else {
                    airSpeed = fallSpeedAftCollision;
                }
                updateXPos(xSpeed);
            }
        } else {
            updateXPos(xSpeed);
        }
        moving = true;
    }

    private void jump() {
        if (inAir) {
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
        } else {
            hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
        }
    }

    public void changeHealth(int value) {
        currentHealth += value;

        if (currentHealth <= 0) {
            currentHealth = 0;
            //gameOver();
        } else if (currentHealth >= maxHealth) {
            currentHealth = maxHealth;
        }
    }

    private void loadAnimations() {

        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[7][8];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
            }
        }

        statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);
    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
        if (!IsEntityOnGround(hitbox, lvlData)) {
            inAir = true;
        }
    }

    public void resetDirBooleans() {
        left = false;
        up = false;
        right = false;
        down = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void resetAll(){
        resetDirBooleans();
        inAir = false;
        attacking = false;
        moving = false;
        characterAction = IDLE;
        currentHealth = maxHealth;
        // starting position
        hitbox.x = x;
        hitbox.y = y;
        
         if (!IsEntityOnGround(hitbox, lvlData))
                inAir = true;
    }

}
