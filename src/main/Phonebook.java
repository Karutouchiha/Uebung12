package main;

import java.io.*;
import java.util.Scanner;
import java.util.TreeMap;

public class Phonebook {
    //name,Adresse,Phonenumber
    TreeMap<String,String[]> phonebook = new TreeMap();

    public void setPhonebook(String name, String address, String phonenumber){
        phonebook.put(phonenumber,new String[]{name, address});
    }
    public void delete(String phonenumber){
        phonebook.remove(phonenumber);
    }
    public void save(){
        try(FileWriter fw= new FileWriter(new File("Phonebook.csv"))){
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
    public String[] load(){
        String[] s = new String[3];
        try {
            Scanner scanner = new Scanner(new File("Phonebook.csv"));
            scanner.useDelimiter(";");
            for (int i=0;scanner.hasNext();i++){
                s[i]=(scanner.next()+"\n");
            }

            scanner.close();
        }
        catch (IOException ex){
            s[0]=ex.getMessage();
        }
        return s;
    }
}
