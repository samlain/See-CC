import javax.swing.*;
import java.awt.*;

/**
 * This class is the GameViewer for controlling the GUI of the main game
 */

public class GameViewer {
    MapController mapController;
    JFrame frame;
    JPanel cardPanel;
    CardLayout cardLayout;
    PlayerModel player;

    /**
     * The constructor
     * Work for initialize JFame and other GUI staff
     * @param mapController
     * @param buildingControllers
     */


    public GameViewer(MapController mapController, BuildingController[] buildingControllers) {
        this.mapController = mapController;
        this.player = player;

        frame = new JFrame("See CC");
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        frame.setFocusable(true);
        // Add the map panel
        cardPanel.add(mapController.getPanel(), "map");

        // Add building controllers' panels
        for(BuildingController ctrlr : buildingControllers) {
            cardPanel.add(ctrlr.view.building(), ctrlr.model.name);
            cardPanel.add(ctrlr.view.npc(), ctrlr.view.getNPCName());
            cardPanel.add(ctrlr.miniGameController.getPanel(), ctrlr.miniGameController.id);
        }
        mapController.focus();
        frame.requestFocusInWindow();
        frame.setFocusable(true);
        frame.add(cardPanel); // Add cardPanel to the frame
        frame.setSize(700, 500); // Set frame size before making it visible
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * Use cardLayout to switch panels
     * @param panelId
     */
    public void showPanel(String panelId) {
        cardLayout.show(cardPanel, panelId);
        cardPanel.revalidate();  // Revalidate after showing panel
        cardPanel.repaint();     // Repaint to ensure visibility
    }

}

