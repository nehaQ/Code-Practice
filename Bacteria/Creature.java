import java.awt.*;

public class Creature {
    //public static final int MAX_AGE = 5;
    public Board board;

    public int age; // age of the creature measures in ticks
    public int x; // (x,y) is the position of the creature
    public int y;

        //Draw a circle representing the creature at its position
    //This function is done.
    public void draw(Graphics g){}

    public void tick(){}

    public boolean atPosition(int _x, int _y) {
        //your code
        // return true if the creature is at the position (_x,_y)
        // you need this function for board.empty to function correctly
        if(_x == x && _y == y){
            return true;
        }
        return false;
    }
}
