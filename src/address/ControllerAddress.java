package address;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import other.Phonebook;
import java.util.ArrayList;

public class ControllerAddress {
    private Phonebook ph = new Phonebook();
    private ArrayList keyl;
    private int index=0;
    private boolean isadd=false;
    @FXML private TextField phone;
    @FXML private TextField name;
    @FXML private TextField address;
    @FXML private Text value;

    public void setPh(Phonebook ph) {
        this.ph = ph;
    }
    public void change(){
        if (name.getText().equals("")||phone.getText().equals("")||address.getText().equals("")) {
            System.out.println("Bitte füllen Sie alle Felder aus");
        }
        else {
            ph.change(phone.getText(), address.getText(), name.getText(), index);
        }
        //System.out.println(ph.getPhonebook().toString());
        update();
    }
    public void add(){
        if (name.getText().equals("")||phone.getText().equals("")||address.getText().equals("")) {
            System.out.println("Bitte füllen Sie alle Felder aus");
        }
        else {
            if (isadd) {
                if (!ph.getPhonebook().containsKey(phone.getText())) {
                        ph.setPhonebook(name.getText(), address.getText(), phone.getText());
                } else {
                    System.out.println("Telefonnummer bereits vergeben.");
                }
                update();
            }
        }

    }
    public void delete(){
        if (ph.getPhonebook().containsKey(phone.getText())){
            ph.delete(keyl.toArray()[index].toString());
        }
        else {
            System.out.println("Diese Telefonnummer ist nicht im Telefonbuch vorhanden.");
        }
        //System.out.println(ph.getPhonebook().toString());
        update();
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
        update();
    }

    public void update(){
        try {
            ArrayList al = new ArrayList();
            keyl=al;
            al.addAll(ph.getPhonebook().keySet());

            String[] s =ph.getPhonebook().get(al.toArray()[index]);
            phone.setText(al.get(index).toString());
            name.setText(s[0]);
            address.setText(s[1]);
            value.setText((index+1)+"/"+al.size());
        }
        catch (IndexOutOfBoundsException ex){
            if (ph.getPhonebook().isEmpty()){
                System.out.println("");
                phone.setText("");
                name.setText("");
                address.setText("");
                value.setText("0/0");
                index = 0;
            }
        }
    }
}
