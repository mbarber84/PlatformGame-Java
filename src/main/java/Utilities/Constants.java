package Utilities;

import com.mycompany.platformgame.Game;

/**
 *
 * @author mbarb
 * The class contains several static nested classes that define constants used in a platform video game.
 * The constants include values for character actions, enemy types, and user interface elements such as button sizes.
 * The code also includes a method for getting the number of sprites for each action and another for getting the health and damage values for each enemy type.
 */
public class Constants {
    
    public static class EnemyConstants{
        public static final int CRABBY = 0;
        
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;
        public static final int HIT = 3;
        public static final int DEAD = 4;
        
        public static final int CRABBY_WIDTH_DEFAULT = 72;
        public static final int CRABBY_HEIGHT_DEFAULT = 32;
        
        public static final int CRABBY_WIDTH = (int)(CRABBY_WIDTH_DEFAULT * Game.SCALE);
        public static final int CRABBY_HEIGHT = (int)(CRABBY_HEIGHT_DEFAULT * Game.SCALE);
        
        public static final int CRABBY_DRAWOFFSET_X = (int)(26 * Game.SCALE);
        public static final int CRABBY_DRAWOFFSET_Y = (int)(9 * Game.SCALE); 
        
        public static int GetSpriteAmount(int eneny_type, int enemy_state){
            
            switch (eneny_type) {
                case CRABBY:
                    switch(enemy_state){
                        case IDLE:
                            return 9;
                        case RUNNING:
                            return 6;
                        case ATTACK:
                            return 7;
                        case HIT:
                            return 4;
                        case DEAD:
                            return 5;
                    }
            }
            return 0;
        }
        
        public static int GetMaxHealth(int enemy_type){
            switch(enemy_type){
            case CRABBY:
            return 10;
            default:
                return 1;
            }
        }
        
        public static int GetEnemyDmg(int enemy_type){
             switch(enemy_type){
            case CRABBY:
            return 15;
            default:
                return 0;
            }
        }  
    }
    
    public static class UI{
        public static class Buttons{
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int)(B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGHT = (int)(B_HEIGHT_DEFAULT * Game.SCALE);
        }
        public static class PauseButtons{
            public static final int SOUND_SIZE_DEFAULT = 42;
            public static final int SOUND_SIZE = (int)(SOUND_SIZE_DEFAULT * Game.SCALE);
        }
        public static class URMButtons{
            public static final int URM_SIZE_DEFAULT = 56;
            public static final int URM_SIZE = (int)(URM_SIZE_DEFAULT * Game.SCALE);
        }
        public static class VolumeButton{
            public static final int VOLUME_WIDTH_DEFAULT = 28;
            public static final int VOLUME_HEIGHT_DEFAULT = 44;
            public static final int SLIDER_WIDTH_DEFAULT = 215;
            
            public static final int VOLUME_WIDTH = (int)(VOLUME_WIDTH_DEFAULT * Game.SCALE);
            public static final int VOLUME_HEIGHT = (int)(VOLUME_HEIGHT_DEFAULT * Game.SCALE);
            public static final int SLIDER_WIDTH = (int)(SLIDER_WIDTH_DEFAULT * Game.SCALE);  
        }
    }

    public static class Direction {

        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class CharacterConstants {

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int ATTACK = 4;
        public static final int HIT = 5;
        public static final int DEAD = 6;


        public static int GetSpriteAmount(int character_action) { //Upercase G due to it being a static method

            switch (character_action) {
                case DEAD:
                    return 8;
                case RUNNING:
                    return 6;
                case IDLE:
                    return 5;
                case HIT:
                    return 4;
                case JUMP:
                case ATTACK:
                    return 3;
                case FALLING:
                default:
                    return 1;
            }
        }
    }
}
