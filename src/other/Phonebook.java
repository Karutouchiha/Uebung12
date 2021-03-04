package other;

import java.io.*;
import java.util.*;

public class Phonebook {
    private LinkedHashMap<String,String[]> phonebook = new LinkedHashMap<>();
    int Index = 0;

    public void sort(){
        TreeSet<String> a = new TreeSet<>();
        LinkedHashMap<String,String[]> ph = new LinkedHashMap<>(phonebook);
        for (String key: phonebook.keySet()){
            a.add(phonebook.get(key)[0]);
        }
        phonebook.clear();
        for (String name: a){
            for (String key: ph.keySet()) {
                if (name.equals(ph.get(key)[0])){
                    phonebook.put(key,ph.get(key));
                    break;
                }
            }
        }
    }
    public int getIndex() {
        return Index;
    }

    public void setIndex(int index) {
        Index = index;
    }
    public void setPhonebook(String name, String address, String phonenumber) {
        if (!(phonenumber.length()<14)) {
            if (testStringDig(phonenumber)) {
                if (!phonebook.containsKey(phonenumber)) {
                    phonebook.put(phonenumber, new String[]{name, address});
                    sort();
                } else {
                    System.out.println("Telefonnummer bereits vergeben.");
                }
            } else {
                System.out.println("Die Telefonnummer enthält nicht zugelassene Zeichen");
            }
        }
        else {
            System.out.println("Die Telefonnummer ist zu kurz. Bitte geben Sie Leerzeichen und Vorwahl an.");
        }
    }
    public LinkedHashMap<String, String[]> getPhonebook() {
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
        sort();
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
        sort();
    }
    public boolean testStringDig (String a){
        char[] c= " +".toCharArray();
        for( int i = 0; i<a.length(); i++ )
            if(!Character.isDigit(a.charAt( i ))){
                if (a.charAt(i)==c[0]||a.charAt(i)==c[1]) {
                }
                else {
                    return false;
                }
            }
        return true;
    }
    public String[] separateString(String a){
        char[] c= " +".toCharArray();
        int index =0;
        boolean first = true;
        String[] strings = new String[3];
        for( int i = 0; i<a.length(); i++) {
            if (!Character.isDigit(a.charAt(i))) {
                if (a.charAt(i) == c[0]) {
                    index++;
                    first=true;
                }
            }
            else {
                if (first){
                    strings[index] = String.valueOf(a.charAt(i));
                    first=false;
                }
                else {
                    strings[index] = strings[index] + a.charAt(i);
                }
            }

        }
        return strings;
    }
    public void save(){
        try(FileWriter fw= new FileWriter(new File("Phonebook.csv"))){
            BufferedWriter bw = new BufferedWriter(fw);
            for (String key: phonebook.keySet()) {
                String[] s = phonebook.get(key);
                String[] keys = separateString(key);
                bw.write(keys[0] + ";" + keys[1] + ";" + keys[2] + ";" + s[0] + ";" + s[1] + ";\n;");
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
