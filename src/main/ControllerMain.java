package main;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControllerMain {
    Phonebook ph = new Phonebook();
    @FXML private TextField phone;
    @FXML private TextField name;
    @FXML private TextField address;
    public Phonebook getPhonebook() {
        return ph;
    }

    public void save(){
        ph.setPhonebook("adfs","afasdf","sadf");
        ph.setPhonebook("asgd","asdg","safeva");
        ph.delete("safeva");
        ph.save();
    }
    public void load(){
        String[] strings =ph.load();
        phone.setText(strings[0]);
        name.setText(strings[1]);
        address.setText(strings[2]);
    }
}
