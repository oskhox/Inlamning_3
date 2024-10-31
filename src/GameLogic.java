import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLogic implements ActionListener {
    private GameUI gui; //Håller GameUI
    private final JButton[][] buttonsArray; //Håller arrayen med knapparna från GameUI

    GameLogic(GameUI gui_input) {
        this.gui = gui_input; //Hämtar nu gällande GameUI
        this.buttonsArray = gui.getButtonsArray(); //Hämtar knapparnas array från GameUI
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource(); //Hämtar in hela knappen som klickades på
        String buttonText = clickedButton.getText();
        System.out.println("Knappen " + buttonText + " är tryckt på."); //TO-DO: ta bort denna rad sen

        //Hämta positionen för den klickade knappen genom att söka igenom 2D-arrayen
        //Först söks varje rad
        for (int row = 0; row < 4; row++) {
            //Och inom varje rad söks varje kolumn igenom
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

    //Metod för att byta platser på två knappar
    public void switchButtons(JButton clickedButton, JButton emptyButton) {
        String clickedButtonText = clickedButton.getText(); //hämtar texten på den klickade knappen
        clickedButton.setText(emptyButton.getText()); //sätter tom text på den klickade knappen
        emptyButton.setText(clickedButtonText); //sätter ny text på den tomma knappen
    }

    //TO DO: Metod beginNewGame() som skapar ett nytt spel, som slumpar knappar 1-15 och blandar knappar, ändra både 2d-array och GridLout

    //TO DO: Metod youWon() som kontrollerar om alla brickor ligger rätt i nummerordning, vi testar varje rad för sig sannolikt i arrayen,
    //rad 1 ska vara 1-4, rad 2 ska vara 5-8, rad 3 ska vara 9-12, rad 4 ska vara 13-15 + en tom "". skriv då ut "Grattis, du vann!”

    //hämta in aktuell 2D-array
    //ha en räknare
    //loopa igenom
}