package GameStates;

import UI.MenuButton;
import Utilities.LoadSave;
import com.mycompany.platformgame.Game;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * class named "Menu" that extends a parent class named "State" and implements an interface named "Statemethods". The class contains methods for loading background images and       * buttons, updating and drawing the menu screen, and handling user input events such as mouse clicks, releases, and movements, and key presses. It also uses other classes and      * methods for getting sprite images and applying game states.
 * 
 */
public class Menu extends State implements Statemethods {

    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage backgroundImg, backgroundImgStartMenu;
    private int menuX, menuY, menuWidth, menuHeight;

    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
        backgroundImgStartMenu = LoadSave.GetSpriteAtlas(LoadSave.START_MENU_BACKGROUND);
    }
    
     private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
        menuWidth = (int)(backgroundImg.getWidth() * Game.SCALE);
        menuHeight = (int)(backgroundImg.getHeight()* Game.SCALE);
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int)(45 * Game.SCALE);
    }
     //Loads the background image for the menu screen and sets its dimensions and position.

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220 * Game.SCALE), 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 2, Gamestate.QUIT);
    }
    //Loads the menu buttons and sets their positions and corresponding game states.

    @Override
    public void update() {
        for (MenuButton mb : buttons) {
            mb.update();
        }
    }
    //Updates the state of the menu buttons.

    @Override
    public void draw(Graphics g) {
        
        g.drawImage(backgroundImgStartMenu, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);
        
        for (MenuButton mb : buttons) {
            mb.draw(g);
        }
    }
    //Draws the menu screen with the background image and menu buttons.

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isInButton(e, mb)) {
                mb.setMousePressed(true);
                break;
            }
        }
    }
    // Handles user input when the mouse button is pressed by setting a button to the pressed state.

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isInButton(e, mb)) {
                if (mb.isMousePressed()) {
                    mb.applyGamestate();
                }
                break;
            }
        }
        resetButtons();
    }
    //Handles user input when the mouse button is released by applying the corresponding game state for the pressed button and resetting all button states.

    private void resetButtons() {
        for (MenuButton mb : buttons) {
            mb.resetBools();
        }
    }
    //Resets all button states to the unpressed state.

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : buttons) {
            mb.setMouseOver(false);
        }

        for (MenuButton mb : buttons) {
            if (isInButton(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
        }
    }
    // Handles user input when the mouse is moved by setting the appropriate button to the mouse over state.

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Gamestate.state = Gamestate.PLAYING;
        }
    }
    //Handles user input when a key is pressed by setting the game state to playing if the enter key is pressed.

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
