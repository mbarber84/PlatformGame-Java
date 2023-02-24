package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

/**
 * an abstract class named Entity that provides common properties and methods for game entities. It includes fields for x, y, width, height, and a hitbox object. It also includes    * methods to draw the hitbox and initialize the hitbox.
 *
 */
public abstract class Entity { //abstract class = a class you can not create an object of.

    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    // a constructor method that initializes the fields x, y, width, and height with the given values.
    
    protected void drawHitbox(Graphics g, int xLvlOffset){
        //To allow for better debugging on hitbox
        g.setColor(Color.red);
        g.drawRect((int)hitbox.x - xLvlOffset, (int)hitbox.y, (int)hitbox.width, (int)hitbox.height);  
    }
    //a method that takes a Graphics object and an x offset as parameters, sets the color to red, and draws a rectangle at the position of the hitbox with the given x offset.

    protected void initHitbox(float x, float y, int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }
    //initializes the hitbox object with the given position and size values.
    public Rectangle2D.Float getHitbox(){
        return hitbox;
    }
}
