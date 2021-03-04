package main;

import address.ControllerAddress;
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
    private int index=ph.getIndex();
    private Stage stage;
    @FXML private TextField phone;
    @FXML private TextField name;
    @FXML private TextField address;
    @FXML private Text value;
    public Phonebook getPh() {
        return ph;
    }

    public void setPh(Phonebook ph) {
        this.ph = ph;
    }
    public Stage getStage() {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void save(){
        ph.save();
    }
    public void load(){
        ph.load();
        update();
    }
    public void move(MouseEvent event){
        Object node = event.getSource();
        Polygon pol = (Polygon)node;
        String id = pol.getId();
       ph.move(id);
        update();
    }
    public void update(){
        try {
        ArrayList al = new ArrayList();
        al.addAll(ph.getPhonebook().keySet());
        this.index= ph.getIndex();
        String[] s =ph.getPhonebook().get(al.toArray()[index]);
        phone.setText(al.get(index).toString());
        name.setText(s[0]);
        address.setText(s[1]);
        value.setText((index+1)+"/"+al.size());
        }
        catch (IndexOutOfBoundsException ex){
            System.out.println("Es wurde kein Datensatz gefunden");
            phone.setText("");
            name.setText("");
            address.setText("");
            value.setText("0/0");
            ph.setIndex(0);
        }
    }
    public void changeScene(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/address/Address.fxml"));
            Parent root = loader.load();
            ControllerAddress AC = loader.getController();
            AC.setPh(this.ph);
            AC.update();
            Stage stage = new Stage();
            stage.setTitle("Phonebook edit");
            stage.getIcons().add(new Image("https://s2.qwant.com/thumbr/0x380/c/e/17fc1f9bbfe37c9fb092530587faeb7b246672b67d6cf2b8be8a02d49f8e36/Address-Book-icon.png?u=http%3A%2F%2Ficons.iconarchive.com%2Ficons%2Fartua%2Fmac%2F512%2FAddress-Book-icon.png&q=0&b=1&p=0&a=1"));
            stage.setScene(new Scene(root));
            AC.setStage(stage);
            stage.show();
            this.stage.close();
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
