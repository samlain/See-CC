import javax.swing.*;
import java.awt.*;

public class ZamboniMazeView {
    public JPanel cardPanel;
    public CardLayout cardLayout;
    public JButton easyButton, mediumButton, hardButton;
    private JButton returnButton;
    public JPanel mazePanel;
    private JLabel playerLabel;
    private ImageIcon playerIcon;
    public JLabel resultLabel;  // To display the score

    public ZamboniMazeView() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(createMainMenu(), "Main Menu");
        cardPanel.add(createMazeGame(), "Maze");
        cardPanel.add(createResultScreen(), "Results");
        cardLayout.show(cardPanel, "Main Menu");

        // Initialize the player icon with the scaled version of the image
        playerIcon = new ImageIcon("Images/snoopy.png");  // Replace with the actual path to your image
        playerLabel = new JLabel(playerIcon);
    }

    private JPanel createMainMenu() {
        JPanel mainMenuPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Welcome to Zamboni Maze", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JPanel rulesPanel = createRules();
        JPanel difficultyPanel = new JPanel(new GridLayout(1, 3));
        easyButton = new JButton("Easy");
        mediumButton = new JButton("Medium");
        hardButton = new JButton("Hard");
        difficultyPanel.add(easyButton);
        difficultyPanel.add(mediumButton);
        difficultyPanel.add(hardButton);
        mainMenuPanel.add(titleLabel, BorderLayout.NORTH);
        mainMenuPanel.add(rulesPanel, BorderLayout.CENTER);
        mainMenuPanel.add(difficultyPanel, BorderLayout.SOUTH);
        return mainMenuPanel;
    }

    private JPanel createRules() {
        JPanel rulesPanel = new JPanel();
        rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.Y_AXIS));
        JLabel rulesLabel = new JLabel("Rules", JLabel.CENTER);
        rulesLabel.setFont(new Font("Arial", Font.BOLD, 20));
        rulesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rulesPanel.add(rulesLabel);
        JLabel[] ruleLabels = {
                new JLabel("Navigate the maze to the goal."),
                new JLabel("Finish the maze to receive your prize."),
        };
        for (JLabel label : ruleLabels) {
            label.setFont(new Font("Arial", Font.BOLD, 20));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            rulesPanel.add(label);
        }
        return rulesPanel;
    }

    private JPanel createMazeGame() {
        mazePanel = new JPanel(new GridLayout(5, 8));  // Default to easy maze grid size
        mazePanel.setBackground(Color.WHITE);  // Background color for maze area
        return mazePanel;
    }

    public JPanel createResultScreen() {
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultLabel = new JLabel("Game Over! Your score is: 0", JLabel.CENTER);  // Initial score
        resultLabel.setFont(new Font("Arial", Font.BOLD, 24));
        returnButton = new JButton("Exit");
        returnButton.addActionListener(e -> GameController.controller.showMap());
        resultPanel.add(resultLabel, BorderLayout.CENTER);
        resultPanel.add(returnButton, BorderLayout.SOUTH);
        return resultPanel;
    }

    /**
     * Update the result screen with the player's final score.
     *
     * @param score The final score of the player.
     */
    public void updateResultScreen(int score) {
        resultLabel.setText("Game Over! Your score is: " + score);
    }

    public void updateMazeGrid(int[][] mazeLayout, int playerX, int playerY) {
        mazePanel.removeAll();  // Clear the previous maze layout
        mazePanel.setLayout(new GridLayout(mazeLayout.length, mazeLayout[0].length));  // Adjust the grid layout

        // Calculate the size of each cell
        int cellWidth = mazePanel.getWidth() / mazeLayout[0].length;
        int cellHeight = mazePanel.getHeight() / mazeLayout.length;

        // Scale the player image to fit inside the cell
        Image scaledImage = playerIcon.getImage().getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH);
        playerLabel.setIcon(new ImageIcon(scaledImage));  // Update playerLabel with the scaled image

        for (int i = 0; i < mazeLayout.length; i++) {
            for (int j = 0; j < mazeLayout[i].length; j++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setPreferredSize(new Dimension(cellWidth, cellHeight));

                if (i == 1 && j == 1) {
                    // Color the start block green
                    cellPanel.setBackground(Color.GREEN);
                } else if (i == mazeLayout.length - 2 && j == mazeLayout[0].length - 2) {
                    // Color the end block red
                    cellPanel.setBackground(Color.RED);
                } else if (mazeLayout[i][j] == 1) {
                    // Wall
                    cellPanel.setBackground(Color.BLACK);
                } else {
                    // Path
                    cellPanel.setBackground(Color.WHITE);
                }

                // Add the player at the current position
                if (i == playerX && j == playerY) {
                    cellPanel.add(playerLabel);  // Add the player's icon
                }

                mazePanel.add(cellPanel);  // Add each cell to the grid
            }
        }
        mazePanel.revalidate();  // Refresh the panel to show changes
        mazePanel.repaint();
    }

    public JPanel getPanel() {
        return cardPanel;
    }
}