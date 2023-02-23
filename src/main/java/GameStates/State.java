package GameStates;

import UI.MenuButton;
import com.mycompany.platformgame.Game;
import java.awt.event.MouseEvent;

/**
 * The class has two instance variables: a protected instance of a class called "Game" called "game", and a constructor that initializes this variable.
 * 
 */
public class State {
    
    protected Game game;
    
    public State(Game game){
        this.game = game;
    }
    
    public boolean  isInButton(MouseEvent e, MenuButton mb){
       return mb.getBounds().contains(e.getX(), e.getY());
    }
    //a method that takes a MouseEvent and a MenuButton as parameters and returns a boolean value indicating whether the mouse event occurred within the boundaries of the menu            button.
    
    public Game getGame(){
        return game; 
    }
    
}
