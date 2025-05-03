import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The SignUpView class represents the user interface for signing up and signing in. It provides a graphical
 * interface where users can enter their details to sign up or log in using a username.
 * <p>
 * This view contains fields for user input, buttons for submitting the information, and a section for additional details.
 * It also provides methods to add action listeners to the buttons and retrieve user input from the fields.
 * </p>
 */
public class SignUpView {

    /**
     * The main JFrame for displaying the sign-up and sign-in interface.
     */
    JFrame signUpPage;

    /**
     * Button for signing up with details.
     */
    JButton signUpWithDetails;

    /**
     * Button for signing in with a username.
     */
    JButton signInWithUsername;

    /**
     * Text field for entering the sign-up username.
     */
    JTextField signUpUsernameText;

    /**
     * Text field for entering the first name during sign-up.
     */
    JTextField firstNameText;

    /**
     * Text field for entering the last name during sign-up.
     */
    JTextField lastNameText;

    /**
     * Text field for selecting an icon during sign-up.
     */
    JTextField iconText;

    /**
     * Text field for entering the major during sign-up.
     */
    JTextField majorText;

    /**
     * Text field for entering the sign-in username.
     */
    JTextField signInUsernameText;

    /**
     * Text area for displaying additional details and options for the icon.
     */
    JTextArea detailsTextArea;

    /**
     * Constructs a SignUpView instance, initializes the JFrame, sets up the layout, and creates
     * panels for sign-in, sign-up, and details sections. Configures the layout and adds components
     * to the main frame.
     */
    public SignUpView() {
        // Initialize the JFrame and set up
        signUpPage = new JFrame("Sign up or log in!");
        signUpPage.setSize(700, 600);

        // Set layout
        signUpPage.setLayout(new BorderLayout(20, 20));

        // Panel for Sign In section (Top)
        JPanel signInPanel = new JPanel();
        signInPanel.setLayout(new GridLayout(2, 2, 10, 10));
        signInPanel.setBorder(BorderFactory.createTitledBorder("Sign In"));

        // Sign In fields
        signInPanel.add(new JLabel("Username:"));
        signInUsernameText = new JTextField(20);
        signInPanel.add(signInUsernameText);

        signInWithUsername = new JButton("Sign In");
        signInPanel.add(signInWithUsername);

        // Panel for Sign Up section (Middle)
        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new GridLayout(6, 2, 10, 10));
        signUpPanel.setBorder(BorderFactory.createTitledBorder("Sign Up"));

        // Sign Up fields
        signUpPanel.add(new JLabel("Username:"));
        signUpUsernameText = new JTextField(20);
        signUpPanel.add(signUpUsernameText);

        signUpPanel.add(new JLabel("First Name:"));
        firstNameText = new JTextField(20);
        signUpPanel.add(firstNameText);

        signUpPanel.add(new JLabel("Last Name:"));
        lastNameText = new JTextField(20);
        signUpPanel.add(lastNameText);

        signUpPanel.add(new JLabel("Icon:"));
        iconText = new JTextField(20);
        signUpPanel.add(iconText);

        signUpPanel.add(new JLabel("Major:"));
        majorText = new JTextField(20);
        signUpPanel.add(majorText);

        signUpWithDetails = new JButton("Sign Up");
        signUpPanel.add(signUpWithDetails);

        // Panel for Details section (Bottom)
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Details"));

        // Details text area
        detailsTextArea = new JTextArea(5, 20);
        detailsTextArea.setText("Options for icon: cc, tiger, derpy tiger, pikes");
        detailsTextArea.setEditable(false); // Text area is for display purposes only

        JScrollPane scrollPane = new JScrollPane(detailsTextArea);
        detailsPanel.add(scrollPane, BorderLayout.CENTER);

        // Add panels to the main frame in the correct order
        signUpPage.add(signInPanel, BorderLayout.NORTH);
        signUpPage.add(signUpPanel, BorderLayout.CENTER);
        signUpPage.add(detailsPanel, BorderLayout.SOUTH);

        // Make the frame visible
        signUpPage.setVisible(true);
    }

    /**
     * Adds an action listener to the "Sign Up" button. This listener will be triggered when the button is pressed.
     *
     * @param listener The ActionListener to be added.
     */
    public void addSignUpWithDetailsListener(ActionListener listener) {
        signUpWithDetails.addActionListener(listener);
    }

    /**
     * Adds an action listener to the "Sign In" button. This listener will be triggered when the button is pressed.
     *
     * @param listener The ActionListener to be added.
     */
    public void addSignInWithUsernameListener(ActionListener listener) {
        signInWithUsername.addActionListener(listener);
    }

    /**
     * Retrieves the username entered for sign-up.
     *
     * @return The sign-up username as a String.
     */
    public String getSignUpUsername() {
        return signUpUsernameText.getText();
    }

    /**
     * Retrieves the first name entered for sign-up.
     *
     * @return The first name as a String.
     */
    public String getFirstName() {
        return firstNameText.getText();
    }

    /**
     * Retrieves the last name entered for sign-up.
     *
     * @return The last name as a String.
     */
    public String getLastName() {
        return lastNameText.getText();
    }

    /**
     * Retrieves the icon selected for sign-up.
     *
     * @return The icon as a String.
     */
    public String getIcon() {
        return iconText.getText();
    }

    /**
     * Retrieves the major entered for sign-up.
     *
     * @return The major as a String.
     */
    public String getMajor() {
        return majorText.getText();
    }

    /**
     * Retrieves the username entered for sign-in.
     *
     * @return The sign-in username as a String.
     */
    public String getSignInUsername() {
        return signInUsernameText.getText();
    }

    /**
     * Closes the sign-up page by disposing of the JFrame.
     */
    public void close() {
        signUpPage.dispose();
    }
}
