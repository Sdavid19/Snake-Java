package view;

import model.highscore.HighScore;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class HighScoresDialog {

    public static void showDialog(JFrame parent, String playerName, int playerScore, List<HighScore> topScores, Runnable restartGame) {
        JDialog dialog = new JDialog(parent, "Game Over", true);
        dialog.setLayout(new BorderLayout());
        dialog.setPreferredSize(new Dimension(400, 500));

        // Cím panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("High Scores");
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        dialog.add(titlePanel, BorderLayout.NORTH);

        // Lista panel
        JPanel scoresPanel = new JPanel();
        scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.Y_AXIS));
        scoresPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Aktuális játékos
        JLabel currentPlayerLabel = new JLabel(playerName + ": " + playerScore);
        currentPlayerLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        currentPlayerLabel.setForeground(Color.BLUE);
        currentPlayerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoresPanel.add(currentPlayerLabel);
        scoresPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Top10 lista
        int rank = 1;
        for (HighScore hs : topScores) {
            String text = rank + ". " + hs.getName() + ": " + hs.getScore();
            JLabel label = new JLabel(text);
            label.setFont(new Font("Monospaced", Font.PLAIN, 16));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            scoresPanel.add(label);
            rank++;
            if (rank > 10) break; // csak top10
        }

        JScrollPane scrollPane = new JScrollPane(scoresPanel);
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Gombok
        JPanel buttonPanel = new JPanel();
        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> {
            dialog.dispose();
            restartGame.run();
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(restartButton);
        buttonPanel.add(exitButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }
}
