import java.awt.*;

public abstract class NPCModel {
    public String sourcePath;
    String convo;

    String exitName;
    Color background;

    /**
     * Set what this NPC has to say
     * @param message
     */
    public abstract void setConvo(String message);

    /**
     * Gets this NPC's bit of speech
     * @return what this NPC has to say
     */
    public abstract String getConvo();

}