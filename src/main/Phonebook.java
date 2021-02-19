package main;

import java.util.TreeMap;

public class Phonebook {
    //name,Adresse,Phonenumber
    TreeMap phonebook = new TreeMap();

    public void setPhonebook(String name, String address, String phonenumber){
        phonebook.put(phonenumber,new String[]{name, address});
        System.out.println(phonebook);
    }
    public void delete(String phonenumber){
        phonebook.remove(phonenumber);
        System.out.println(phonebook);
    }
    public void save(){

    }
    public void load(){

    }
}
