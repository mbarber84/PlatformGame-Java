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
 * @author mbarb
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
