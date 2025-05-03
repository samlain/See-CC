import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The SignUpController class manages user sign-up and sign-in actions. It interacts with the {@link Database} to handle
 * user registration and login, and updates the {@link SignUpView} to reflect changes.
 * <p>
 * The controller listens to events triggered by the user in the sign-up and sign-in view, processes the user input,
 * and performs appropriate actions such as registering a new user, signing in an existing user, and transitioning to the game.
 * </p>
 */
public class SignUpController {

    /**
     * The view associated with this controller, used to display sign-up and sign-in forms.
     */
    private SignUpView view;

    /**
     * The database instance used for interacting with user data.
     */
    private Database database;

    /**
     * Constructs a SignUpController instance. Initializes the view and database, and sets up listeners for sign-up
     * and sign-in actions.
     */
    public SignUpController() {
        this.database = Database.shared; // Use the singleton instance of Database
        this.view = new SignUpView();

        // Set up action listeners for sign-up and sign-in buttons
        view.addSignUpWithDetailsListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignUpWithDetails();
            }
        });

        view.addSignInWithUsernameListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignInWithUsername();
            }
        });
    }

    /**
     * Handles the sign-up process using the details provided in the view.
     * Retrieves user details from the view, attempts to sign up the user through the database, and handles the result.
     * If the sign-up is successful, the user is transitioned to the game and a success message is displayed.
     * If the sign-up fails, an error message is shown.
     */
    public void handleSignUpWithDetails() {
        String username = view.getSignUpUsername();
        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        String icon = view.getIcon();
        String major = view.getMajor();

        PlayerModel player = database.signUp(username, firstName, lastName, icon, major);

        if (player != null) {
            PlayerModel.shared = player;
            GameController.controller.enterGame();
            JOptionPane.showMessageDialog(null, "Sign up successful!");
            view.close();
        } else {
            JOptionPane.showMessageDialog(null, "Sign up failed.");
        }
    }

    /**
     * Handles the sign-in process using the username provided in the view.
     * Retrieves the username from the view, attempts to sign in the user through the database, and handles the result.
     * If the sign-in is successful, the user is transitioned to the game and a success message is displayed.
     * If the user does not exist, an error message is shown.
     */
    public void handleSignInWithUsername() {
        String username = view.getSignInUsername();
        PlayerModel player = database.signIn(username);
        if (player != null) { // Adjusted to check for a specific username
            PlayerModel.shared = player;
            GameController.controller.enterGame();
            JOptionPane.showMessageDialog(null, "Sign up successful!");
            view.close();
            // Logic to handle incomplete sign-up
        } else {
            JOptionPane.showMessageDialog(null, "User does not exist. Please sign up.");
        }
    }
}
