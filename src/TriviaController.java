import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * The TriviaController class manages the interaction between the TriviaView and TriviaModel.
 * It handles user input, game logic, and updates the view based on the model's state.
 * It also plays background music while answering trivia questions and stops it when the game ends.
 */
public class TriviaController extends MiniGameController {
    private TriviaModel model;  // Model to handle the game logic
    private TriviaView view;    // View to display the game interface
    PlayerModel player;         // Player model to track player's points
    private Clip clip;          // Clip for playing background sound

    /**
     * Constructor for TriviaController.
     * Initializes the controller with the player model and sets up the view.
     * It also adds action listeners for the buttons and shows the main menu initially.
     *
     * @param player The PlayerModel instance used to track the player's points.
     */
    public TriviaController(PlayerModel player) {
        this.player = player;
        this.view = new TriviaView(player);
        super.id = "trivia";
        addActionListeners();  // Add listeners for user interactions
        view.cardLayout.show(view.cardPanel, "Main Menu");  // Show the main menu at the start
    }

    /**
     * Adds action listeners for the buttons in the view.
     * Listens for difficulty selection and answer submission.
     */
    private void addActionListeners() {
        view.easyButton.addActionListener(e -> startGameWithDifficulty("Easy"));
        view.mediumButton.addActionListener(e -> startGameWithDifficulty("Medium"));
        view.hardButton.addActionListener(e -> startGameWithDifficulty("Hard"));

        view.submitButton.addActionListener(e -> handleAction(view.answerField.getText().trim()));
    }

    /**
     * Starts the trivia game with the selected difficulty level.
     * Initializes the TriviaModel and updates the view with the first question.
     * It also starts playing the background music.
     *
     * @param difficulty The selected difficulty level (Easy, Medium, or Hard).
     */
    private void startGameWithDifficulty(String difficulty) {
        model = new TriviaModel(player);  // Initialize the model with the player
        model.setDifficulty(difficulty);  // Set the difficulty level
        view.updateQuestion(model.getCurrentQuestion());  // Update the view with the first question
        view.cardLayout.show(view.cardPanel, "Trivia");  // Show the trivia panel
        playSound("Music/Jeopardy.wav");  // Play background music
    }

    /**
     * Handles the player's answer submission.
     * Compares the player's answer with the correct answer and updates the points in the model.
     * It either moves to the next question or shows the results if all questions are answered.
     *
     * @param answer The player's answer input.
     */
    private void handleAction(String answer) {
        boolean correct = model.getAnswer().equalsIgnoreCase(answer);  // Check if the answer is correct
        model.gainAndLosePoints(correct);  // Update the points based on the answer
        updateViewer(correct);  // Provide feedback to the player

        if (model.checkFinished()) {
            stopSound();  // Stop the background music when the game is over
            showResults();  // Show the results screen
        } else {
            model.nextQuestion();  // Move to the next question
            view.updateQuestion(model.getCurrentQuestion());
        }

        view.answerField.setText("");  // Clear the answer field after submission
    }

    /**
     * Updates the view with feedback about the correctness of the player's answer.
     *
     * @param correct True if the player's answer was correct, false otherwise.
     */
    public void updateViewer(boolean correct) {
        if (correct) {
            JOptionPane.showMessageDialog(null, "Correct! You got points.");
        } else {
            JOptionPane.showMessageDialog(null, "Wrong answer! You lost points.");
        }
    }

    /**
     * Displays the result screen and stops the background music.
     * Updates the player's total points with the points earned during the game session.
     */
    private void showResults() {
        int gamePoints = model.getCurrentGamePoints();  // Get the points earned in the current game
        player.setPoints(player.getPoints() + gamePoints, "trivia");  // Update the player's total points

        view.cardPanel.add(view.createResultScreen(gamePoints), "Results");
        view.updateQuestion("Game Over! You earned: " + gamePoints + " points in this session.");
        view.cardLayout.show(view.cardPanel, "Results");  // Show the result panel
    }

    /**
     * Returns the main panel containing the trivia game UI.
     *
     * @return The JPanel that contains the trivia game UI.
     */
    @Override
    public JPanel getPanel() {
        return view.getPanel();
    }

    /**
     * Plays a background sound using the Clip class.
     * The sound file is played in a continuous loop while the trivia game is active.
     *
     * @param filepath The path to the .wav file to be played.
     */
    private void playSound(String filepath) {
        try {
            File soundFile = new File(filepath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.loop(Clip.LOOP_CONTINUOUSLY);  // Loop the audio continuously
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the background sound when the game ends or the result screen is shown.
     * Closes the Clip to release the resources.
     */
    private void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();  // Stop the sound playback
            clip.close();  // Close the clip to release resources
        }
    }
}
