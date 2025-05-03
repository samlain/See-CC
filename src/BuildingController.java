import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BuildingController {

    BuildingView view;
    BuildingModel model;
    MiniGameController miniGameController;

    MapView mapView;


    /**
     * Uses the view object to create the view w buttons
     */
    public void setStage() {
        showBuilding();
        view.NPC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNPC();
            }
        });
        view.exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        view.game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMinigame();
            }
        });
    }

    public void showNPC() {
        GameController.controller.showPanel(view.getNPCName());
    }
    public void showBuilding() {
        GameController.controller.showPanel(model.name());
    }

    public void showMinigame() {
        GameController.controller.showPanel(miniGameController.id);
    }

    public void exit(){
        GameController.controller.showMap();
        if(mapView != null){
            mapView.showLeaderboard();
        }
    }

}
