package main;

import javafx.fxml.FXML;

public class ControllerMain {
    Phonebook ph = new Phonebook();
    public void save(){
        ph.setPhonebook("adfs","afasdf","sadf");
        ph.setPhonebook("asgd","asdg","safeva");
    }
}
