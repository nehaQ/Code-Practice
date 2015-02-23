import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// http://examples.javacodegeeks.com/java-basics/exceptions/java-util-concurrentmodificationexception-how-to-handle-concurrent-modification-exception/
// When i removed the alive and dead and tried to use just one i was getting a concurrent
// modification error. this may happen due to 3 reasons during iteration:
// 1. The iterator makes changes to the structure of the collection while iterating through the collection - like removing the next item
// 2. An external method makes changes to the structure of the collection
// 3. Another iterator makes changes to the structure of the collection

public class Board extends JPanel {

    public static final int CELL_SIZE = 10;
    private final int rows = 10;
    private final int cols = 20;
    ArrayList<Creature> creatureList;

    ArrayList<Creature> alive, dead;
    // ArrayList<Bacteria> bacteriaList;
    // ArrayList<Bacteria> alive_bac, dead_bac;
    Board() {
        super();
        setBackground(Color.white);
        creatureList = new ArrayList<Creature>();
        //creatureList.size();
        alive = new ArrayList<Creature>();
        dead = new ArrayList<Creature>();
        setPreferredSize(new Dimension(cols * CELL_SIZE, rows * CELL_SIZE));
        Bacteria firstBacteria = new Bacteria(this, 1, 1);
        creatureList.add(firstBacteria);
        Ant firstAnt = new Ant(this, 7, 7);
        creatureList.add(firstAnt);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Creature creature: creatureList) {
            // System.out.println(creature.y+" draw "+creature.toString());
            creature.draw(g);
        }
    }

    public void tick() {
        for (Creature creature: creatureList) {
             creature.tick();
        }
        // System.out.println("alive"+alive.size()+" "+dead.size());
        for (Creature creature: alive) {
             creatureList.add(creature);
        }
        alive.clear();
        // System.out.println("after alive"+creatureList.size());

        for (Creature creature: dead) {
            creatureList.remove(creature);
        }
        dead.clear();
        // System.out.println("after dead"+creatureList.size());

    }

    public void addBaby(Creature baby) {
        if (baby != null) alive.add(baby);
    }

    public void addDead(Creature deadCreature) {
        if (deadCreature != null) dead.add(deadCreature);
    }

    public boolean emptyCell(int x, int y) {
        // System.out.println("x "+x+" y"+y);
        if (x < 0 || y < 0 || x >= cols || y >= rows)
        {
            // System.out.println("out of board");  
            return false;
        } 

        for (Creature creature : alive) {
            if (creature.atPosition(x, y)) {
                // System.out.println("cell here");
                return false;
            }
        }

        for (Creature creature : creatureList) {
            if (creature.atPosition(x, y)) {
                // System.out.println("cell here");
                return false;
            }
        }
        
        // System.out.println("its ok ");
        return true;
    }

    public String getPopulation() {
        return String.format("%d", creatureList.size());
    }
}