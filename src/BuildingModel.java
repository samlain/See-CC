public abstract class BuildingModel {
    String filepath;
    String NPCButton;
    String name;
    Location location;


    public abstract String getFilePath();

    public abstract String getNPCButton();

    public Location location() {
        return location;
    }

    public String name() {
        return name;
    }
}