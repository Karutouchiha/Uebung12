package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import other.Phonebook;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ControllerMain implements Initializable {
    private Phonebook ph = new Phonebook();
    private int index=0;
    @FXML private TextField phone;
    @FXML private TextField name;
    @FXML private TextField address;
    @FXML private Text value;
    public Phonebook getPhonebook() {
        return ph;
    }

    public void save(){
        ph.save();
    }
    public void load(){
        ph.load();
        update();
        index=0;
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
        //System.out.println(index);
        update();
    }
    public void update(){
        try {
        ArrayList al = new ArrayList();
        al.addAll(ph.getPhonebook().keySet());

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
    public void changeScene(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/address/Address.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Phonebook edit");
            stage.getIcons().add(new Image("https://s2.qwant.com/thumbr/0x380/c/e/17fc1f9bbfe37c9fb092530587faeb7b246672b67d6cf2b8be8a02d49f8e36/Address-Book-icon.png?u=http%3A%2F%2Ficons.iconarchive.com%2Ficons%2Fartua%2Fmac%2F512%2FAddress-Book-icon.png&q=0&b=1&p=0&a=1"));
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        //System.out.println("Change");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ph.load();/*
        ph.setPhonebook("Andreas","Wels 4600","+43 676 12345678");
        ph.setPhonebook("Atonio","Attnang-P. 4800","+43 654 12351435");
        ph.setPhonebook("Lena","Attnang-P. 4800","+43 654 15675675");
        ph.setPhonebook("Patrick","Vöcklabruck. 4840","+43 654 15677441");
        ph.setPhonebook("Mathias","Attnang-P. 4800","+43 654 12555476");//*/
        update();
        name.setEditable(false);
        phone.setEditable(false);
        address.setEditable(false);
    }
}
