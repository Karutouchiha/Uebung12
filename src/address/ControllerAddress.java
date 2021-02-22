package address;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import main.ControllerMain;
import other.Phonebook;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerAddress implements Initializable {
    private Phonebook ph = new Phonebook();
    private ControllerMain CM;
    private int index=0;
    @FXML private TextField phone;
    @FXML private TextField name;
    @FXML private TextField address;
    @FXML private Text value;

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
        update();
    }

    public void update(){
        try {
            ArrayList al = new ArrayList();
            al.addAll(CM.getPhonebook().getPhonebook().keySet());

            String[] s =ph.getPhonebook().get(al.toArray()[index]);
            phone.setText(al.get(index).toString());
            name.setText(s[0]);
            address.setText(s[1]);
            value.setText((index+1)+"/"+al.size());
        }
        catch (IndexOutOfBoundsException ex){
            System.out.println("Es wurde kein Datensatz gefunden");
            index=0;
        }
    }

    public void Phonebook(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/Mainscreen.fxml"));
            loader.load();
            CM = loader.getController();
            ph = CM.getPhonebook();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Phonebook();
        update();
    }
}
