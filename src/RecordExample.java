//record was introduced in Java 16

public record RecordExample(String name, int age) {

    static int rank;

    public RecordExample {
        rank = 1;
        System.out.println("This is compact constructor");

    }

    public RecordExample() {
        this("No name", 0); //Mandatory to call within a non-compact constructor
        System.out.println("No args constructor called");
    }

    public static void main(String[] args) {
        RecordExample recordObj;

        System.out.println("Creating record with values");
        recordObj = new RecordExample("Milan", 25);
        System.out.println("Name:%s Age:%d".formatted(recordObj.name(), recordObj.age()));
        System.out.println("Using the defualt toString of record: " + recordObj);
        System.out.println("Equals method:" + recordObj.equals(new RecordExample("Milan", 25)));

        System.out.println("\nCreating record without values(no args constructor)");
        recordObj = new RecordExample();
        if (recordObj instanceof RecordExample(String name, int age)) { // Pattern matching (available since Java 21)
            System.out.println("Name:%s Age:%d".formatted(name, age));
            System.out.println("Using the defualt toString of record: " + recordObj);

        }

    }

}
