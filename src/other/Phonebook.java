package other;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Phonebook {
    private TreeMap<String,String[]> phonebook = new TreeMap();
    int Index = 0;

    public int getIndex() {
        return Index;
    }

    public void setIndex(int index) {
        Index = index;
    }
    public void setPhonebook(String name, String address, String phonenumber){
        if (!phonebook.containsKey(phonenumber)) {
            phonebook.put(phonenumber,new String[]{name, address});
        } else {
            System.out.println("Telefonnummer bereits vergeben.");
        }
    }
    public TreeMap<String, String[]> getPhonebook() {
        return phonebook;
    }
    public void delete(String phonenumber){
        phonebook.remove(phonenumber);
    }
    public void move(String id){
        if (id.equals("next")){
            if (Index==phonebook.size()-1){
                Index=0;
            }
            else {
                Index++;
            }
        }
        else {
            if (Index==0){
                Index=phonebook.size()-1;
            }
            else {
                Index--;
            }
        }
    }

    public void change(String key, String addess, String name ,int index){
        if (!phonebook.isEmpty()) {
            if (phonebook.containsKey(key)) {
                delete(key);
            } else {
                ArrayList a = new ArrayList();
                a.addAll(phonebook.keySet());
                String s = a.get(index).toString();
                delete(s);
            }
            setPhonebook(name, addess, key);
        }
        else {
            setPhonebook(name,addess,key);
            System.out.println("Da kein Eintrag forhanden ist wurde der Eintrag hinzugefügt");
        }
    }
    public void save(){
        try(FileWriter fw= new FileWriter(new File("Phonebook.csv"))){
            BufferedWriter bw = new BufferedWriter(fw);
            for (String key: phonebook.keySet()) {
                String[] s = phonebook.get(key);
                bw.write(key+";"+s[0]+";"+s[1]+";\n;");
            }
            bw.close();
            System.out.println("Successfully saved");
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
                String[] s = new String[4];
                for (int i = 0; i<4&&scanner.hasNext(); i++) {
                    s[i] = (scanner.next());
                }
                arrayList.add(s);
            }while (scanner.hasNext());
            scanner.close();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        for (int i=0;i<arrayList.size();i++) {
            String[] strings = arrayList.get(i);
            boolean nnull = false;
            for (String s : strings) {
                if (s == null) {
                    nnull = true;
                }
            }
            if (!nnull) {
                setPhonebook(strings[1], strings[2], strings[0]);
                System.out.println("Successfully loaded");
            }
            else {
                System.out.println("Data loading failed");
            }
        }
    }
}
