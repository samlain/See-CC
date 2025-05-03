import java.awt.*;

public class DeskModel extends NPCModel {

    public DeskModel(){
        convo = "Hello and welcome to the Worner Desk! \n Let me know if you need" +
                "any help or need your gold card reprinted. \n Good luck gambling...";
        sourcePath = "Images/worner.jpg";
        exitName = "Go back to the lobby!";
        this.background = Color.GREEN;
    }

    public DeskModel(String message, String source, String exit, Color background){
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
