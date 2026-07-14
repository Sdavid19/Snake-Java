package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyListener;

public class SnakeView extends JFrame {
    private static final int CELL_SIZE = 22;
    private static JPanel grid;
    private final JPanel topPanel;

    private int score = 0;

    public SnakeView(int rows, int cols, KeyListener keyListener, String playerName) {
        setTitle("Snake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        topPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setFont(getPixelFont(15f));
                g.setColor(Color.BLACK);

                FontMetrics fm = g.getFontMetrics();

                String scoreText = "Score: " + score;
                int scoreHeight = fm.getAscent();
                int y = (getHeight() + scoreHeight) / 2 - 2;
                g.drawString(scoreText, 10, y);

                String playerText = playerName;
                int playerWidth = fm.stringWidth(playerText);
                g.drawString(playerText, getWidth() - playerWidth - 10, y);
            }
        };

        topPanel.setPreferredSize(new Dimension(cols * CELL_SIZE, 40));
        topPanel.setBackground(new Color(160, 140, 100));
        add(topPanel, BorderLayout.NORTH);

        // Grid
        grid = new JPanel(new GridLayout(rows, cols, 0, 0));
        grid.setBackground(new Color(160, 140, 100));
        for (int i = 0; i < rows * cols; i++) {
            SquarePanel squarePanel = new SquarePanel();
            grid.add(squarePanel);
        }

        grid.setBorder(new LineBorder(new Color(160, 140, 100), 8));

        add(grid, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        this.addKeyListener(keyListener);
    }

    private Font getPixelFont(float size) {
        return new Font("Monospaced", Font.BOLD, (int) size);
    }

    public void setScore(int score) {
        this.score = score;
        topPanel.repaint();
    }

    public void setCell(int x, int y) {
        Color color = new Color(19, 188, 19);
        grid.getComponent((17 * x) + y).setBackground(color);
    }

    public void deleteCell(int row, int col){
        int index = row * 17 + col;
        grid.getComponent(index).setBackground(new Color(237, 201, 175));
        ((SquarePanel)grid.getComponent(index)).setBorder(new LineBorder(Color.DARK_GRAY));
    }

    public void setAppleCell(int x, int y){
        grid.getComponent((17 * x) + y).setBackground(Color.RED);
    }

    public void setRockCell(int x, int y) {
        grid.getComponent((17 * x) + y).setBackground(new Color(160, 140, 100));
    }

    private static class SquarePanel extends JPanel {
        SquarePanel() {
            setBackground(new Color(237, 201, 175));
            setBorder(new LineBorder(Color.DARK_GRAY));
            setOpaque(true);
            setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        }
    }
}
