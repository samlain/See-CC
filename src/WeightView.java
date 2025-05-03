import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The WeightView class handles the graphical user interface (GUI) for the weightlifting game.
 * It creates panels for the main menu, game view, and result screen. It also manages the game loop,
 * key bindings, and handles user input for controlling the barbell during the game.
 */
public class WeightView {
    JPanel cardPanel;  // Main panel containing the game views
    CardLayout cardLayout;  // Layout for switching between the different views (menu, game, results)

    // Barbell and timer UI elements
    private JLabel barbell, timerLabel, resultLabel;

    // Coordinates for the barbell's target zone (top and bottom Y-axis values)
    private final int zoneTopY = 100;
    private final int zoneBottomY = 150;

    // Variables to track game state (e.g., whether space is pressed, whether the barbell is in the target zone)
    private boolean spacePressed = false;
    private boolean inZone = false;

    // Timer for controlling the game loop
    Timer gameTimer;

    // Variables for tracking time and points
    private long startTime;
    private int points;

    // Difficulty buttons and return button
    public JButton easyButton, mediumButton, hardButton, returnButton;

    // Strategy for controlling game difficulty
    private WeightGameStrategy strategy;

    // Player model and controller
    PlayerModel player;
    boolean startRun = false;
    public int totalPoints;
    private WeightController controller;

    /**
     * Constructor for the WeightView class.
     * Initializes the card layout and sets up the views for the weightlifting game.
     *
     * @param player The PlayerModel object for tracking player points.
     * @param controller The WeightController object to interact with the game logic.
     */
    public WeightView(PlayerModel player, WeightController controller) {
        this.player = player;
        this.controller = controller;
        strategy = new WeightGameStrategy();

        // Set up the card layout and initialize the panels
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(createMainMenu(), "Main Menu");
        cardPanel.add(createWeightGame(), "Weight Game");
        cardPanel.add(createResultScreen(), "Results");

        // Show the main menu first
        cardLayout.show(cardPanel, "Main Menu");
    }

    /**
     * Creates the main menu panel with difficulty selection and the exit button.
     *
     * @return The JPanel representing the main menu.
     */
    private JPanel createMainMenu() {
        JPanel mainMenuPanel = new JPanel(new BorderLayout());

        // Set up title for the main menu
        JLabel titleLabel = new JLabel("CAN YOU LIFT????", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Create the rules panel and difficulty buttons
        JPanel rulesPanel = createRules();
        JPanel difficultyPanel = new JPanel(new GridLayout(1, 3));

        easyButton = new JButton("Easy");
        mediumButton = new JButton("Medium");
        hardButton = new JButton("Hard");
        returnButton = new JButton("Exit");
        returnButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "Main Menu");
            GameController.controller.showMap();
        });

        // Add buttons to the difficulty panel
        difficultyPanel.add(easyButton);
        difficultyPanel.add(mediumButton);
        difficultyPanel.add(hardButton);
        difficultyPanel.add(returnButton);

        // Add components to the main menu panel
        mainMenuPanel.add(titleLabel, BorderLayout.NORTH);
        mainMenuPanel.add(rulesPanel, BorderLayout.CENTER);
        mainMenuPanel.add(difficultyPanel, BorderLayout.SOUTH);

        return mainMenuPanel;
    }

    /**
     * Creates a panel displaying the rules of the weightlifting game.
     *
     * @return The JPanel containing the game rules.
     */
    private JPanel createRules() {
        JPanel rulesPanel = new JPanel();
        rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.Y_AXIS));

        // Create the rules label
        JLabel rulesLabel = new JLabel("Rules", JLabel.CENTER);
        rulesLabel.setFont(new Font("Arial", Font.BOLD, 20));
        rulesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rulesPanel.add(rulesLabel);

        // Define the rules of the game
        JLabel[] ruleLabels = {
                new JLabel("Press the space bar repeatedly to keep the barbell in the zone."),
                new JLabel("Each second you stay in the zone will get points"),
                new JLabel("Easy mode you will get 10 points per second"),
                new JLabel("Medium mode you will get 15 points per second"),
                new JLabel("Hard mode you will get 20 points per second")
        };

        // Add the rules to the panel
        for (JLabel label : ruleLabels) {
            label.setFont(new Font("Arial", Font.BOLD, 20));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            rulesPanel.add(label);
        }

        return rulesPanel;
    }

    /**
     * Creates the game panel where the weightlifting game is played.
     * Sets up the barbell, timer, and the green zone where the barbell needs to stay.
     *
     * @return The JPanel representing the game view.
     */
    private JPanel createWeightGame() {
        JPanel weightGamePanel = new JPanel();
        weightGamePanel.setLayout(null);

        // Set up the barbell icon
        barbell = new JLabel();
        barbell.setOpaque(true);
        barbell.setBounds(305, 350, 100, 20);
        try {
            BufferedImage img = ImageIO.read(new File("Images/barbell.png"));
            Image dimg = img.getScaledInstance(barbell.getWidth(), barbell.getHeight(), Image.SCALE_SMOOTH);
            barbell.setIcon(new ImageIcon(dimg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        weightGamePanel.add(barbell);

        // Set up the timer label
        timerLabel = new JLabel("Time: 0.0s", SwingConstants.CENTER);
        timerLabel.setBounds(300, 20, 100, 30);
        weightGamePanel.add(timerLabel);

        // Set up the green zone where the barbell needs to stay
        JPanel zonePanel = new JPanel();
        zonePanel.setBackground(Color.GREEN);
        zonePanel.setBounds(200, zoneTopY, 300, zoneBottomY - zoneTopY);
        weightGamePanel.add(zonePanel);

        // Add key bindings to control the game
        setupKeyBindings(weightGamePanel);

        // Start the game timer (but it won’t run until the game starts)
        gameTimer = new Timer(30, e -> gameLoop());
        gameTimer.start();

        return weightGamePanel;
    }

    /**
     * Creates the result screen showing the player's total points and provides an option to exit.
     *
     * @return The JPanel representing the result screen.
     */
    private JPanel createResultScreen() {
        JPanel resultPanel = new JPanel(new BorderLayout());

        // Set up the result label to show the total score
        resultLabel = new JLabel("Game Over! Your score is: " + totalPoints, JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Set up the exit button
        returnButton = new JButton("Exit");
        returnButton.addActionListener(e -> {
            resetGame();
            cardLayout.show(cardPanel, "Main Menu");
            GameController.controller.showMap();
        });

        // Add components to the result panel
        resultPanel.add(resultLabel, BorderLayout.CENTER);
        resultPanel.add(returnButton, BorderLayout.SOUTH);

        return resultPanel;
    }

    /**
     * Resets the game state, including the barbell position, timer, and points.
     */
    public void resetGame() {
        barbell.setLocation(305, 350);  // Reset the barbell position
        timerLabel.setText("Time: 0.0s");  // Reset the timer label
        spacePressed = false;  // Reset the state of the space bar press
        inZone = false;  // Reset the in-zone flag
        startRun = false;  // Reset the game start flag
        points = 0;  // Reset points

        // Stop the game timer if it's running
        if (gameTimer.isRunning()) {
            gameTimer.stop();
        }
        startRun = !startRun;
    }

    /**
     * Sets up key bindings to control the game.
     * The space bar moves the barbell upwards.
     *
     * @param panel The JPanel where the key bindings will be applied.
     */
    private void setupKeyBindings(JPanel panel) {
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();

        // Bind the space bar press event
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "spacePressed");
        actionMap.put("spacePressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spacePressed = true;
                System.out.println("Space pressed");
            }
        });

        // Bind the space bar release event
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "spaceReleased");
        actionMap.put("spaceReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spacePressed = false;
            }
        });
    }

    /**
     * Updates the result label with the correct total points when the game ends.
     */
    public void updateResultScreen() {
        resultLabel.setText("Game Over! Your score is: " + totalPoints);  // Dynamically update the score
    }

    /**
     * The game loop that updates the game state (barbell position, timer, etc.) while the game is running.
     * It checks whether the barbell is within the target zone and calculates points accordingly.
     */
    private void gameLoop() {
        if (!startRun) return;

        int barbellY = barbell.getY();

        // Move the barbell upwards if space is pressed, otherwise let it fall
        if (spacePressed) {
            barbellY -= 5;  // Move up when space is pressed
        } else {
            barbellY += controller.getGravity();  // Use gravity to move the barbell down
        }

        // End the game if the barbell moves out of bounds
        if (barbellY < 0 || barbellY > 700 - barbell.getHeight()) {
            gameTimer.stop();  // Stop the game
            cardLayout.show(cardPanel, "Results");
            return;
        }

        // Update barbell position
        barbell.setLocation(barbell.getX(), barbellY);

        // Check if the barbell is in the correct zone and update points
        if (barbellY >= zoneTopY && barbellY <= zoneBottomY) {
            if (!inZone) {
                inZone = true;
                startTime = System.currentTimeMillis();
            } else {
                long elapsedTime = System.currentTimeMillis() - startTime;
                timerLabel.setText(String.format("Time: %.1fs", elapsedTime / 1000.0));
            }
        } else {
            if (inZone) {
                inZone = false;
                gameTimer.stop();
                long elapsedTime = System.currentTimeMillis() - startTime;
                points = (int) (elapsedTime / 100);
                totalPoints = (int) (points * controller.getMultiplier());  // Apply multiplier to points
                player.setPoints(totalPoints, "weight");
                controller.stopGame();
                updateResultScreen();  // Update result screen with correct points
                resetGame();
                cardLayout.show(cardPanel, "Results");
            }
        }
    }

    /**
     * Returns the main panel that contains the entire game UI.
     *
     * @return The JPanel that contains the card layout for the game.
     */
    public JPanel getPanel() {
        return cardPanel;
    }
}
