package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author mbarb
 */
public abstract class Entity { //abstract class = a class you can not create an object of.

    protected float x, y;
    protected int width, height;
    protected Rectangle hitbox;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initHitbox();
    }
    
    protected void drawHitbox(Graphics g){
        //To allow for better debugging on hitbox
        g.setColor(Color.red);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        
    }

    private void initHitbox() {
        hitbox = new Rectangle((int)x, (int)y, width, height);
    }
    
    protected void updateHitbox(){
        hitbox.x = (int)x;
        hitbox.y = (int)y;
    }
    
    public Rectangle getHitbox(){
        return hitbox;
    }

}
