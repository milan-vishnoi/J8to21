
import java.io.Console;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

public class InputOutputExamples {

    public static void main(String[] args) {

        // Binary Data reading and writing
        String inputFile = "src\\resources\\inputBin.txt";
        String outputFile = "src\\resources\\outputBin.txt";
        try (InputStream in = new FileInputStream(inputFile); OutputStream out = new FileOutputStream(outputFile)) {
            byte[] buffer = new byte[2152];
            int length = 0;
            System.out.println("Size of file(characters):" + in.available());
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 20, length);
            }
        } catch (IOException ex) {
            System.out.println("Exception occured:" + ex.getMessage());
        }

        // Charater data reading and writing
        Charset charset = Charset.forName("UTF-8");

        // To print the code and write it to the outputChar file, uncomment below
        //inputFile = "src\\InputOutputExamples.java";
        inputFile = "src\\resources\\inputChar.txt";
        outputFile = "src\\resources\\outputChar.txt";
        try (Reader in = new FileReader(inputFile, charset); Writer out = new FileWriter(outputFile)) {
            char[] buffer = new char[1024];
            int length = 0;
            System.out.println("Writing into output file:");
            while ((length = in.read(buffer)) != - 1) {
                String text = String.valueOf(buffer);
                text = text.replace("input file", "output file").replace("reading", "writing");
                System.out.print(text);
                out.write(text, 0, length);

            }
        } catch (IOException ex) {
            System.out.println("Exception occured:" + ex.getMessage());
        }

        //Console
        Console c = System.console();
        if (c == null) {
            System.out.println("Console is not supported");
            return;
        }

        PrintWriter out = c.writer();
        out.println("To quite type: exit");
        out.println("Type value and press enter:");
        String txt = null;
        while (!(txt = c.readLine()).equals("exit")) {
            out.println("Echo: " + txt);

        }
    }

}
