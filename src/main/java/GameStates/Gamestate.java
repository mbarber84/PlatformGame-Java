package GameStates;

/**
 * an enum called Gamestate, which has four possible values: PLAYING, MENU, OPTIONS, and QUIT. It also defines a static variable state initialized to MENU, indicating the current     * state of the game.
 *
 */
public enum Gamestate {
    
    PLAYING, MENU, OPTIONS, QUIT;
    
    public static Gamestate state = MENU;
}
