public class RobsonController extends BuildingController {

    //    BuildingView view;
//    BuildingModel model;
    MayotteModel libmodel;
    PlayerModel player;

    public RobsonController(PlayerModel player) {
        this.player = player;
        model = new RobsonModel(player);
        libmodel = new MayotteModel();
        miniGameController = new ZamboniMazeController(player);
        view = new BuildingView("Robson arena", model.NPCButton, model.filepath,
                libmodel.getConvo(), libmodel.getSource(), libmodel.getBackground(), this);
    }
}