package GameStates;

import Entities.Character;
import Levels.LevelController;
import UI.PauseOverlay;
import com.mycompany.platformgame.Game;
import static com.mycompany.platformgame.Game.SCALE;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author mbarb
 */
public class Playing extends State implements Statemethods {

    private Character character;
    private LevelController levelController;
    private PauseOverlay pauseOverlay;
    private boolean paused = true;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelController = new LevelController(game);
        character = new Entities.Character(200, 200, (int) (64 * SCALE), (int) (40 * SCALE));
        character.loadLvlData(levelController.getCurrentLevel().getLevelData());
        pauseOverlay = new PauseOverlay();
    }

    @Override
    public void update() {
        levelController.update();
        character.update();
        
        pauseOverlay.update();
    }

    @Override
    public void draw(Graphics g) {
        levelController.draw(g);
        character.render(g);
        pauseOverlay.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            character.setAttacking(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(paused)
            pauseOverlay.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(paused)
            pauseOverlay.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(paused)
            pauseOverlay.mouseMoved(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                character.setLeft(true);
                break;
            case KeyEvent.VK_D:
                character.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                character.setJump(true);
                break;
            case KeyEvent.VK_BACK_SPACE:
                Gamestate.state = Gamestate.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                character.setLeft(false);
                break;
            case KeyEvent.VK_D:
                character.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                character.setJump(false);
                break;
        }
    }

    public void windowFocusLost() {
        character.resetDirBooleans();
    }

    public Character getCharacter() {
        return character;
    }
}
