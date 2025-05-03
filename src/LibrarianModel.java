import java.awt.*;

public class LibrarianModel extends NPCModel{

    PlayerModel player;

    public LibrarianModel(PlayerModel player){
        this.player = player;
        convo = "Hello and welcome to the Tutt library! \n I am the librarian today. \n Make sure to keep your voice down, " +
                "\n even when playing the super fun trivia mini game!";
        sourcePath = "Images/librarian.jpg";
        exitName = "Go back to the library!";
        this.background = Color.PINK;
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

