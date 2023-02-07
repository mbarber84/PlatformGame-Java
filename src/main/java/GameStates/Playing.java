package GameStates;

import Entities.Character;
import Entities.EnemyController;
import Levels.LevelController;
import UI.GameOverOverlay;
import UI.LevelCompletedOverlay;
import UI.PauseOverlay;
import Utilities.LoadSave;
import com.mycompany.platformgame.Game;
import static com.mycompany.platformgame.Game.SCALE;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author mbarb
 */
public class Playing extends State implements Statemethods {

    private Character character;
    private LevelController levelController;
    private EnemyController enemyController;
    private PauseOverlay pauseOverlay;
    private GameOverOverlay gameOverOverlay;
    private LevelCompletedOverlay levelCompletedOverlay;
    private boolean paused = false;
    
    private int xlvlOffset;
    private int leftBorder = (int)(0.2 * Game.GAME_WIDTH);
    private int rightBorder = (int)(0.8 * Game.GAME_WIDTH);
    private int lvlTilesWide = LoadSave.GetLevelData()[0].length;
    private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
    private int maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE;
    
    private boolean gameOver;
    private boolean lvlCompleted = false;

    public Playing(Game game) {
        super(game);
        initClasses();
    }
    

    private void initClasses() {
        levelController = new LevelController(game);
        enemyController = new EnemyController(this);
        character = new Entities.Character(200, 200, (int) (64 * SCALE), (int) (40 * SCALE), this);
        character.loadLvlData(levelController.getCurrentLevel().getLevelData());
        pauseOverlay = new PauseOverlay(this);
        gameOverOverlay = new GameOverOverlay(this);
        levelCompletedOverlay = new LevelCompletedOverlay(this);
    }

    @Override
    public void update() {
        if(paused){
            pauseOverlay.update();
        }else if(lvlCompleted){
            levelCompletedOverlay.update();
        }else if(!gameOver){
           levelController.update();
           character.update();
           enemyController.update(levelController.getCurrentLevel().getLevelData(), character);
           CheckBorderProximity();
        } 
    }
    
    private void CheckBorderProximity() {
        int characterX = (int)character.getHitbox().x;
        int diff = characterX - xlvlOffset;
        
        if(diff > rightBorder)
            xlvlOffset += diff - rightBorder;
        else if(diff < leftBorder)
            xlvlOffset += diff - leftBorder;
        
        if(xlvlOffset > maxLvlOffsetX)
            xlvlOffset = maxLvlOffsetX;
        else if(xlvlOffset < 0)
            xlvlOffset = 0;
    }


    @Override
    public void draw(Graphics g) {
        levelController.draw(g, xlvlOffset);
        character.render(g, xlvlOffset);
        enemyController.draw(g, xlvlOffset);
        
       if(paused){
          g.setColor(new Color(48, 25, 52, 200));
          g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
          pauseOverlay.draw(g);
       }else if(gameOver)
           gameOverOverlay.draw(g);
       else if(lvlCompleted)
           levelCompletedOverlay.draw(g);
    }
    
    public void resetAll(){
        //Reset game
        gameOver = false;
        paused = false;
        character.resetAll();
        enemyController.resetAllEnemies();
    }
    
    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
    }
    
    public void checkEnemyHit(Rectangle2D.Float attackBox){
        enemyController.checkEnemyHit(attackBox);
    }
    
    public void mouseDragged(MouseEvent e){
        if(!gameOver)
            if(paused)
                pauseOverlay.mouseDragged(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!gameOver)
            if (e.getButton() == MouseEvent.BUTTON1) {
            character.setAttacking(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(!gameOver){
            if(paused)
                pauseOverlay.mousePressed(e);
            else if(lvlCompleted)
                levelCompletedOverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(!gameOver){
            if(paused)
                pauseOverlay.mouseReleased(e);
            else if(lvlCompleted)
                levelCompletedOverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(!gameOver){
            if(paused)
                pauseOverlay.mouseMoved(e);
            else if(lvlCompleted)
                levelCompletedOverlay.mouseMoved(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(gameOver)
           gameOverOverlay.KeyPresses(e);
        else
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
                    paused = !paused;
                    break;
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!gameOver)
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
    
    public void setLevelCompleted(boolean levelCompleted){
        this.lvlCompleted = levelCompleted;
    }
    
    public void unpauseGame() {
        paused = false;
    }

    public void windowFocusLost() {
        character.resetDirBooleans();
    }

    public Character getCharacter() {
        return character;
    }
    public EnemyController getEnemyController(){
        return enemyController;
    }
}
