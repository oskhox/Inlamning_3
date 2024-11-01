import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameUI extends JFrame {
    private JPanel panelBoxes = new JPanel(new GridLayout(4, 4));
    private final JPanel panelBottom = new JPanel();
    private final JButton startButton = new JButton("Nytt spel");
    private final JLabel victoryLabel = new JLabel("Grattis, du vann!");
    Border buttonPadding = new EmptyBorder(1, 1, 1, 1);
    Border buttonBorder = new LineBorder(Color.orange, 6, true);
    private JButton[][] buttonsArray = new JButton[4][4]; //Deklarerar 2D-array av JButtons för att möjliggöra sökning
    GameLogic gl = new GameLogic(this); //Skapar upp en instans av GameLogic och skickar in aktuell instans av GameUI. Används på varje knapp.

    //Konstruktor
    GameUI() {
        userInterface();
        setTitle("15-spel");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeButtons(panelBoxes, gl);
        updatePanelBoxes();
    }

    public JPanel getPanelBoxes() {
        return panelBoxes;
    }

    public JButton[][] getButtonsArray() {
        return buttonsArray; //Returnera knapparnas array till GameLogic
    }

    public void userInterface() {
        //Sätter layouter
        setLayout(new BorderLayout());
        panelBoxes.setLayout(new GridLayout(4, 4, 1, 1));
        panelBottom.setLayout(new FlowLayout());

        //Lägger till paneler till frame
        add(panelBoxes, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);

        //Lägger till knapp och label till bottenpanel
        panelBottom.add(startButton);
        panelBottom.add(victoryLabel);
        panelBottom.setBackground(Color.ORANGE);

        //Sätter ram och padding panel boxes

        //Sätter design på knappen "Nytt spel"
        Border startButtonPadding = new EmptyBorder(5, 2, 5, 2);
        Border startButtonBorder = new LineBorder(Color.BLACK, 3, true);
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(Color.BLACK);
        startButton.setOpaque(true);
        startButton.setFont(new Font("Poppins", Font.BOLD, 20));
        startButton.setBorder(buttonBorder);
        startButton.setBorder(buttonPadding);
        startButton.setBorder(new CompoundBorder(startButtonBorder, startButtonPadding));
        startButton.setPreferredSize(new Dimension(150, 50));
        //TO-DO: Gör så att startknappen innebär en ny spelomgång med lambda
        startButton.addActionListener(e -> gl.beginNewGame());

        //Design och viss funktionalitet för vinst label
        victoryLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        victoryLabel.setVisible(false);

    }

    //Gjort till en metod så den kan anropas
    //Itererar genom knapparna och lägger till var och en i buttonsArray samt i panelen
    public void initializeButtons(JPanel panelBoxes, ActionListener gl) {
        int number = 1; //nummer på första knappen
        //Skapar upp 4 rader
        for (int row = 0; row < 4; row++) {
            //För varje rad skapar den 4 kolumner med en knapp i varje
            for (int column = 0; column < 4; column++) {
                JButton button;
                if (number <= 15) {
                    button = new JButton(String.valueOf(number)); //Numret för varje knapp skickas in som inparameter
                    number++;
                } else {
                    button = new JButton(""); //Den 16:e knappen blir tom
                }
                //Sätter visuella attribut på varje knapp
                button.setFont(new Font("Poppins", Font.BOLD, 30));
                button.setBorder(new CompoundBorder(buttonBorder, buttonPadding));
                button.setPreferredSize(new Dimension(50, 50));

                //Lägger till lyssnare och logik för varje knapp
                button.addActionListener(gl);

                //Lägger in knapp-objektet på rätt plats i 2D-arrayen
                buttonsArray[row][column] = button;

                //Lägger även in knapp-objektet på motsvarande numrerad plats i panelen
                panelBoxes.add(button);
            }
        }
    }

    //Metoden uppdaterar panelen med nya knappar
    public void updatePanelBoxes() {
        panelBoxes.removeAll();

        for (int row = 0; row < buttonsArray.length; row++) {
            for (int column = 0; column < buttonsArray[row].length; column++) {
                panelBoxes.add(buttonsArray[row][column]);
            }
        }
        panelBoxes.revalidate();
        panelBoxes.repaint();
    }

    //Metod som skriver ut text vid vinst
    public void updateWinText() {
        if (gl.youWon()) {
            victoryLabel.setVisible(true);
        }
    }
}
