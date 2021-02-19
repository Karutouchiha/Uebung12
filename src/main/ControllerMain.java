package main;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ControllerMain {
    Phonebook ph = new Phonebook();
    @FXML private TextField phone;
    @FXML private TextField name;
    @FXML private TextField address;
    public Phonebook getPhonebook() {
        return ph;
    }

    public void save(){
        ph.setPhonebook("Andreas","Wels 4600","+43 676 12345678");
        ph.setPhonebook("Mathias","Attnang-P. 4800","+43 654 12351435");
        ph.save();
    }
    public void load(){
        ph.load();
    }
}
