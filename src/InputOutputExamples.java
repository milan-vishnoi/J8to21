
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InputOutputExamples {

    public static void main(String[] args) {
        String inputFile = "src\\resources\\inputBin.txt";
        String outputFile = "src\\resources\\outputBin.txt";
        try (InputStream in = new FileInputStream(inputFile); OutputStream out = new FileOutputStream(outputFile)) {
            byte[] buffer = new byte[1024];
            int length;
            System.out.println("Size of file(characters):" + in.available());
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 20, length);
            }
        } catch (IOException ex) {
            System.out.println("Exception occured:" + ex.getMessage());
        }
    }

}
