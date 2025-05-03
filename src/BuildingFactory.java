public class BuildingFactory {

    /**
     * This is where the factory "churns" out buildings
     * @param name of building user entered
     * @return the building controller for that building
     */
    public BuildingController buildingBuildings(String name, PlayerModel player){
        if (name.equals("library")){
            return new LibraryController(player);
        }
        else if (name.equals("gym")){
            return new GymController(player);
        }
//        else if (name.equals("hockey")){
//            return new RobsonController(player);
//        }
        return new WornerController(player);

    }
}
