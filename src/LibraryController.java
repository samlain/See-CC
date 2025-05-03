import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryController extends BuildingController{

//    BuildingView view;
//    BuildingModel model;
    LibrarianModel libmodel;
    PlayerModel player;

    public LibraryController(PlayerModel player){
        this.player = player;
        model = new LibraryModel(player);
        libmodel = new LibrarianModel(player);
        miniGameController = new TriviaController(player);
        view = new BuildingView("the library", model.NPCButton, model.filepath,
                libmodel.getConvo(), libmodel.getSource(), libmodel.getBackground(), this);
    }



    /**
     * Uses the view object to create the view w buttons
     */
//    @Override
//    public void setStage() {
//        //view.createAndShowGui(model.NPCButton, model.filepath);
//        // view.game(TriviaController());
//        view.NPC.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //LibraryController libControl = new LibraryController();
//                //model.talkNPC(view, libControl);
//                view.cardLayout.last(view.cardPanel);
//            }
//        });
//        view.game.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new TriviaController();
//            }
//        });
//    }
}

