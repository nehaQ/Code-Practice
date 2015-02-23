import java.awt.*;
import java.util.Random;


public class Ant extends Creature{
    private final int MAX_AGE = 6;
    private final int COLOR_STEP = 255;

    Ant(Board board, int _x, int _y) {
        this.board = board;
        //initialize age, x, y
        age = 1;
        x = _x;
        y = _y;
    }


    //Draw a circle representing the creature at its position
    //This function is done.
    public void draw(Graphics g) {
        g.setColor(new Color(COLOR_STEP/age, 0, 0));
        g.fillOval(x * board.CELL_SIZE, y * board.CELL_SIZE, board.CELL_SIZE, board.CELL_SIZE);
    }

    public void tick() {
        //your code
        // this is how the creature lives in one tick
        // make the creature get older if it is not dead.
        // if it's got older than MAX_AGE then it should die
        // ...call board.addDead() to register the dead creature
        // if it can have baby then try reproduce()
        age++;
        if(age > MAX_AGE)
            board.addDead(this);
        reproduce();
    }

    public void reproduce() {
        //your code
        // if it can have a baby then produce a baby
        // ...in an empty cell next to its cell.
        // board.emptyCell() should be used
        // ....to check whether a cell in the board is empty
        // and call board.addBaby() to place the baby to the board
        //int x_current = x, y_current = y;
        Random rand = new Random();
        int tries = 0;
        while(tries <= 8)
        {
            int randomX = rand.nextInt(((x+1) - (x-1)) + 1) + (x-1);
            int randomY = rand.nextInt(((y+1) - (y-1)) + 1) + (y-1);
            if(board.emptyCell(randomX, randomY))
            {
                Ant baby = new Ant(board, randomX, randomY);
                board.addBaby(baby);
                return;  
            }
            tries++;

        }
    }
}


