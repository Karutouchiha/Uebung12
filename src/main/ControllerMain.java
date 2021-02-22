package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerMain implements Initializable {
    Phonebook ph = new Phonebook();
    int index=0;
    @FXML private TextField phone;
    @FXML private TextField name;
    @FXML private TextField address;
    public Phonebook getPhonebook() {
        return ph;
    }

    public void save(){
        ph.save();
    }
    public void load(){
        ph.load();
    }
    public void move(MouseEvent event){
        Object node = event.getSource();
        Polygon pol = (Polygon)node;
        String id = pol.getId();
        if (id.equals("next")){
            if (index==ph.getPhonebook().size()-1){
                index=0;
            }
            else {
                index++;
            }
        }
        else {
            if (index==0){
                index=ph.getPhonebook().size()-1;
            }
            else {
                index--;
            }
        }
        System.out.println(index);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ph.load();
        ph.setPhonebook("Andreas","Wels 4600","+43 676 12345678");
        ph.setPhonebook("Atonio","Attnang-P. 4800","+43 654 12351435");
        ph.setPhonebook("Lena","Attnang-P. 4800","+43 654 15675675");
        ph.setPhonebook("Patrick","Vöcklabruck. 4840","+43 654 15677435");
        ph.setPhonebook("Mathias","Attnang-P. 4800","+43 654 12555435");
    }
}
