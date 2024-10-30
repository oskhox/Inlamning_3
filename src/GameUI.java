import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class GameUI extends JFrame {
    private final JPanel panelBoxes = new JPanel();
    private final JPanel panelBottom = new JPanel();
    private final JButton startButton = new JButton("Nytt spel");
    private final JLabel victoryLabel = new JLabel("Grattis, du vann!");
    private ArrayList<JButton> buttons = new ArrayList<>();
    private int index = 15;

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
        panelBoxes.setLayout(new GridLayout(4, 4,1,1));
        panelBottom.setLayout(new FlowLayout());

        //Lägger till paneler till frame
        add(panelBoxes, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);

        //Lägger till knapp och label till bottenpanel
        panelBottom.add(startButton);
        panelBottom.add(victoryLabel);
        panelBottom.setBackground(Color.ORANGE);

        //Sätter ram och padding panel boxes
        Border buttonPadding = new EmptyBorder(1, 1, 1, 1);
        Border buttonBorder = new LineBorder(Color.orange, 6, true);

        //Sätter design start knapp
        Border startButtonPadding = new EmptyBorder(5, 2, 5, 2);
        Border startButtonBorder = new LineBorder(Color.BLACK, 3, true);
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(Color.BLACK);
        startButton.setOpaque(true);
        startButton.setFont(new Font("Poppins", Font.BOLD, 20));
        startButton.setBorder(buttonBorder);
        startButton.setBorder(new CompoundBorder(startButtonBorder,startButtonPadding));
        startButton.setPreferredSize(new Dimension(150,50));

        //Design och viss funktionalitet för vinst label
        victoryLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        victoryLabel.setVisible(false);


        //Iterierar genom knapparna och lägger till i Arraylist
        for (int i = 1; i <= index; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setFont(new Font("Poppins", Font.BOLD, 30));
            button.setBorder(new CompoundBorder(buttonBorder,buttonPadding));
            button.setPreferredSize(new Dimension(50,50));

            panelBoxes.add(button);
            buttons.add(button);
        }


    }

    public static void main(String[] args) {

        GameUI main = new GameUI();

    }
}

