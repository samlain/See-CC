import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SlotView class represents the visual interface of the slot machine game.
 * It contains components such as buttons, labels, and panels for different
 * states of the game, including the main menu and the slot machine itself.
 */
public class SlotView extends MiniGameView {
    JPanel cardPanel;
    CardLayout cardLayout;
    String mainMenu = "Main Menu"; // shows the title and rules
    String slotMachine = "Slot Machine"; // slot machine
    JButton gambleButton; // gamble as many points as you want
    JButton spinButton; // spin button for slot machine
    JButton exitButton; // exit the game
    JButton startButton;
    JLabel amountWonLabel; // label for the amount of money won
    JLabel amountGambledLabel; // label for how much you gambled
    JLabel slot1;
    JLabel slot2;
    JLabel slot3;
    JLabel totalPointsLabel; // label for displaying total points
    int amountWon; // the amount the user won
    int amountGambled; // the amount gambled

    PlayerModel player;

    /**
     * Constructs a SlotView with the given controller and player model.
     * Sets up the initial panels for the game and displays the main menu.
     *
     * @param controller the controller handling game logic
     * @param player     the player model containing player's information
     */
    public SlotView(MiniGameController controller, PlayerModel player) {
        this.player = player;
        JPanel panel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        // calls on the screens to create them
        cardPanel.add(createMainMenu(), mainMenu);
        cardPanel.add(createSlotMachine(), slotMachine);
        // add the panels to the gamePage
        panel.add(cardPanel);
        // start on the Main Menu
        cardLayout.show(cardPanel, mainMenu);
    }

    /**
     * Creates a panel displaying the rules of the game.
     *
     * @return JPanel containing the rules
     */
    private JPanel createRules() {
        JPanel rulesPanel = new JPanel();
        rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.Y_AXIS));
        JLabel rulesLabel = new JLabel("Rules", JLabel.CENTER);
        rulesLabel.setFont(new Font("Arial", Font.BOLD, 20));
        rulesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel rule1Label = new JLabel("Gamble as much as you want by clicking the point button");
        rule1Label.setFont(new Font("Arial", Font.BOLD, 20));
        rule1Label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel rule2Label = new JLabel("After, click the spin button to spin the slot machine");
        rule2Label.setFont(new Font("Arial", Font.BOLD, 20));
        rule2Label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel rule3Label = new JLabel("Gamble at your own risk remember you are at Rastall's");
        rule3Label.setFont(new Font("Arial", Font.BOLD, 20));
        rule3Label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel rule4Label = new JLabel("You can leave at anytime");
        rule4Label.setFont(new Font("Arial", Font.BOLD, 20));
        rule4Label.setAlignmentX(Component.CENTER_ALIGNMENT);

        rulesPanel.add(rulesLabel);
        rulesPanel.add(Box.createVerticalStrut(10));
        rulesPanel.add(rule1Label);
        rulesPanel.add(rule2Label);
        rulesPanel.add(rule3Label);
        rulesPanel.add(rule4Label);
        return rulesPanel;
    }

    /**
     * Creates the main menu panel with the start and exit options.
     *
     * @return JPanel representing the main menu
     */
    private JPanel createMainMenu() {
        JPanel mainMenuPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Welcome to the Slot Machine", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JPanel rulesPanel = createRules();
        JPanel startAndExitPanel = new JPanel(new GridLayout(1, 2));

        startButton = new JButton("Start Game");
        startButton.addActionListener(e -> cardLayout.show(cardPanel, slotMachine));

        JButton returnButton = new JButton("Exit");
        returnButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "Main Menu");
            GameController.controller.showMap();
        });

        startAndExitPanel.add(startButton);
        startAndExitPanel.add(returnButton);
        mainMenuPanel.add(titleLabel, BorderLayout.NORTH);
        mainMenuPanel.add(rulesPanel, BorderLayout.CENTER);
        mainMenuPanel.add(startAndExitPanel, BorderLayout.SOUTH);
        return mainMenuPanel;
    }

    /**
     * Creates the slot machine panel, including slots, result display,
     * and buttons for gambling, spinning, and exiting the game.
     *
     * @return JPanel representing the slot machine interface
     */
    private JPanel createSlotMachine() {
        JPanel slotMachinePanel = new JPanel(new BorderLayout());

        JPanel slotsPanel = new JPanel();
        slotsPanel.setLayout(new GridLayout(1, 3));

        slot1 = new JLabel("SPIN", SwingConstants.CENTER);
        slot2 = new JLabel("SPIN", SwingConstants.CENTER);
        slot3 = new JLabel("SPIN", SwingConstants.CENTER);

        Dimension slotSize = new Dimension(150, 150);
        slot1.setPreferredSize(slotSize);
        slot2.setPreferredSize(slotSize);
        slot3.setPreferredSize(slotSize);

        Font slotFont = new Font("Arial", Font.BOLD, 48);
        slot1.setFont(slotFont);
        slot2.setFont(slotFont);
        slot3.setFont(slotFont);

        slot1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        slot2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        slot3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        slotsPanel.add(slot1);
        slotsPanel.add(slot2);
        slotsPanel.add(slot3);
        slotMachinePanel.add(slotsPanel, BorderLayout.NORTH);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(1, 3));
        amountWonLabel = new JLabel("Won: " + amountWon);
        amountGambledLabel = new JLabel("Gambled: " + amountGambled);
        totalPointsLabel = new JLabel("Points: " + player.getPoints());
        resultPanel.add(amountGambledLabel);
        resultPanel.add(amountWonLabel);
        resultPanel.add(totalPointsLabel);
        slotMachinePanel.add(resultPanel, BorderLayout.CENTER);

        JPanel gamblePanel = new JPanel();
        gambleButton = new JButton("+1 Point");
        spinButton = new JButton("Spin");
        exitButton = new JButton("Exit");

        gambleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amountGambled++;
                amountGambledLabel.setText("Gambled: " + amountGambled);
            }
        });

        spinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, mainMenu);
                GameController.controller.showMap();
            }
        });

        gamblePanel.add(gambleButton);
        gamblePanel.add(spinButton);
        gamblePanel.add(exitButton);
        slotMachinePanel.add(gamblePanel, BorderLayout.SOUTH);

        return slotMachinePanel;
    }

    /**
     * Scales an image to the specified width and height.
     *
     * @param icon  the original ImageIcon
     * @param width the target width
     * @param height the target height
     * @return ImageIcon with the specified dimensions
     */
    private ImageIcon scaleImage(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    /**
     * Updates the images in the slot machine display to fill each slot.
     *
     * @param images array of ImageIcon to update the slot machine images
     */
    public void updateSlotImages(ImageIcon[] images) {
        slot1.setIcon(scaleImage(images[0], slot1.getWidth(), slot1.getHeight()));
        slot2.setIcon(scaleImage(images[1], slot2.getWidth(), slot2.getHeight()));
        slot3.setIcon(scaleImage(images[2], slot3.getWidth(), slot3.getHeight()));

        slot1.setText(null);
        slot2.setText(null);
        slot3.setText(null);
    }

    /**
     * Displays the exit button on the screen.
     */
    public void showExitButton() {
        exitButton.setVisible(true);
    }

    @Override
    public void buttonPress() {
        // Method inherited from MiniGameView; not implemented here
    }
}
