
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class FileSystemExamples {

    public static void main(String[] args) {
        FileSystem fs = FileSystems.getDefault();
        System.out.println("-----\nFileSystem\n-----");
        fs.getFileStores().forEach(s -> System.out.println(s.type() + ' ' + s.name()));
        fs.getRootDirectories().forEach(p -> System.out.println(p));
        String separator = fs.getSeparator();
        System.out.println("Separator:" + separator);
        System.out.println("");

        System.out.println("-----\nPath\n-----");
        Path current = Path.of("").toAbsolutePath();
        System.out.println("Current Directory:" + current);
        System.out.println("");

        Path someFile = Path.of("C:\\", "Users", "Public", "Documents", "someFile.txt");
        System.out.println("Somefile path:" + someFile);
        Path docsFolder = someFile.getParent();
        System.out.println("Somefile Parent folder:" + docsFolder);
        System.out.println("");
        Path otherFile = someFile.resolveSibling("otherFile.txt");
        System.out.println("Sibling file:" + otherFile);
        System.out.println("Sibling Parent:" + otherFile.getParent());
        System.out.println("");

        Path thirdFile = docsFolder.resolve("../Downloads/thirdFile.txt");
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

        System.out.println("-----\nFiles\n-----");
        try {
            Path someFileSym = Path.of("C:\\", "Users", "Public", "Downloads", "sf.txt");
            System.out.println("Symblink path:" + someFileSym);
            if (Files.isSymbolicLink(someFileSym)) {

                Path symblink = Files.readSymbolicLink(someFileSym);
                System.out.println("Symbolic link of:" + symblink);
            } else {
                //Need to run as admin(or have permission) to execute below code to create symbolic link
                Files.createSymbolicLink(someFileSym, someFile);
                System.out.println("Symbolic link for " + someFile + " created as " + someFileSym);
            }

            System.out.println("");
            System.out.println("Content type of Somefile:" + Files.probeContentType(someFile));
            System.out.println("Some is readable?" + Files.isReadable(someFile));
            System.out.println("Some is writeable?" + Files.isWritable(someFile));

            BasicFileAttributes fa = Files.readAttributes(someFile, BasicFileAttributes.class);
            long size = fa.size();
            System.out.println("Size:" + fa.size() + " characters");
            FileTime createdTime = fa.creationTime();
            FileTime lastModifiedTime = fa.lastModifiedTime();
            // System.out.println("Some file-\nCreated at %s\nLast Modified at %s".formatted(createdTime, lastModifiedTime));
            System.out.println("Writing the Created and Modified time into the file");
            Files.writeString(someFile, "Some file-\nCreated at %s\nLast Modified at %s".formatted(createdTime, lastModifiedTime));
            System.out.println("Wrote the data into the file, reading below:");
            Files.lines(someFile, Charset.forName("UTF-8")).forEach(System.out::println);

            System.out.println("\nCreating a temp dir and temp file:");
            Path tempDir = Files.createTempDirectory("TEMP");
            Path tempFile = Files.createTempFile(tempDir, "TEMP", ".tmp");
            System.out.println("Temp:" + tempFile);
            Files.deleteIfExists(tempFile);
            System.out.println("Deleted the temp file");
            Files.deleteIfExists(tempDir);
            System.out.println("Deleted the temp dir");

            System.out.println("");
            System.out.println("Listing the contents of %s:".formatted(docsFolder));
            Files.list(docsFolder).forEach(System.out::println);
            System.out.println("");
            System.out.println("Walking down %s:".formatted(docsFolder));
            Files.walk(docsFolder).map(p -> p.toString()).forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Some error occurred:" + e);
        }

    }

}
