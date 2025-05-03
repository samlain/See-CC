public class RobsonModel extends BuildingModel{
    //come back here and add in sams game
    PlayerModel player;


    public RobsonModel(PlayerModel player){
        filepath = "Images/robson.png";
        NPCButton = "Talk to the coach Mayotte!";
        name = "Robson Arena";
        location = new Location(10,7);//come back here
        this.player = player;
    }

    public String getFilePath(){
        return filepath;
    }

    public String getNPCButton(){
        return NPCButton;
    }

}
