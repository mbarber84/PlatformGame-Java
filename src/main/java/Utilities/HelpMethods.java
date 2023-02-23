package Utilities;

import com.mycompany.platformgame.Game;
import java.awt.geom.Rectangle2D;

/**
 * This is a Java class called "HelpMethods" that contains various static methods for providing helpful utilities to a platform game.
 * 
 */
public class HelpMethods {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {

        if (!IsSolid(x, y, lvlData)) {
            if (!IsSolid(x + width, y + height, lvlData)) {
                if (!IsSolid(x + width, y, lvlData)) {
                    if (!IsSolid(x, y + height, lvlData)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    // checks if a rectangular object with the given coordinates and dimensions can move to the specified position in the game level. It checks if the object collides with any         solid tiles in the level data.

    private static boolean IsSolid(float x, float y, int[][] lvlData) {
        int maxWidth = lvlData[0].length * Game.TILES_SIZE;
        if (x < 0 || x >= maxWidth) {
            return true;
        }
        if (y < 0 || y >= Game.GAME_HEIGHT) {
            return true;
        }
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;
        return IsTileSolid((int)xIndex, (int)yIndex, lvlData);
    }
    //checks if a tile at the specified position in the level data is solid, i.e., if it should block the movement of the character or not.
    
    public static boolean IsTileSolid(int xTile, int yTile, int[][] lvlData){
        int value = lvlData[yTile][xTile];
        if (value >= 48 || value < 0 || value != 11) {
            return true;
        }
        return false;
    }
    //checks if a tile at the specified position in the level data is solid, i.e., if it should block the movement of the character or not. It returns true if the tile value is        greater than or equal to 48, less than 0, or not equal to 11.

    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
        if (xSpeed > 0) {
            //to the right
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;
        } else {
            //to the left
            return currentTile * Game.TILES_SIZE;
        }
    }
    // returns the x-coordinate of the closest tile to the left or right of the given hitbox that the entity can stand on, given its speed.

    public static float GetEntityYPosUdrRoofOrAbvFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
        if (airSpeed > 0) {
            //falling
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        } else {
            //Jumping
            return currentTile * Game.TILES_SIZE;
        }
    }
    //returns the y-coordinate of the closest tile above or below the given hitbox that the entity can stand on or jump on, given its air speed.

    public static boolean IsEntityOnGround(Rectangle2D.Float hitbox, int[][] lvlData) {
        //checks bottom left and right pixels
        if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData)) {
            if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData)) {
                return false;
            }
        }
        return true;
    }
    //checks if the entity represented by the given hitbox is standing on a solid tile in the level data
    
    public static boolean IsGround(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData){
        if(xSpeed > 0)
            return IsSolid(hitbox.x + hitbox.width + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
        else
            return IsSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
    }
    // checks if the ground below the entity represented by the given hitbox is solid, i.e., if it should block the movement of the character or not. It takes into account the         entity's speed.
    
    public static boolean IsAllTileWalkable(int xStart, int xEnd, int y, int[][] lvlData){
        for(int i = 0; i < xEnd - xStart; i++){
                if(IsTileSolid(xStart + i, y, lvlData))
                    return false;
                if(!IsTileSolid(xStart + i, y + 1, lvlData))
                    return false;
        }
        return true;
    //checks if all tiles in the specified range of x-coordinates and a given y-coordinate in the level data are walkable, i.e., not solid.
    }
    
    public static boolean IsClearSight(int[][] lvlData, Rectangle2D.Float firstHitbox, Rectangle2D.Float secondHitbox,int yTile){
        int firstXTile = (int)(firstHitbox.x / Game.TILES_SIZE);
        int secondXTile = (int)(secondHitbox.x / Game.TILES_SIZE);
        
        if(firstXTile > secondXTile){
                    return IsAllTileWalkable(secondXTile, firstXTile, yTile, lvlData);
        }else 
            return IsAllTileWalkable(firstXTile,secondXTile, yTile, lvlData);
    //IsClearSight - checks if there are no solid tiles between two hitboxes at the same height on a given y-coordinate in the level data. It is used to check if a character           can shoot or see through a gap in the level.
    }
}
