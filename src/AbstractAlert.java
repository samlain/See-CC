import javax.swing.*;
import java.awt.*;

/**
 * The AbstractAlert class is a custom JPanel designed to display an alert with an image, a message, and an action button.
 * It provides a template for creating specific types of alerts by extending this class and implementing the
 * {@link #onActionButtonPressed()} method to define the behavior when the action button is pressed.
 */
public abstract class AbstractAlert extends JPanel {

    /**
     * Label to display the image in the alert.
     */
    protected JLabel imageLabel;

    /**
     * Label to display the message in the alert.
     */
    protected JLabel messageLabel;

    /**
     * Button for the alert action, such as confirming or taking action based on the alert.
     */
    protected JButton actionButton;

    /**
     * Button to close the alert.
     */
    private JButton closeButton;

    /**
     * Constructs an AbstractAlert with a specified text for the action button.
     * Initializes the layout and components of the alert including image, message, and action button.
     * Sets the initial visibility of the alert to false.
     *
     * @param buttonText The text to be displayed on the action button.
     */
    public AbstractAlert(String buttonText) {
        setLayout(new BorderLayout());
        setBounds(150, 50, 400, 250);  // Increased height to allow for more message space
        setBackground(new Color(255, 255, 200));
        setVisible(false);

        // Top panel with close button
        closeButton = new JButton("X");
        closeButton.setFocusPainted(false);
        closeButton.setPreferredSize(new Dimension(50, 30));
        closeButton.addActionListener(e -> setVisible(false));
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));  // Add small margin at top
        topPanel.setBackground(new Color(255, 255, 200));
        topPanel.add(closeButton);

        // Image and message area
        imageLabel = new JLabel();
        messageLabel = new JLabel();
        messageLabel.setVerticalAlignment(SwingConstants.TOP); // Align text to top

        // Set up content panel with padding
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding around components
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;

        // Add imageLabel to contentPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 1;
        contentPanel.add(imageLabel, gbc);

        // Add messageLabel to contentPanel
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(messageLabel, gbc);

        // Bottom action button
        actionButton = new JButton(buttonText);
        actionButton.setPreferredSize(new Dimension(100, 30));
        actionButton.addActionListener(e -> onActionButtonPressed());

        // Add components to the layout
        add(topPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(actionButton, BorderLayout.SOUTH);
    }

    /**
     * Abstract method to be implemented by subclasses to define the behavior
     * when the action button is pressed.
     */
    protected abstract void onActionButtonPressed();

    /**
     * Displays the alert with the specified image and message.
     * Updates the image and message labels and makes the alert visible.
     *
     * @param imageFilePath The file path to the image to be displayed in the alert.
     * @param message The message to be displayed in the alert, formatted as HTML.
     */
    public void showAlert(String imageFilePath, String message) {
        ImageIcon icon = new ImageIcon(imageFilePath);
        Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));

        // Adjust the message text with HTML formatting, including margins and wrapping
        messageLabel.setText("<html><div style=\"margin-top: 10px; width: 200px;\">" + message + "</div></html>");

        setVisible(true);
    }
}
