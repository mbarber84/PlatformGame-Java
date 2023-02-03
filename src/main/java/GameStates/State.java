package GameStates;

import UI.MenuButton;
import com.mycompany.platformgame.Game;
import java.awt.event.MouseEvent;

/**
 *
 * @author mbarb
 */
public class State {
    
    protected Game game;
    
    public State(Game game){
        this.game = game;
    }
    
    public boolean  isInButton(MouseEvent e, MenuButton mb){
       return mb.getBounds().contains(e.getX(), e.getY());
    }
    
    public Game getGame(){
        return game; 
    }
    
}
