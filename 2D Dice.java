
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class dice extends JFrame implements ActionListener {

    private DicePanel dicePanel;
    private JButton rollButton;

    public dice() {
        setTitle("2D Dice Rolling Simulator");
        setSize(300, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        dicePanel = new DicePanel();
        dicePanel.setPreferredSize(new Dimension(200, 200));
        dicePanel.setBackground(Color.WHITE);

        rollButton = new JButton("Roll Dice");
        rollButton.addActionListener(this);

        add(dicePanel, BorderLayout.CENTER);
        add(rollButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random rand = new Random();
        int value = rand.nextInt(6) + 1;
        dicePanel.setDiceValue(value);
    }

    public static void main(String[] args) {
        new dice();
    }
}

class DicePanel extends JPanel {
    private int diceValue = 0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(255, 228, 181)); 
        g2.fillRoundRect(50, 50, 100, 100, 20, 20);

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(50, 50, 100, 100, 20, 20);

        if (diceValue > 0) {
            drawPips(g2, diceValue);
        }
    }

    private void drawPips(Graphics2D g2, int value) {
        g2.setColor(Color.BLACK);
        int[][] positions = {
            {100, 100}, // center
            {75, 75},   // top left
            {125, 125}, // bottom right
            {125, 75},  // top right
            {75, 125},  // bottom left
            {75, 100},  // middle left
            {125, 100}  // middle right
        };

        switch (value) {
            case 1:
                drawDot(g2, positions[0]);
                break;
            case 2:
                drawDot(g2, positions[1]);
                drawDot(g2, positions[2]);
                break;
            case 3:
                drawDot(g2, positions[1]);
                drawDot(g2, positions[0]);
                drawDot(g2, positions[2]);
                break;
            case 4:
                drawDot(g2, positions[1]);
                drawDot(g2, positions[2]);
                drawDot(g2, positions[3]);
                drawDot(g2, positions[4]);
                break;
            case 5:
                drawDot(g2, positions[1]);
                drawDot(g2, positions[2]);
                drawDot(g2, positions[0]);
                drawDot(g2, positions[3]);
                drawDot(g2, positions[4]);
                break;
            case 6:
                drawDot(g2, positions[1]);
                drawDot(g2, positions[2]);
                drawDot(g2, positions[3]);
                drawDot(g2, positions[4]);
                drawDot(g2, positions[5]);
                drawDot(g2, positions[6]);
                break;
        }
    }

    private void drawDot(Graphics2D g2, int[] pos) {
        g2.fillOval(pos[0] - 5, pos[1] - 5, 10, 10);
    }

    public void setDiceValue(int val) {
        this.diceValue = val;
        repaint();
    }
}
