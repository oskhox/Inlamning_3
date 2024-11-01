import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameLogic implements ActionListener {
    private GameUI gui; //Håller GameUI
    private final JButton[][] buttonsArray; //Håller arrayen med knapparna från GameUI

    GameLogic(GameUI gui_input) {
        this.gui = gui_input; //Hämtar nu gällande GameUI
        this.buttonsArray = gui.getButtonsArray(); //Hämtar nu gällande array från GameUI
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource(); //Hämtar in hela knappen som klickades på
        String buttonText = clickedButton.getText();
        System.out.println("Knappen " + buttonText + " är tryckt på."); //TO-DO: ta bort denna rad sen

        //Hämta positionen för den klickade knappen genom att söka igenom 2D-arrayen
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                if (buttonsArray[row][column] == clickedButton) { //Om någon knapp i 2D-arrayen matchar referensen för klickad knapp
                    //Kollar först i varje riktning efter tom knapp, börjar med "up" först
                    JButton emptyButton = returnEmptyButton("up", row, column);
                    if (emptyButton == null) //Dvs. hittade ingen empty button
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


        //TO-DO: Om det är startknappen som trycks på, skapa nytt spel
        //if (e.getSource() == startButton) {
        //System.out.println("Skapar nytt spel");
        //beginNewGame();
        //logik för nytt spel, alla brickor blandas i slumpmässig ordning, inga identiska
    }

    //Hjälpmetod som returnerar tom knapp, om det finns. Skicka in önskad riktning att kontrollera samt klickade knappens rad och kolumn.
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

    //Byt plats på två knappar
    public void switchButtons(JButton clickedButton, JButton emptyButton) {
        String clickedButtonText = clickedButton.getText(); //Hämtar texten på den klickade knappen
        clickedButton.setText(emptyButton.getText()); //Sätter tom text på den klickade knappen
        emptyButton.setText(clickedButtonText); //Sätter ny text på den tomma knappen
        gui.updateWinText(); //Kontrollerar om vinst efter förflyttning
    }


    //Metod för att lägga siffrorna i en lista och shuffla
    public List<JButton> shuffleNumbersToList() {
        JButton[][] buttonsArray = gui.getButtonsArray();
        List<JButton> buttonList = new ArrayList<>();

        for (int row = 0; row < buttonsArray.length; row++) {
            for (int column = 0; column < buttonsArray[row].length; column++) {
                buttonList.add(buttonsArray[row][column]);
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
        List<JButton> shuffledList = shuffleNumbersToList();
        updateButtonsArray(shuffledList);
        gui.updatePanelBoxes();
    }

    //TO DO: Metod beginNewGame() som skapar ett nytt spel, som slumpar knappar 1-15 och blandar knappar, ändra både 2d-array och GridLout

    //Kontrollera om vinst, detta sker efter varje förflyttning
    public boolean youWon() {
        int buttonToCheck = 1;
        int buttonsMatched = 0;

        //Loopar igenom alla knappar
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                //För varje knapp;
                String buttonText = buttonsArray[row][column].getText();
                //Om knappen inte är tom och matchning 1-15 sker
                if (!buttonText.isEmpty() && buttonText.equals(String.valueOf(buttonToCheck))) {
                    buttonsMatched++;
                }
                buttonToCheck++;
            }
        }
        //Returnerar true om första eller sista knappen är tom och 15 knappar har matchat
        return (buttonsArray[0][0].getText().isEmpty() || buttonsArray[3][3].getText().isEmpty() && (buttonsMatched == 15));
    }
}