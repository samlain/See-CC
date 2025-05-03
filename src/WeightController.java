import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * The WeightController class manages the weightlifting game, including interactions
 * between the view and model. It also manages the background music during the game.
 */
public class WeightController extends MiniGameController {
    private WeightView view;  // The view for the weightlifting game
    private WeightModel model;  // The model for the weightlifting game
    private WeightGameStrategy strategy;  // The strategy for the game (handles difficulty)
    private double multiplier;  // Multiplier for point calculation based on difficulty
    private PlayerModel player;  // The player model for tracking points
    private Clip clip;  // Clip for playing background sound

    /**
     * Constructor that accepts a PlayerModel.
     * Initializes the view, model, and strategy for the weight game.
     *
     * @param player The PlayerModel instance for tracking the player's points.
     */
    public WeightController(PlayerModel player) {
        this.player = player;  // Initialize the player instance passed from GymController
        view = new WeightView(player, this);  // Initialize the view (can be passed player if needed)
        model = new WeightModel();  // Initialize the model
        strategy = new WeightGameStrategy();  // Initialize strategy
        id = "weight";  // Unique ID for the game
        addActionListeners();  // Set up action listeners for buttons
    }

    /**
     * Method to get gravity from the strategy.
     *
     * @return The gravity value for the current game difficulty.
     */
    public double getGravity() {
        return strategy.getGravity();
    }

    /**
     * Method to get multiplier from the strategy.
     *
     * @return The points multiplier for the current game difficulty.
     */
    public double getMultiplier() {
        return strategy.getMultiplier();
    }

    /**
     * Adds action listeners to the difficulty buttons in the view.
     * When a button is clicked, the game starts with the selected difficulty.
     */
    private void addActionListeners() {
        view.easyButton.addActionListener(e -> startGameWithDifficulty("Easy"));
        view.mediumButton.addActionListener(e -> startGameWithDifficulty("Medium"));
        view.hardButton.addActionListener(e -> startGameWithDifficulty("Hard"));
    }

    /**
     * Starts the weightlifting game with the selected difficulty.
     * Plays background music during the game.
     *
     * @param difficulty The difficulty level selected (Easy, Medium, Hard).
     */
    private void startGameWithDifficulty(String difficulty) {
        strategy.setDifficulty(difficulty);  // Set the strategy based on difficulty
        multiplier = strategy.getMultiplier();  // Get the multiplier for the selected difficulty
        view.cardLayout.show(view.cardPanel, "Weight Game");  // Show the game panel
        view.startRun = true;  // Mark the game as started
        view.gameTimer.start();  // Start the game timer
        playSound("Music/EyeOfTheTiger.wav");  // Play background music
    }

    /**
     * Method to handle the game loop and point updates.
     * Called during the game to calculate and update the player's points.
     *
     * @param elapsedTime The time that has passed since the game started.
     */
    public void updatePoints(long elapsedTime) {
        int basePoints = (int) (elapsedTime / 100);  // Base points based on time

        int totalPoints = basePoints * (int) multiplier;  // Apply the multiplier
        player.setPoints(totalPoints, "weight");  // Update player's points

        // Display the updated points
        JOptionPane.showMessageDialog(null, "You now have " + view.totalPoints + " points.");
    }

    /**
     * Stops the game, updates the points, and stops the background music.
     * This method is called when the game is over.
     */
    public void stopGame() {
        stopSound();  // Stop the background music
        view.gameTimer.stop();  // Stop the game timer
        // Handle any other logic for ending the game, like showing results
    }

    /**
     * Returns the panel for the weightlifting game.
     *
     * @return The JPanel containing the game UI.
     */
    @Override
    public JPanel getPanel() {
        return view.getPanel();
    }

    /**
     * Plays a background sound using the Clip class.
     * The sound file is played in a continuous loop while the game is active.
     *
     * @param filepath The path to the .wav file to be played.
     */
    private void playSound(String filepath) {
        try {
            File soundFile = new File(filepath);  // Load the sound file
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.loop(Clip.LOOP_CONTINUOUSLY);  // Loop the audio continuously
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();  // Handle exceptions
        }
    }

    /**
     * Stops the background sound when the game ends.
     * Closes the Clip to release the resources.
     */
    private void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();  // Stop the sound playback
            clip.close();  // Close the clip to release resources
        }
    }
}
