package GameStates;

import com.mycompany.platformgame.Game;

/**
 *
 * @author mbarb
 */
public class State {
    
    protected Game game;
    
    public State(Game game){
        this.game = game;
    }
    
    
    public Game getGame(){
        return game; 
    }
    
}
