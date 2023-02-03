package Entities;

import static Utilities.Constants.CharacterConstants.ATTACK_1;
import static Utilities.Constants.CharacterConstants.GetSpriteAmount;
import static Utilities.Constants.CharacterConstants.IDLE;
import static Utilities.Constants.CharacterConstants.RUNNING;
import Utilities.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author mbarb
 */
public class Character extends Entity {

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int characterAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private float characterSpeed = 2.0f;

    public Character(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
    }

    public void update() {

        updatePos();
        updateHitbox();
        updateAnimationTick();
        setAnimation();

    }

    public void render(Graphics g) {
        g.drawImage(animations[characterAction][aniIndex], (int) x, (int) y, width, height, null);
        drawHitbox(g);
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(characterAction)) {
                aniIndex = 0;
                attacking = false;
            }
        }

    }

    private void setAnimation() {

        int startAni = characterAction;

        if (moving) {
            characterAction = RUNNING;
        } else {
            characterAction = IDLE;
        }

        if (attacking) {
            characterAction = ATTACK_1;
        }

        if (startAni != characterAction) {
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {

        moving = false;

        if (left && !right) {
            x -= characterSpeed;
            moving = true;
        } else if (right && !left) {
            x += characterSpeed;
            moving = true;
        }

        if (up && !down) {
            y -= characterSpeed;
            moving = true;
        } else if (down && !up) {
            y += characterSpeed;
            moving = true;
        }
    }

    private void loadAnimations() {

        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[9][6];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
            }
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

}

