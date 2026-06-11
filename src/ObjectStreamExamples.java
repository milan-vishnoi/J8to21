
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectStreamExamples implements Serializable {

    String type;
    int number;

    public ObjectStreamExamples(int number, String type) {
        this.number = number;
        this.type = type;
    }

    public static void main(String[] args) {
        ObjectStreamExamples obj = new ObjectStreamExamples(1, "Input Stream");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src\\resources\\serializedFile.txt"))) {
            out.writeObject(obj);
            out.writeObject("\nEOF"); // String is an object and is Serializable
            out.writeObject(1); // Will treated as Object of Integer which is Number which is Serializable
            System.out.println("Successfully write the data into the file");
        } catch (IOException ex) {
            System.out.println("Some issue occured:" + ex);
        }
    }

}
