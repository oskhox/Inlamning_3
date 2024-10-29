import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//i gameUI, instansiera denna klass med "jb.addMouseListener(new GameLogic()); för varje knapp

public class GameLogic extends MouseAdapter {

    //behövs konstruktor?
    GameLogic() {

    }

    @Override
    public void mouseEntered(MouseEvent entered) {
        System.out.println("Mouse entered.");
        //ogiltiga rutor blir ljusröda och de två giltiga blir ljusgröna när man hovrar

    }

    @Override
    public void mouseExited(MouseEvent exited) {
        System.out.println("Mouse exited.");
        //återställer alltid aktuell knapps färg till ursprungsfärg när denna blir aktiverad
    }

    @Override
    public void mouseClicked(MouseEvent clicked) {
        System.out.println("The mouse was clicked.");
        //1. knappen som tryckts ska byta plats med den tomma knappen.

        //2. om istället getSource är knappen "Nytt spel" så ska alla brickona blandas i slumpmässig ordning, inga identiska
    }

    //ON: FOKUSERA på hjälpmetoden checkNearbyButtons först. <- <- <-
    //hjälpmetod checkNearbyButtons som identifierar knapparna ovanför, under, till höger och till vänster om aktuell knapp i gridLayout
    //genom att vi har alla knappar i ett rutnät har alla knappar ett fast index som kan räknas ut
    //den metoden tar emot int index från mouseClicked som skickar aktuell knapps index

    //hjälpmetod checkValidButton som avgör om ruta är giltig eller inte
    boolean checkValidButton(int button) {
        boolean isValid = false;

        //isValid = true om knappen norr om, söder om, väster om och öster om har innehållet "" (alltså är tom).
        //returnera true

        //isValid = false om ovan inte stämmer, alltså genom en else
        //returnera false
        return false; //temporärt
    }

    //till sist metod som kontrollerar om alla brickor ligger rätt i nummerordning
    //vi testar varje rad för sig, att rad 1 är 1-4, rad 2 är 5-8, rad 3 är 9-12, rad 4 är 13-15 + tom
    //skriv då ut "Grattis, du vann!”
}