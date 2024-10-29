import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class GameUI extends JFrame {

    private JPanel panelBoxes = new JPanel();
    private JPanel panelBottom = new JPanel();
    private JButton startButton = new JButton("Nytt spel");
    private JLabel victoryLabel = new JLabel("Grattis, du vann!");
    private ArrayList<JButton> buttons = new ArrayList<>();
    private int buttonIndex = 15;

    GameUI() {

        userInterface();
        setTitle("15-spel");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void userInterface() {

        //Sätter layouter
        setLayout(new BorderLayout());
        panelBoxes.setLayout(new GridLayout(4, 4, 5, 5));
        panelBottom.setLayout(new FlowLayout());

        //Lägger till paneler till frame
        add(panelBoxes, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);

        //Lägger till knapp och label till bottenpanel
        panelBottom.add(startButton);
        panelBottom.add(victoryLabel);
        panelBottom.setBackground(Color.orange);

        //Sätter ram och paddding knappar
        Border buttonBorder = new EmptyBorder(2, 2, 5, 2);
        Border buttonPadding = new LineBorder(Color.black, 1, true);

        //Sätter design start knapp
        startButton.setBackground(Color.ORANGE);
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setBorder(buttonBorder);
        startButton.setBorder(new CompoundBorder(buttonBorder, buttonPadding));
        startButton.setPreferredSize(new Dimension(150, 50));

        //Design och viss funktionalitet för vinst label
        victoryLabel.setFont(new Font("Arial", Font.BOLD, 20));
        victoryLabel.setVisible(false);


        //Iterierar genom knapparna och lägger till i Arraylist
        for (int i = 1; i <= buttonIndex; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setBorder(new CompoundBorder(buttonBorder, buttonPadding));
            button.setPreferredSize(new Dimension(50, 50));

            panelBoxes.add(button);
            buttons.add(button);
        }

    }
}

