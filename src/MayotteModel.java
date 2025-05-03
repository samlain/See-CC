import java.awt.*;

public class MayotteModel extends NPCModel{


    public MayotteModel(){
        convo = "Hello and welcome to Robson Arena! \n I am Coach Kris Mayotte, head hockey coach!. \n " +
                "GOOOOOOOOOO TIGERS!";
        sourcePath = "Images/inside.png";
        exitName = "Go back!";
        this.background = Color.YELLOW;
    }

    public MayotteModel(String message, String source, String exit, Color background){
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
