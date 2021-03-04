package other;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Phonebook {
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
    public void change(String key, String addess, String name ,int index){
        if (phonebook.containsKey(key)){
            delete(key);
        }
        else {
            ArrayList a = new ArrayList();
            a.addAll(phonebook.keySet());
            String s = a.get(index).toString();
            delete(s);
        }
        setPhonebook(name,addess,key);
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
