package pl.swieczkowski.serializacja;

import java.io.*;
import java.util.Calendar;

public class Human implements Serializable {
    public static long serialVersionUID = 1L;
    private String name;
    private transient int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getYearOfBirth() {
        return Calendar.getInstance().get(Calendar.YEAR) - age;
    }

    public Human(String name, int age) {
        setName(name);
        setAge(age);
    }

    public static void main(String[] args) {
        Human human = new Human("Tymon", 7);
        human.writeObject(human);
        System.out.println("Serialized object: " + human.getName() + ", " + human.getAge());
        human.readObject(human);
        System.out.println("Deserialized object: " + human.getName() + ", " + human.getAge());
    }

    public void writeObject(Object object) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("object.bin"))) {
            outputStream.writeObject(object);
            outputStream.writeInt(getYearOfBirth());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readObject(Object object) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("object.bin"))) {
            object = (Object) inputStream.readObject();
            int yearOfBirth = inputStream.readInt();
            setAge(Calendar.getInstance().get(Calendar.YEAR) - yearOfBirth);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
