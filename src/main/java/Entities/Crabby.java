package Entities;

import static Utilities.Constants.EnemyConstants.*;
import com.mycompany.platformgame.Game;

/**
 *
 * @author mbarb
 */
public class Crabby extends Enemy{
    
    public Crabby(float x, float y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
        initHitbox(x, y, (int)(22 * Game.SCALE), (int)(19 * Game.SCALE));
        
        
    }
    
}
