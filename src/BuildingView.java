import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildingView {
    JButton NPC, game, exit;
    String npc;
    String background;

    String name;
    String message;
    String sourcepath;
    Color color;
    BuildingController buildingController;

    public BuildingView(String spot, String npc, String backgroundImage, String message, String sourcepath, Color background, BuildingController buildingController){
        name = spot;
        this.npc = npc;
        this.background = backgroundImage;
        this.message = message;
        this.sourcepath = sourcepath;
        color = background;
        this.buildingController = buildingController;
    }
    public JPanel building(){
        return createBuilding(npc, background);
    }

    public JPanel npc(){
        return createNPC(message, sourcepath, color);
    }

    public String getNPCName() {
        return this.npc;
    }

    private JPanel createBuilding(String npc, String backgroundImage){
        JPanel buildingPanel = new JPanel(new BorderLayout());
        JLabel background = new JLabel(new ImageIcon(backgroundImage));
        background.setPreferredSize(new Dimension(700, 500));
        background.setLayout(new BorderLayout());
        buildingPanel.add(background, BorderLayout.CENTER);

        // Add two buttons to our pane
        NPC = new JButton(npc);
        game = new JButton("Play!");
        exit = new JButton("Exit");

        NPC.setPreferredSize(new Dimension(175, 45));

        game.setPreferredSize(new Dimension(75, 45));
        exit.setPreferredSize(new Dimension(75, 45));

        JLabel welcome = new JLabel("Welcome to " + name + "!" );
        Font heading = new Font("Times Roman", Font.BOLD, 20);
        welcome.setFont(heading);
        welcome.setOpaque(true);
        welcome.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(NPC);
        buttonPanel.add(game);
        buttonPanel.add(exit);

        background.add(buttonPanel, BorderLayout.SOUTH);
        background.add(welcome, BorderLayout.PAGE_START);
        return buildingPanel;
    }
    private JPanel createNPC(String message, String sourcepath, Color background){
        JPanel NPCpanel = new JPanel(new BorderLayout());

        JLabel lib = new JLabel(new ImageIcon(sourcepath));
        lib.setPreferredSize(new Dimension(700, 500));
        lib.setLayout(new BorderLayout());
        NPCpanel.add(lib, BorderLayout.CENTER);

        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                buildingController.showBuilding();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(exit);

        JTextArea empty = new JTextArea(" \n \n \n \n");
        empty.setOpaque(false);

        JTextArea convo = new JTextArea(message);
        convo.setBackground(background);
        convo.setOpaque(true);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.setOpaque(false);
        textPanel.add(convo, BorderLayout.PAGE_START);

        lib.add(empty, BorderLayout.PAGE_START);
        lib.add(textPanel, BorderLayout.WEST);
        lib.add(buttonPanel, BorderLayout.PAGE_END);
        return NPCpanel;
    }

}
