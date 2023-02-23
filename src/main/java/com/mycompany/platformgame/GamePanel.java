package com.mycompany.platformgame;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import static com.mycompany.platformgame.Game.GAME_HEIGHT;
import static com.mycompany.platformgame.Game.GAME_WIDTH;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 *
 * The GamePanel class in the package com.mycompany.platformgame, which is a subclass of JPanel from the javax package.
 * The GamePanel class serves as a view in the game, which is responsible for displaying the game state on the screen.
 * The GamePanel constructor takes a Game object as an argument and sets it as a member variable.
 * The class sets the size of the panel using the setPanelSize method and sets the preferred size to be the GAME_WIDTH and GAME_HEIGHT constants defined in the Game class.
 * Additionally, the class adds keyboard and mouse listeners to receive inputs, and overrides the paintComponent method to render the game by calling the render method on the game object.
 * The updateGame method is used to update the game state, although it is currently empty. The getGame method returns the game member variable.
 */
public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game) {

        mouseInputs = new MouseInputs(this);
        this.game = game;

        addKeyListener(new KeyboardInputs(this));
        setPanelSize();
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void updateGame() {

    }

    public Game getGame() {
        return game;
    }

    //paintComponent never gets called directly - it is call when the play button is used.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.render(g);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("SIZE : " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }

}
