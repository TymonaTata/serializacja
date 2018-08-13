package pl.swieczkowski.serializacja;

import PetleIInstrukcjeWarunkowe.Array;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NameList implements Serializable {
    public final static String EXIT = "-";
    public static long serialVersionUID = 1L;


    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        getNamesToList(names);
        writeNameListToFile(names);
        System.out.println(readNameListFromFile());

    }

    public static void getNamesToList(List<String> nameList) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Podaj imię które chcesz zapisać w pliku lub wprowadź \"-\" jeżeli chesz zakończyć dodawanie imion.");
            String name = sc.nextLine();
            if (name.equals(EXIT)) {
                break;
            } else {
                nameList.add(name);
            }
        }
    }

    public static void writeNameListToFile(List<String> nameList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("nameList.bin"))) {
            outputStream.writeObject(nameList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<String> readNameListFromFile() {
        List<String> nameList = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("nameList.bin"))) {
            nameList = (ArrayList<String>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return nameList;
    }
}

