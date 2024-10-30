import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//i gameUI, instansiera denna klass med "jb.addActionListener(new GameLogic()); för varje knapp (istället för this om i

public class GameLogic implements ActionListener {

    //behövs konstruktor?
    GameLogic() {

    }

    @Override
    public void actionPerformed(ActionEvent e){

        if (e.getSource() == "button1") { //eller vad knappen heter
            System.out.println("Knapp 1 är tryckt på");
            //knappen som tryckts ska byta plats med den tomma knappen.
        }
        if (e.getSource() == "buttonNewGame") {
            System.out.println("Skapar nytt spel");
            //logik för nytt spel, alla brickor blandas i slumpmässig ordning, inga identiska
        }

    }

    //hjälpmetod checkValidButton som avgör om ruta är giltig för flytt eller inte.
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