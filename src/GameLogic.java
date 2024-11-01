import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameLogic implements ActionListener {
    private final GameUI gui;
    private final JButton[][] buttonsArray; //Håller arrayen med knapparna från GameUI

    GameLogic(GameUI gui_input) {
        this.gui = gui_input;
        this.buttonsArray = gui.getButtonsArray(); //Hämtar nu gällande array från GameUI
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource(); //Hämtar in hela knappen som klickades på.

        //Hämtar positionen för den klickade knappen genom att söka igenom 2D-arrayen
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                if (buttonsArray[row][column] == clickedButton) { //Om någon knapp i 2D-arrayen matchar referensen för klickad knapp
                    //Kollar först i varje riktning efter tom knapp, börjar med "up" först
                    JButton emptyButton = returnEmptyButton("up", row, column);
                    if (emptyButton == null)
                        emptyButton = returnEmptyButton("down", row, column);
                    if (emptyButton == null)
                        emptyButton = returnEmptyButton("left", row, column);
                    if (emptyButton == null)
                        emptyButton = returnEmptyButton("right", row, column);

                    //Byter sedan plats på knapparna om det finns en tom knapp ("") som inte är null
                    if (emptyButton != null) {
                        switchButtons(clickedButton, emptyButton);
                    }
                    break; //Avbryter när väl en knapp har flyttats
                }
            }
        }
    }

    //Metod för att returnera tom knapp, om det finns. Skicka in önskad riktning att kontrollera samt klickade knappens rad och kolumn.
    public JButton returnEmptyButton(String direction, int row, int column) {
        JButton nextButton = null;
        //Kontrollerar först så att det finns knappar i respektive riktning
        if (direction.equals("up") && row > 0) {
            nextButton = buttonsArray[row - 1][column];
        } else if (direction.equals("down") && row < 3) {
            nextButton = buttonsArray[row + 1][column];
        } else if (direction.equals("left") && column > 0) {
            nextButton = buttonsArray[row][column - 1];
        } else if (direction.equals("right") && column < 3) {
            nextButton = buttonsArray[row][column + 1];
        }

        //Kontrollerar sen om den angränsande knappen är tom och returnera den om den är det
        if (nextButton != null && nextButton.getText().isEmpty()) {
            return nextButton;
        }
        //Om ingen tom knapp hittades returneras null
        return null;
    }

    //Metod för att byta plats på två knappar
    public void switchButtons(JButton clickedButton, JButton emptyButton) {
        String clickedButtonText = clickedButton.getText(); //Hämtar texten på den klickade knappen
        clickedButton.setText(emptyButton.getText()); //Sätter tom text på den klickade knappen
        emptyButton.setText(clickedButtonText); //Sätter ny text på den tomma knappen
        gui.updateWinText(); //Kontrollerar om vinst efter förflyttning
    }

    //Metod för att lägga siffrorna i en lista och shuffla
    public List<JButton> toListAndShuffle() {
        JButton[][] buttonsArray = gui.getButtonsArray();
        List<JButton> buttonList = new ArrayList<>();

        for (JButton[] jButtons : buttonsArray) {
            for (JButton jButton : jButtons) {
                buttonList.add(jButton);
            }
        }
        Collections.shuffle(buttonList);
        return buttonList;
    }

    //Metod för att lägga tillbaka de shufflade siffrorna tillbaka i en 2 dimensionell array
    public void updateButtonsArray(List<JButton> shuffledList) {
        JButton[][] buttonsArray = gui.getButtonsArray();
        int index = 0;

        for (int row = 0; row < buttonsArray.length; row++) {
            for (int column = 0; column < buttonsArray[row].length; column++) {
                JButton button = shuffledList.get(index++);
                buttonsArray[row][column] = button;
                gui.getPanelBoxes().add(button);
            }
        }
        gui.getPanelBoxes().revalidate();
        gui.getPanelBoxes().repaint();
    }

    //Metod för att starta det nya spelet, anropar våra tre andra metoder
    public void beginNewGame() {
        List<JButton> shuffledList = toListAndShuffle();
        updateButtonsArray(shuffledList);
        gui.updatePanelBoxes();
        gui.getVictoryLabel().setVisible(false);
    }

    //Metod för att kontrollera om vinst, detta sker efter varje förflyttning
    public boolean youWon() {
        int buttonToCheck = 1;
        int buttonsMatched = 0;

        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                //För varje knapp
                String buttonText = buttonsArray[row][column].getText();
                //Om knappen inte är tom och matchning 1-15 sker
                if (!buttonText.isEmpty() && buttonText.equals(String.valueOf(buttonToCheck))) {
                    buttonsMatched++;
                }
                buttonToCheck++;
            }
        }
        //Returnerar true om första eller sista knappen är tom och 15 knappar har matchat
        return (buttonsArray[3][3].getText().isEmpty() && (buttonsMatched == 15));
    }
}