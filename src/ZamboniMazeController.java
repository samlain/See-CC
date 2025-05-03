import javax.swing.*;
import java.awt.event.*;

public class ZamboniMazeController extends MiniGameController implements MouseListener, KeyListener {
    private ZamboniMazeModel model;
    private ZamboniMazeView view;
    private PlayerModel player;

    // Constructor
    public ZamboniMazeController(PlayerModel player) {
        this.player = player;
        this.model = new ZamboniMazeModel();
        this.view = new ZamboniMazeView();
        this.id = "zamboniMaze";

        // Set up button listeners for difficulty selection
        view.easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame("Easy");
            }
        });

        view.mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame("Medium");
            }
        });

        view.hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame("Hard");
            }
        });
        // Add key listener to control player movement
        view.cardPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                view.cardPanel.requestFocusInWindow();
                handleMovement(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        view.cardPanel.setFocusable(true);  // Ensure the panel can receive key events
    }

    // Start the game with the specified difficulty
    private void startGame(String difficulty) {
        model.setMaze(difficulty);  // Set the maze based on the difficulty
        view.cardLayout.show(view.cardPanel, "Maze");  // Show the maze screen
        updateView();  // Update the maze view with the player's position
    }

    // Handle player movement based on arrow key inputs
    private void handleMovement(int keyCode) {
        boolean moved = false;

        switch (keyCode) {
            case KeyEvent.VK_UP:
                moved = model.movePlayer(-1, 0);  // Move up
                break;
            case KeyEvent.VK_DOWN:
                moved = model.movePlayer(1, 0);  // Move down
                break;
            case KeyEvent.VK_LEFT:
                moved = model.movePlayer(0, -1);  // Move left
                break;
            case KeyEvent.VK_RIGHT:
                moved = model.movePlayer(0, 1);  // Move right
                break;
        }

        if (moved) {
            updateView();  // Update the maze if the player moved
            if (model.isAtGoal()) {
                handleGameEnd();  // Handle the game ending if the player reached the goal
            }
        }
    }

    // Update the view with the current maze and player's position
    private void updateView() {
        view.updateMazeGrid(model.getCurrentMaze(), model.getPlayerX(), model.getPlayerY());
    }

    // Handle the game ending when the player reaches the goal
    private void handleGameEnd() {
        model.increaseScore();  // Increase the player's score
        view.resultLabel.setText("Game Over! Your score is: " + player.getPoints());
        view.cardLayout.show(view.cardPanel, "Results");  // Show the result screen
    }

    // Method to return the view panel (for the controller)
    public JPanel getPanel() {
        return view.getPanel();
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        view.getPanel().requestFocusInWindow();
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        view.mazePanel.requestFocusInWindow();
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        boolean moved = false;
        if (key == KeyEvent.VK_UP) {
            moved = model.movePlayer(-1, 0); // Move up
        } else if (key == KeyEvent.VK_DOWN) {
            moved = model.movePlayer(1, 0); // Move down
        } else if (key == KeyEvent.VK_LEFT) {
            moved = model.movePlayer(0, -1); // Move left
        } else if (key == KeyEvent.VK_RIGHT) {
            moved = model.movePlayer(0, 1); // Move right
        }

        if (moved) {
            updateView();  // Update the view if the player moved

            // Check if the player has reached the goal (red square)
            if (model.isAtGoal()) {
                // Increase the score based on the difficulty (gainPoints)
                model.increaseScore();

                // Update the result screen with the final score
                view.updateResultScreen(model.getScore());

                // Switch to the result screen
                view.cardLayout.show(view.cardPanel, "Results");
            }
        }
    }


    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}