import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// extend: change lifespan of ant and bacteria
// extend: in the current case bacteria reproduces first and thus gets 
// a better choice in mating ground. Make who gets to reproduce first
// random
// originally from : http://stackoverflow.com/questions/27591991/java-error-for-beginner/27593310#27593310


public class BacteriaSimulator extends JFrame {
    private Board board;
    private JLabel label;

    public BacteriaSimulator() {
        initUI();
        setTitle("Bacteria Simulator");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initUI() {
        JPanel panel = new JPanel();

        board = new Board();
        panel.add(board);

        label = new JLabel(board.getPopulation());
        panel.add(label);

        JButton button = new JButton("Tick");
        button.addActionListener(new ButtonNextListener());
        panel.add(button);

        add(panel);
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                BacteriaSimulator ex = new BacteriaSimulator();
                ex.setVisible(true);
            }
        });
    }

    private class ButtonNextListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.tick();
            label.setText(board.getPopulation());
            repaint();
        }
    }
}