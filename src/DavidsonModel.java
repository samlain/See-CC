import java.awt.*;

public class DavidsonModel extends NPCModel{


    public DavidsonModel(){
        convo = "Hello and welcome to the Adam F Press Fitness Center! \n I am Cam Davidson, director of strength and conditioning. \n " +
                "Watch out for altitude when working out here!";
        sourcePath = "Images/davidson.png";
        exitName = "Go back to the gym!";
        this.background = Color.YELLOW;
    }

    public DavidsonModel(String message, String source, String exit, Color background){
        sourcePath = source;
        exitName = exit;
        this.background = background;
        convo = message;
    }
    public String getSource() {
        return sourcePath;
    }
    public Color getBackground() {
        return background;
    }

    /**
     * Set what this NPC has to say
     *
     * @param message
     */
    @Override
    public void setConvo(String message) {
        convo = message;
    }

    /**
     * Gets this NPC's bit of speech
     *
     * @return what this NPC has to say
     */
    @Override
    public String getConvo() {
        return convo;
    }
}
