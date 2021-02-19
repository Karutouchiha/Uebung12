package Address;

import javafx.fxml.FXMLLoader;
import main.Phonebook;

import java.io.IOException;

public class ControllerAddress{

    main.Phonebook phonebook = new Phonebook();
    public void Phonebook(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/Mainscreen.fxml"));
            loader.load();
            main.ControllerMain mainC = loader.getController();
            phonebook = mainC.getPhonebook();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}
