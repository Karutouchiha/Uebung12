package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Phonebook {


    //name,Adresse,Phonenumber
    private TreeMap<String,String[]> phonebook = new TreeMap();

    public void setPhonebook(String name, String address, String phonenumber){
        phonebook.put(phonenumber,new String[]{name, address});
    }
    public TreeMap<String, String[]> getPhonebook() {
        return phonebook;
    }
    public void delete(String phonenumber){
        phonebook.remove(phonenumber);
    }
    public void save(){
        try(FileWriter fw= new FileWriter(new File("Phonebook.csv"))){
            BufferedWriter bw = new BufferedWriter(fw);
            for (String key: phonebook.keySet()) {
                String[] s = phonebook.get(key);
                bw.write(key+";"+s[0]+";"+s[1]+"\n");
            }
            bw.close();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void load(){
        ArrayList<String[]> arrayList = new ArrayList();
        try {
            Scanner scanner = new Scanner(new File("Phonebook.csv"));
            scanner.useDelimiter(";");
            do{
                String[] s = new String[3];
                for (int i = 0; i<3&&scanner.hasNext(); i++) {
                    s[i] = (scanner.next()+"\n");
                }
                scanner.nextLine();
                arrayList.add(s);
            }while (scanner.hasNext());
            scanner.close();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        for (int i=0;i<arrayList.size();i++) {
            String[] strings = arrayList.get(i);
            setPhonebook(strings[1],strings[2],strings[0]);
            System.out.println(phonebook.toString());
        }
    }
}
