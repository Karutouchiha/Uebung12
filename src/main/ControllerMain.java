package main;

import javafx.fxml.FXML;

public class ControllerMain {
    Phonebook ph = new Phonebook();

    public Phonebook getPhonebook() {
        return ph;
    }

    public void save(){
        ph.setPhonebook("adfs","afasdf","sadf");
        ph.setPhonebook("asgd","asdg","safeva");
        ph.delete("safeva");
    }
}
