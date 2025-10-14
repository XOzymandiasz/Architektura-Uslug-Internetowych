package org.example;

import java.io.*;
import java.util.List;

public class BinarySerializer {

    public static <T extends Serializable> void save(List<T> objects, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(fileName)))) {
            oos.writeObject(objects);
            System.out.println("Saved to binary: " + fileName);
        } catch (IOException e) {
            System.err.println("Error while saving: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static <T extends Serializable> List<T> load(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(fileName)))) {
            Object object = ois.readObject();
            System.out.println("Loaded from binary: " + fileName);
            return (List<T>) object;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error while loading: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
