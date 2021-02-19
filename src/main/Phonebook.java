package main;

import java.io.*;
import java.util.TreeMap;

public class Phonebook {
    //name,Adresse,Phonenumber
    TreeMap<String,String[]> phonebook = new TreeMap();

    public void setPhonebook(String name, String address, String phonenumber){
        phonebook.put(phonenumber,new String[]{name, address});
        System.out.println(phonebook);
    }
    public void delete(String phonenumber){
        phonebook.remove(phonenumber);
        System.out.println(phonebook);
    }
    public void save(){
        try(FileWriter fw= new FileWriter(new File("Phone.csv"))){
            BufferedWriter bw = new BufferedWriter(fw);
            for (String key: phonebook.keySet()) {
                String[] s = phonebook.get(key);
                bw.write(key+";"+s[0]+";"+s[1]);
            }
            bw.close();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void load(){

    }
}
