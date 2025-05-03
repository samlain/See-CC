public class LibraryModel extends BuildingModel{
//come back here and add in sams game
    PlayerModel player;


    public LibraryModel(PlayerModel player){
        filepath = "Images/tutt.jpg";
        NPCButton = "Talk to the librarian!";
        name = "Tutt Library";
        location = new Location(7,3);
        this.player = player;
    }

    public String getFilePath(){
        return filepath;
    }

    public String getNPCButton(){
        return NPCButton;
    }
}

