public class GymModel extends BuildingModel{
//come back here and add in sams game


    public GymModel(){
        filepath = "Images/gym.jpg";
        NPCButton = "Talk to Dir. Davidson!";
        name = "Fitness Center";
        location = new Location(2,5);
    }

    public String getFilePath(){
        return filepath;
    }

    public String getNPCButton(){
        return NPCButton;
    }
}

