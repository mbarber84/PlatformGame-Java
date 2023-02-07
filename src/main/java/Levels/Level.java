package Levels;

import Entities.Crabby;
import static Utilities.LoadSave.GetCrabs;
import java.util.ArrayList;

/**
 *
 * @author mbarb
 */
public class Level {

    private int[][] lvlData;
    private ArrayList<Crabby> crabs;

    public Level(int[][] lvlData) {
        this.lvlData = lvlData;
        createEnemies();

    }
    
    public void createEnemies(){
        crabs = GetCrabs();
    }

    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }
    
    public int[][] getLevelData(){
        return lvlData;
    }
    
    public ArrayList<Crabby> getCrabs(){
        return crabs;
    }

}
