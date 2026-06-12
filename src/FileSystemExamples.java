
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileSystemExamples {

    public static void main(String[] args) {
        FileSystem fs = FileSystems.getDefault();
        fs.getFileStores().forEach(s -> System.out.println(s.type() + ' ' + s.name()));
        fs.getRootDirectories().forEach(p -> System.out.println(p));
        String separator = fs.getSeparator();
        System.out.println("Separator:" + separator);

        Path current = Path.of("").toAbsolutePath();
        System.out.println("Current:" + current);
        System.out.println("");

        Path someFile = Path.of("C:\\", "Users", "Public", "Public Documents", "someRandom.txt");
        System.out.println("Somefile path:" + someFile);
        Path docsFolder = someFile.getParent();
        System.out.println("Somefile Parent folder:" + docsFolder);
        System.out.println("");
        Path otherFile = someFile.resolveSibling("otherFile.txt");
        System.out.println("Sibling file:" + otherFile);
        System.out.println("Sibling Parent:" + otherFile.getParent());
        System.out.println("");

        Path thirdFile = docsFolder.resolve("../Public Downloads/thirdFile.txt");
        System.out.println("File in other folder:" + thirdFile);
        System.out.println("Other Parent folder:" + thirdFile.getParent());
        System.out.println("");

        Path normalized = thirdFile.normalize();
        System.out.println("Normalized:" + normalized);
        System.out.println("Normalized Parent:" + normalized.getParent());
        System.out.println("");

        Path relativePath = someFile.relativize(otherFile);
        System.out.println("Relative path of otherFile with someFile:" + relativePath);
        System.out.println("");

    }

}
