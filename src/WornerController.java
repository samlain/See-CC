import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WornerController extends BuildingController{

//    BuildingView view;
//    BuildingModel model;

    DeskModel desk;
    PlayerModel player;

    public WornerController(PlayerModel player){
        this.player = player;
        model = new WornerModel();
        desk = new DeskModel();
        miniGameController = new SlotController(player);
        view = new BuildingView("Worner center", model.NPCButton, model.filepath,
                desk.getConvo(), desk.getSource(), desk.getBackground(), this);
    }

    public void playGame(){
        view.game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SlotController(player);
            }
        });
    }

    /**
     * Uses the view object to create the view w buttons
     */
//    @Override
//    public void setStage() {
//        //view.createAndShowGui(model.getNPCButton(), model.getFilePath());
//        view.NPC.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                view.cardLayout.last(view.cardPanel);
//            }
//        });
//        view.game.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new SlotController();
//            }
//        });
//    }
//
//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                // Add an instance here
//                WornerController control = new WornerController();
//            }
//        });
//    }
}

