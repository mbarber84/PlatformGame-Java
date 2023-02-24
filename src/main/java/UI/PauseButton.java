package UI;

import java.awt.Rectangle;

/**
a class called PauseButton that has instance variables for the x and y coordinates, width, and height of the button, as well as a Rectangle object representing the bounds of the button. It has a constructor that takes in these parameters and creates a new Rectangle object based on them. The class also has methods for getting and setting the values of these instance variables and for retrieving the bounds of the button.
 */
public class PauseButton {
    
    protected int x, y, width, height;
    protected Rectangle bounds;
    
    public PauseButton(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        createBounds();
    }
    // a constructor that creates a new PauseButton object with the specified x and y coordinates, width, and height.

    private void createBounds() {
        bounds = new Rectangle(x, y, width, height);
    }
    //a private method that creates a new Rectangle object based on the x, y, width, and height instance variables.

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    //getX, setX: methods for getting and setting the x coordinate of the button.

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    // methods for getting and setting the y coordinate of the button.

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    //getWidth, setWidth: methods for getting and setting the width of the button.
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    //getHeight, setHeight: methods for getting and setting the height of the button.
    public Rectangle getBounds() {
        return bounds;
    }
    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    } 
    //getBounds, setBounds: methods for getting and setting the bounds of the button as a Rectangle object.
}
