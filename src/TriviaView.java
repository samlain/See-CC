import javax.swing.*;
import java.awt.*;

/**
 * The TriviaView class is responsible for creating and managing the UI components
 * for the trivia game. It provides panels for the main menu, the trivia game, and the
 * results screen. The view manages the layout and updates based on user interaction.
 */
public class TriviaView {
    public JPanel cardPanel;  // Main card panel containing different views (menu, trivia, results)
    public CardLayout cardLayout;  // Layout manager to switch between different game views
    private JLabel questionLabel;  // Label to display the current trivia question
    public JTextField answerField;  // Text field for player to input their answer
    public JButton submitButton;  // Button to submit the player's answer
    public JButton easyButton, mediumButton, hardButton;  // Buttons to select game difficulty
    private JButton returnButton;  // Button to return to the main menu or exit
    PlayerModel player;  // Reference to the player model for tracking player's information

    /**
     * Constructor for the TriviaView.
     * Initializes the card layout and adds the main menu, trivia, and result screens to the card panel.
     *
     * @param player The PlayerModel instance, passed for use in the trivia game UI.
     */
    public TriviaView(PlayerModel player) {
        this.player = player;
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(createMainMenu(), "Main Menu");
        cardPanel.add(createTriviaGame(), "Trivia");
        cardPanel.add(createResultScreen(0), "Results");  // Default to 0 points for now
        cardLayout.show(cardPanel, "Main Menu");  // Start by showing the main menu
    }

    /**
     * Creates the main menu panel, which allows the user to choose the difficulty level.
     *
     * @return JPanel representing the main menu.
     */
    private JPanel createMainMenu() {
        JPanel mainMenuPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Welcome to Trivia", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel rulesPanel = createRules();  // Rules panel with game instructions
        JPanel difficultyPanel = new JPanel(new GridLayout(1, 4));  // Panel for difficulty buttons
        easyButton = new JButton("Easy");
        mediumButton = new JButton("Medium");
        hardButton = new JButton("Hard");
        returnButton = new JButton("Exit");

        // Action listener to return to main menu and show game map
        returnButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "Main Menu");
            GameController.controller.showMap();
        });

        difficultyPanel.add(easyButton);
        difficultyPanel.add(mediumButton);
        difficultyPanel.add(hardButton);
        difficultyPanel.add(returnButton);

        mainMenuPanel.add(titleLabel, BorderLayout.NORTH);
        mainMenuPanel.add(rulesPanel, BorderLayout.CENTER);
        mainMenuPanel.add(difficultyPanel, BorderLayout.SOUTH);

        return mainMenuPanel;
    }

    /**
     * Creates a rules panel displaying the game rules.
     *
     * @return JPanel containing the game rules.
     */
    private JPanel createRules() {
        JPanel rulesPanel = new JPanel();
        rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.Y_AXIS));  // Vertical layout for rules
        JLabel rulesLabel = new JLabel("Rules", JLabel.CENTER);
        rulesLabel.setFont(new Font("Arial", Font.BOLD, 20));
        rulesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        rulesPanel.add(rulesLabel);

        // Array of rule labels explaining the scoring system
        JLabel[] ruleLabels = {
                new JLabel("Answer each question correctly to get points"),
                new JLabel("For easy mode you will get 10 points for every correct answer"),
                new JLabel("For medium mode you will get 20 points for every correct answer"),
                new JLabel("For hard mode you will get 30 points for every correct answer"),
                new JLabel("Answer any questions incorrectly you will lose points"),
                new JLabel("For easy mode you will lose 5 points for every incorrect answer"),
                new JLabel("For medium mode you will lose 10 points for every incorrect answer"),
                new JLabel("For hard mode you will lose 15 points for every incorrect answer"),
        };

        for (JLabel label : ruleLabels) {
            label.setFont(new Font("Arial", Font.BOLD, 20));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            rulesPanel.add(label);
        }

        return rulesPanel;
    }

    /**
     * Creates the trivia game panel with a question label, answer field, and submit button.
     *
     * @return JPanel representing the trivia game screen.
     */
    private JPanel createTriviaGame() {
        JPanel triviaPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel();

        questionLabel = new JLabel("Question: ");  // Label for displaying the current question
        answerField = new JTextField(20);  // Input field for entering the answer
        submitButton = new JButton("Submit");  // Button to submit the answer

        inputPanel.add(questionLabel);
        inputPanel.add(answerField);
        inputPanel.add(submitButton);

        triviaPanel.add(inputPanel, BorderLayout.CENTER);

        return triviaPanel;
    }

    /**
     * Creates the result screen panel displaying the points earned in the current game session.
     *
     * @param gamePoints The points the player earned during the current game.
     * @return JPanel representing the result screen.
     */
    public JPanel createResultScreen(int gamePoints) {
        JPanel resultPanel = new JPanel(new BorderLayout());

        // Label displaying the player's final score
        JLabel resultLabel = new JLabel("Game Over! You earned: " + gamePoints + " points in this session.", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 24));

        returnButton = new JButton("Exit");

        // Action listener to return to main menu and show game map
        returnButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "Main Menu");
            GameController.controller.showMap();
        });

        resultPanel.add(resultLabel, BorderLayout.CENTER);
        resultPanel.add(returnButton, BorderLayout.SOUTH);

        return resultPanel;
    }

    /**
     * Updates the question label with the provided question text.
     *
     * @param question The current trivia question.
     */
    public void updateQuestion(String question) {
        questionLabel.setText("Question: " + question);
    }

    /**
     * Returns the card panel containing all the game screens (main menu, trivia, results).
     *
     * @return JPanel containing the card layout for the trivia game.
     */
    public JPanel getPanel() {
        return cardPanel;
    }
}
