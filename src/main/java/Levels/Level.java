package Levels;

import Entities.Crabby;
import static Utilities.LoadSave.GetCrabs;
import java.util.ArrayList;

/**
 * This Java code defines a class named Level with two instance variables, lvlData and crabs. Its constructor sets the lvlData instance variable and calls createEnemies() to         * initialize the crabs variable. The class also has methods to get the sprite index, lvlData, and crabs.
 * 
 */
public class Level {

    private int[][] lvlData;
    private ArrayList<Crabby> crabs;

    public Level(int[][] lvlData) {
        this.lvlData = lvlData;
        createEnemies();

    }
    //This Java code defines a class named Level with two instance variables, lvlData and crabs. Its constructor takes a 2D integer array as a parameter, sets the lvlData instance      variable, and calls the createEnemies() method, which initializes the crabs variable.
    
    public void createEnemies(){
        crabs = GetCrabs();
    }
    //a method named createEnemies() that assigns the value returned by the GetCrabs() method to the crabs instance variable.

    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }
    // getSpriteIndex() returns an integer value representing the sprite index at the specified position in the lvlData array.
    
    public int[][] getLevelData(){
        return lvlData;
    }
    //getLevelData() returns the lvlData instance variable, which is a 2D integer array.
    
    public ArrayList<Crabby> getCrabs(){
        return crabs;
    }
    // getCrabs() returns the crabs instance variable, which is an ArrayList of Crabby objects.
}
