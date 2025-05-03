import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * SlotController class handles the interaction between the SlotView and SlotModel.
 * It sets up the event listeners for buttons and manages the game's logic, including
 * background music, point tracking, and transitions between screens.
 */
public class SlotController extends MiniGameController {
    private SlotView view;   // View for the slot machine game
    private SlotModel model; // Model for the slot machine game logic
    private PlayerModel player;  // Player model that contains player points and data
    private Clip clip;  // Clip instance to handle background music

    /**
     * Constructs the SlotController with the player model.
     * Initializes the SlotView and SlotModel, sets up button listeners,
     * and displays the exit button by default.
     *
     * @param player the player model that holds player's points and information
     */
    public SlotController(PlayerModel player) {
        this.player = player;
        this.model = new SlotModel(player);
        this.view = new SlotView(this, player);

        // Show the exit button by default
        view.showExitButton();

        // Set up listeners for buttons
        setupListeners();

        // Set the game identifier for this controller
        id = "slot";
    }

    /**
     * Sets up action listeners for the buttons in the SlotView.
     * Includes handlers for spinning the slot machine, gambling points,
     * exiting the game, and starting the game.
     */
    private void setupListeners() {
        // Spin button listener: Spins the slot machine if points have been gambled
        view.spinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.amountGambled > 0) {
                    // Get the result of spinning slots from the model
                    ImageIcon[] slotResults = model.spinSlots();

                    // Update the slot images in the view
                    view.updateSlotImages(slotResults);

                    // Show how much was won for this spin
                    view.amountWonLabel.setText("Won: " + model.amountWon);

                    // Reset the gambled label after showing the gambled amount for this spin
                    view.amountGambledLabel.setText("Gambled: " + model.amountGambled);

                    // Update total points after the spin
                    view.totalPointsLabel.setText("Points: " + player.getPoints());

                } else {
                    JOptionPane.showMessageDialog(view.gamePage,
                            "You need to gamble some points before spinning!",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Gamble button listener: Increments the gambled points if within available points
        view.gambleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.amountGambled < player.getPoints()) {
                    // Increase the amount gambled for the current spin
                    model.amountGambled++;
                    view.amountGambledLabel.setText("Gambled: " + model.amountGambled);
                } else {
                    // Show a warning if the player tries to gamble more than their available points
                    JOptionPane.showMessageDialog(view.gamePage,
                            "You can't gamble more than your available points!",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Exit button listener: Stops the background music and switches back to the main menu
        view.exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopSound();  // Stop the background music when exiting the game
                view.cardLayout.show(view.cardPanel, "Main Menu");  // Switch to the main menu
            }
        });

        // Start button listener: Begins the game and starts playing the background music
        view.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("Music/PokerFace.wav");  // Play the slot machine background music
                view.cardLayout.show(view.cardPanel, "Slot Machine");  // Start the slot machine game
            }
        });
    }

    /**
     * Plays background music for the slot machine game.
     * Loads the sound file and loops the music continuously while the game is active.
     *
     * @param filepath the file path of the sound file to be played
     */
    private void playSound(String filepath) {
        try {
            // Stop any currently playing sound before starting a new one
            stopSound();

            // Load the sound file
            File soundFile = new File(filepath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.loop(Clip.LOOP_CONTINUOUSLY);  // Loop the audio continuously while playing
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();  // Handle exceptions for sound playback
        }
    }

    /**
     * Stops the background music when the game ends or the player exits.
     * Releases resources by closing the audio clip.
     */
    private void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();  // Stop the sound playback
            clip.close();  // Close the clip to release resources
        }
    }

    /**
     * Retrieves the panel that contains the slot machine view components.
     * This panel will be used to display the slot machine UI.
     *
     * @return JPanel representing the slot machine interface
     */
    @Override
    public JPanel getPanel() {
        return view.cardPanel;
    }
}
