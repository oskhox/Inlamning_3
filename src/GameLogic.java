import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//i gameUI, instansiera denna klass med "jb.addMouseListener(new MouseAdapterDemo()); för varje knapp

public class GameLogic extends MouseAdapter {
    private int currentButton = 0;
    private boolean buttonClickable = false;

    //behövs konstruktor?
    GameLogic() {

    }

    @Override
    public void mouseEntered(MouseEvent entered) {
        System.out.println("Mouse entered.");
        //ogiltiga rutor blir ljusröda och de två giltiga blir ljusgröna när man hovrar
        //anropar checkValidButton, skickar in currentButton och tar emot true eller false
        //sparar true eller false i klassens variabel
        //om true, sätt currentButton som ljusgrön samt buttonClickable som true
        //om false, sätt currentButton som ljusröd samt buttonClickable som false
    }

    @Override
    public void mouseExited(MouseEvent exited) {
        System.out.println("Mouse exited.");
        //återställer alltid aktuell knapps färg till ursprungsfärg när denna blir aktiverad
        //sätt alltid currentButton till ursprungsfärg
    }

    @Override
    public void mouseClicked(MouseEvent clicked) {
        System.out.println("The mouse was clicked.");
        //om buttonClickable satts till true av mouseEntered, och användaren trycker, så ska knappen som tryckts
        //byta plats med den tomma knappen.

        //om istället getSource är knappen "Nytt spel" så ska alla brickona blandas i slumpmässig ordning, inga identiska
    }

    //hjälpmetod checkValidButton som avgör om ruta är giltig = ljusgrön eller ogiltig = ljusröd
    boolean checkValidButton(int button) {
        boolean isValid = false;

        //isValid = true om knappen norr om, söder om, väster om och öster om har innehållet "" (alltså är tom).
        //returnera true

        //isValid = false om ovan inte stämmer, alltså genom en else
        //returnera false
        return false; //temporärt
    }

    //hjälpmetod checkNearbyButtons som identifierar knapparna ovanför, under, till höger och till vänster om aktuell knapp i gridLayout

    //metod som kontrollerar om alla brickor ligger rätt i nummerordning
    //Vi testar varje rad för sig, att rad 1 är 1-4, rad 2 är 5-8, rad 3 är 9-12, rad 4 är 13-15 + tom


}