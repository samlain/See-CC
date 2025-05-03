import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GymController extends BuildingController{

//    BuildingView view;
//    BuildingModel model;

    DavidsonModel davidson;
    PlayerModel player;

    public GymController(PlayerModel player){
        model = new GymModel();
        this.player = player;
        davidson = new DavidsonModel();
        miniGameController = new WeightController(player);
        view = new BuildingView("the gym", model.NPCButton, model.filepath,
                davidson.getConvo(), davidson.getSource(), davidson.getBackground(), this);
    }
    public void playGame(){
        view.game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WeightController(player);
            }
        });
    }

}

