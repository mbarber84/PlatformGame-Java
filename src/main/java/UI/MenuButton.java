package UI;

import GameStates.Gamestate;
import static Utilities.Constants.UI.Buttons.*;
import Utilities.LoadSave;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
class called "MenuButton" which represents a button on a menu screen. It has instance variables to store the button's x and y positions, its row index, its associated game state, an array of images, and flags for whether the mouse is over the button or the button is being pressed. The class has methods for initializing the button's bounds, loading the images, drawing the button, updating the button's state, and resetting the mouse flags. It also has methods for getting and setting the bounds and for applying the game state associated with the button.
 */
public class MenuButton {
    
    private int xPos;
    private int yPos;
    private int rowIndex, index;
    private int xOffsetCenter = B_WIDTH / 2;
    private Gamestate state;
    private BufferedImage[] imgs;
    private boolean mouseOver, mousePressed;
    private Rectangle Bounds;
    
    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
        initBounds();
    }
    //Constructor method to create a new MenuButton object with given x and y position, row index, and associated Gamestate.
    
    private void initBounds() {
        Bounds = new Rectangle( xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
    }
    //Method to initialize the button's bounds using Rectangle.

    private void loadImgs() {
        imgs = new BufferedImage[3];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
        for(int i = 0; i < imgs.length; i++)
           imgs[i] = temp.getSubimage( i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
    }
    // Method to load the button images from LoadSave.GetSpriteAtlas and store them in an array.
    
    public void draw(Graphics g){
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
    }
    
    public void update(){
        index = 0;
        if(mouseOver)
            index = 1;
        if(mousePressed)
            index = 2;
    }
    //Method to update the button's current state based on whether the mouse is over the button or the button is being pressed.

    public boolean isMouseOver() {
        return mouseOver;
    }
    //Getter method to check if the mouse is over the button.
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    // Setter method to set the value of mouseOver.
    public boolean isMousePressed() {
        return mousePressed;
    }
    //Getter method to check if the button is being pressed.
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    // Setter method to set the value of mousePressed.
    public Rectangle getBounds(){
        return Bounds;
    }
    //Getter method to get the bounds of the button.
    public void applyGamestate(){
        Gamestate.state = state;
    }
    //Method to apply the associated Gamestate when the button is clicked.
    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }
    //Method to reset mouseOver and mousePressed flags to false.
}
