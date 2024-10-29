import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameUI extends JFrame {
    JFrame frame = new JFrame("15-spel");
    JPanel panel = new JPanel();

    GameUI(){

        //loop som skapar upp knappar och lägger till i panel
        ArrayList<String> buttons = new ArrayList<String>();
        for (int i = 0; i < ; i++) {



            //ändring
        }

        //layout
        //south med knappen nytt spel och text

        //JPanel setup
        add(jp);
        jp.setLayout(new BorderLayout());
        jp.add(northPanel, BorderLayout.NORTH);
        jp.add(westPanel, BorderLayout.WEST);
        jp.add(eastPanel, BorderLayout.EAST);
        jp.add(southPanel, BorderLayout.SOUTH);
        setTitle("Sten, sax, påse");
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //initiera ActionListener
        rock1.addActionListener(this);
        scissors1.addActionListener(this);
        paper1.addActionListener(this);


    }



}
