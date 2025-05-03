public class WornerModel extends BuildingModel{

    public WornerModel(){
        filepath = "Images/worner.jpg";
        NPCButton = "Talk to Worner Desk";
        name = "Rasties";
        location = new Location(5, 6);
    }

    public String getFilePath(){
        return filepath;
    }

    public String getNPCButton(){
        return NPCButton;
    }


}
