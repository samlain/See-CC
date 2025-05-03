import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

/**
 * Represents the view of the map in the game, including the display of players, buildings, and the campus map.
 * Handles user interactions via key events and updates the map display accordingly.
 */

public class MapView extends JPanel implements KeyListener, MouseListener  {
    private MapController controller;
    private Map<String, Number> buildingsMap;
    private Map<String, Number> playersMap;
    private Location playerLoc;
    private BuildingController[] buildingModels;

    private Image campusMapImage;
    private Image playerIcon;
    private JButton interactButton;
    private SquirrelAlert squirrelAlert;
    private PlayerCollisionAlert playerCollisionAlert;
    private boolean debugMode = false; // Display the grid lines
    private HashMap<String, Image> buildingIcons;
    private JPanel leaderboardPanel;
    private JPanel mapPanel;
    JButton leader;
    private LeaderboardController leaderboardController;

    Database database;



    /**
     * Constructs a MapView with the specified player, building models, and controller.
     * Initializes the map, loads images, and sets up the interact button.
     *
     * @param buildingModels Array of building models to be displayed on the map.
     * @param controller     The controller that handles interactions with the map.
     */

    public MapView(BuildingController[] buildingModels, MapController controller)  {
        this.buildingsMap = new Map<String, Number>(14, 9);
        this.playersMap = new Map<String, Number>(14, 9);
        this.controller = controller;
        this.buildingModels = buildingModels;
        this.buildingIcons = new HashMap<>();
        this.setFocusable(true);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.setLayout(new BorderLayout());
        makeMapPanel();
        JPanel topPanel = showPlayer();
        this.add(mapPanel, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
        this.database = new Database();
    }

    /**
     * Creates and initializes the map panel, including setting up the leaderboard and the leaderboard button.
     * This method also configures the panel to be focusable and visible.
     */
    public void makeMapPanel() {
        mapPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                setFocusable(true);
                super.paintComponent(g);
                drawCampusMap(g);
                drawGridIfDebug(g);
                drawPlayer(g);
                drawBuildings(g);
            }
        };
        mapPanel.setFocusable(true);
        mapPanel.setVisible(true);
        mapPanel.requestFocusInWindow();
        leaderboardPanel = makeLeaderboard();
        JButton leader = setLeaderboardButton();
        mapPanel.add(leader);
        mapPanel.add(leaderboardPanel);
    }

    /**
     * Creates and returns a JPanel displaying the current player's name.
     * Initializes the database if it is null and sets up the top panel with the player's name.
     *
     * @return A JPanel displaying the player's name.
     */
    public JPanel showPlayer() {
        JPanel topPanel = new JPanel(new FlowLayout());
        if (this.database == null) {
            this.database = new Database();
        }
        JLabel name = new JLabel("Player: " + PlayerModel.shared.getName());
        topPanel.add(name);
        topPanel.setVisible(true);
        topPanel.setFocusable(false);
        topPanel.repaint();
        return topPanel;
    }

    /**
     * Creates and initializes the leaderboard panel using the LeaderboardController.
     * Sets the panel to be invisible initially and repaints it.
     *
     * @return The initialized leaderboard JPanel.
     */
    private JPanel makeLeaderboard() {
        LeaderboardModel leaderboardModel = new LeaderboardModel(GameController.controller, Database.shared);
        LeaderboardView leaderboardView = new LeaderboardView();
        this.leaderboardController = new LeaderboardController();
        leaderboardController.set(leaderboardModel, leaderboardView);
        this.leaderboardPanel = leaderboardController.display();
        leaderboardPanel.setVisible(false);
        leaderboardPanel.repaint();
        System.out.println("leaderboardPanel painted: " + leaderboardPanel);
        return leaderboardPanel;
    }

    /**
     * Creates and configures the leaderboard button.
     * Sets its bounds, visibility, and action listener to toggle the visibility of the leaderboard panel.
     *
     * @return The configured JButton for the leaderboard.
     */
    private JButton setLeaderboardButton() {
        leader = new JButton("Leaderboard");
        leader.setBounds(500, 340, 150, 30);
        leader.setVisible(true);
        leader.addActionListener(e -> {
            showLeaderboard();
        });
        return leader;
    }

    /**
     * Toggles the visibility of the leaderboard panel.
     * If the panel is visible, it hides it; otherwise, it shows it and repaints both the leaderboard and map panels.
     */
    public void showLeaderboard() {
        leaderboardPanel.setBounds(45,0, 600, 500);
        if (leaderboardPanel.isVisible()){
            leaderboardPanel.setVisible(false);
            // Optionally request focus if needed
            // leaderboardPanel.requestFocusInWindow();
        } else {
            leaderboardPanel.setVisible(true);
            leaderboardPanel.revalidate();
            leaderboardPanel.repaint();
            mapPanel.revalidate();
            mapPanel.repaint();
        }
        System.out.println("leaderboardPanel's visibility: " + leaderboardPanel.isVisible());
    }

    /**
     * Sets up the map view, including creating the map panel, displaying the player information,
     * loading the campus map and player icon, setting up the interact button, and initializing alerts.
     */
    public void setupMap() {
        makeMapPanel();
        JPanel topPanel = showPlayer();
        this.add(topPanel, BorderLayout.NORTH);
        this.add(mapPanel, BorderLayout.CENTER);
        loadCampusMap();
        loadPlayerIcon();
        setupInteractButton();
        setupAlerts();
        setPlayerAndBuildings(PlayerModel.shared, buildingModels);
    }

    /**
     * Initializes and adds alert components to the map panel.
     * Creates instances of SquirrelAlert and PlayerCollisionAlert and adds them to the map panel.
     */
    private void setupAlerts() {
        squirrelAlert = new SquirrelAlert();
        playerCollisionAlert = new PlayerCollisionAlert(controller);
        mapPanel.setLayout(null);
        mapPanel.add(squirrelAlert);
        mapPanel.add(playerCollisionAlert);
    }

    /**
     * Displays a SquirrelAlert with a random number between 1 and 6.
     */
    public void showSquirrel() {
        int squirrelNumber = new Random().nextInt(6) + 1;
        squirrelAlert.showSquirrelAlert(squirrelNumber);
    }

    /**
     * Displays a PlayerCollisionAlert for the specified player.
     *
     * @param player The player involved in the collision.
     */
    public void showPlayerCollision(PlayerModel player) {
        playerCollisionAlert.showCollisionAlert(player);
    }

    /**
     * Requests focus for the map view to enable key event handling.
     */
    public void focus() {
        this.requestFocusInWindow();
    }

    /**
     * Loads the campus map image from the file system.
     */
    private void loadCampusMap() {
        campusMapImage = loadImage("Images/campusMap.png");
    }

    /**
     * Loads the player's icon image from a specified file path.
     */
    private void loadPlayerIcon() {
        playerIcon = loadImage("Images/Icons/" + PlayerModel.shared.getIcon() + ".png");
    }

    /**
     * Loads an image from the given file path.
     *
     * @param path The file path to load the image from.
     * @return The loaded image, or null if the image could not be loaded.
     */
    private Image loadImage(String path) {
        try {
            File imageFile = new File(path);
            if (imageFile.exists()) {
                return new ImageIcon(imageFile.getAbsolutePath()).getImage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Sets up the interact button, including its position, size, and action listener.
     */
    private void setupInteractButton() {
        interactButton = new JButton("Interact");
        interactButton.setBounds(450, 375, 200, 50);
        interactButton.setVisible(false);
        interactButton.addActionListener(e -> {
            controller.enterBuilding(); // Handle interaction
            mapPanel.requestFocusInWindow(); // Regain focus for key events
        });
        mapPanel.setLayout(null);
        mapPanel.add(interactButton);
    }


    /**
     * Shows the interact button with a text indicating the specified building.
     *
     * @param buildingName The name of the building to be shown on the interact button.
     */
    public void showInteractButton(String buildingName) {
        interactButton.setText("Enter " + buildingName);
        interactButton.setVisible(true);
    }

    /**
     * Hides the interact button.
     */
    public void hideInteractButton() {
        interactButton.setVisible(false);
    }

    /**
     * Sets the player and building models and updates the display accordingly.
     *
     * @param player          The new player model to be displayed.
     * @param buildingModels  Array of new building models to be displayed.
     */
    public void setPlayerAndBuildings(PlayerModel player, BuildingController[] buildingModels) {
        markPlayer(player);
        markBuildings(buildingModels);
        mapPanel.revalidate();
        mapPanel.repaint();
    }

    /**
     * Marks the location of the player on the map.
     *
     * @param player The player model whose location is to be marked.
     */
    public void markPlayer(PlayerModel player) {
        playersMap.clear();
        this.playerLoc = player.getLocation();
        playersMap.addMark(playerLoc, player.getName());
    }

    /**
     * Marks the locations of the buildings on the map.
     *
     * @param buildingModels Array of building models to be marked on the map.
     */
    private void markBuildings(BuildingController[] buildingModels) {
        buildingsMap.clear();
        for (BuildingController b : buildingModels) {
            buildingsMap.addMark(b.model.location(), b.model.name());
            loadBuildingIcon(b.model.name());
        }
    }

    /**
     * Loads the building icon from a file based on the building name.
     *
     * @param buildingName The name of the building whose icon should be loaded.
     */
    private void loadBuildingIcon(String buildingName) {
        if (!buildingIcons.containsKey(buildingName)) {
            buildingIcons.put(buildingName, loadImage("Images/" + buildingName + ".png"));
        }
    }

    /**
     * Draws the buildings on the map.
     *
     * @param g The Graphics object used for drawing.
     */
    private void drawBuildings(Graphics g) {
        if (buildingModels != null) {
            for (BuildingController building : buildingModels) {
                Image icon = buildingIcons.get(building.model.name());
                if (icon != null) {
                    g.drawImage(icon, building.model.location().getX() * 50, building.model.location().getY() * 50, 40, 40, null);
                } else {
                    g.setColor(Color.BLUE);
                    g.fillRect(building.model.location().getX() * 50, building.model.location().getY() * 50, 40, 40);
                    g.setColor(Color.BLACK);
                    g.drawString(building.model.name(), building.model.location().getX() * 50, building.model.location().getY() * 50);
                }
            }
        }
    }

    /**
     * Draws the campus map on the panel.
     *
     * @param g The Graphics object used for drawing.
     */
    private void drawCampusMap(Graphics g) {
        if (campusMapImage != null) {
            g.drawImage(campusMapImage, 0, 0, getWidth(), getHeight(), null);
        }
    }

    /**
     * Draws a grid on the map panel if debug mode is enabled.
     *
     * @param g The Graphics object used for drawing.
     */
    private void drawGridIfDebug(Graphics g) {
        if (debugMode) {
            drawGrid(g);
            drawSquirrelIfDebug(g);
            drawOtherPlayerIfDebug(g);
        }
    }

    /**
     * Draws a squirrel on the map panel in debug mode.
     *
     * @param g The Graphics object used for drawing.
     */
    private void drawSquirrelIfDebug(Graphics g) {
        if (controller.getSquirrel() != null) {
            g.setColor(Color.GREEN);
            g.fillOval(controller.getSquirrel().getX() * 50, controller.getSquirrel().getY() * 50, 30, 30);
            g.setColor(Color.BLACK);
            g.drawString("Squirrel", controller.getSquirrel().getX() * 50 + 5, controller.getSquirrel().getY() * 50 + 20);
        }
    }

    /**
     * Draws a grid on the map.
     *
     * @param g The Graphics object used for drawing.
     */
    private void drawGrid(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        int gridSize = 50;
        for (int x = 0; x < getWidth(); x += gridSize) {
            for (int y = 0; y < getHeight(); y += gridSize) {
                g.drawRect(x, y, gridSize, gridSize);
                g.drawString(String.format("(%d, %d)", x / gridSize, y / gridSize), x + 5, y + 20);
            }
        }
    }

    /**
     * Draws another player on the map in debug mode.
     *
     * @param g The Graphics object used for drawing.
     */
    private void drawOtherPlayerIfDebug(Graphics g) {
        if (controller.getRandomPlayer() != null) {
            g.setColor(Color.GREEN);
            g.fillOval(controller.getRandomPlayer().getLocation().getX() * 50, controller.getRandomPlayer().getLocation().getY() * 50, 30, 30);
            g.setColor(Color.BLACK);
            g.drawString(controller.getRandomPlayer().getName(), controller.getRandomPlayer().getLocation().getX() * 50 + 5, controller.getRandomPlayer().getLocation().getY() * 50 + 20);
        }
    }

    /**
     * Draws the current player on the map.
     *
     * @param g The Graphics object used for drawing.
     */
    private void drawPlayer(Graphics g) {
        if (playerIcon != null && PlayerModel.shared != null) {
            g.drawImage(playerIcon, playerLoc.getX() * 50, playerLoc.getY() * 50, 30, 30, null);
        } else if (PlayerModel.shared != null) {
            g.setColor(Color.RED);
            g.fillOval(playerLoc.getX() * 50, playerLoc.getY() * 50, 30, 30);
        }

        if (buildingModels != null) {
            for (BuildingController building : buildingModels) {
                Image icon = buildingIcons.get(building.model.name());
                if (icon != null) {
                    g.drawImage(icon, building.model.location().getX() * 50, building.model.location().getY() * 50, 40, 40, null);
                } else {
                    g.setColor(Color.BLUE);
                    g.fillRect(building.model.location().getX() * 50, building.model.location().getY() * 50, 40, 40);
                    g.setColor(Color.BLACK);
                    g.drawString(building.model.name(), building.model.location().getX() * 50, building.model.location().getY() * 50);
                }
            }
        }
    }


    public Map<String, Number> getBuildingsMap() {
        return buildingsMap;
    }
    public Map<String, Number> getPlayerMap() {
        return playersMap;
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

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Location currentLocation = PlayerModel.shared.getLocation();
        Location newLocation = null;
        if (key == KeyEvent.VK_LEFT) {
            newLocation = new Location(currentLocation.getX() - 1, currentLocation.getY());
        } else if (key == KeyEvent.VK_RIGHT) {
            newLocation = new Location(currentLocation.getX() + 1, currentLocation.getY());
        } else if (key == KeyEvent.VK_UP) {
            newLocation = new Location(currentLocation.getX(), currentLocation.getY() - 1);
        } else if (key == KeyEvent.VK_DOWN) {
            newLocation = new Location(currentLocation.getX(), currentLocation.getY() + 1);
        } else if (key == KeyEvent.VK_D) {
            debugMode = !debugMode;
        }
        if (newLocation != null) {
            controller.movePlayer(newLocation);
        }
        // Call repaint once after all changes
        mapPanel.revalidate();
        mapPanel.repaint();
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
        this.requestFocusInWindow();
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}

