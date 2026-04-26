
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@FunctionalInterface
interface SampleFunctionalInterface {

    void run();

    // default methods in interface were introduced Java 8
    default void start() {
        printHello();
        System.out.println("This is default start function");
    }

    // static methods in interface were introduced Java 8
    static void utility() {
        System.out.println("This is static utility function");
    }

    // private methods in interface were introduced Java 9
    private void printHello() {
        System.out.println("Hellos from private method.");

    }

}

public class FunctionalInterfaceExample {

    public static void main(String args[]) {

        System.out.println("-----\nFunctional Interface");
        SampleFunctionalInterface sfi = () -> System.out.println("This is run");
        sfi.run();
        sfi.start();
        SampleFunctionalInterface.utility();
        // FunctionalInterfaceExample.utility(); Not allowed
        // sfi.utility(); Not allowed

        System.out.println("-----\nBuilt in Functional Interfaces");
        Predicate<Integer> checkEven = x -> x % 2 == 0;
        System.out.println("Predicate check even: " + checkEven.test(6)); // boolean test(T t)
        Function<Integer, String> checkEvenFunction = x -> {
            if (x % 2 == 0) {
                return "This is even";
            } else {
                return "This is odd";
            }
        };
        System.out.println("Function check even: " + checkEvenFunction.apply(11)); // R apply(T t)

        Consumer<Integer> displaySquare = x -> System.out.println("Consumer display Square of " + x + ": " + x * x);
        displaySquare.accept(4);  // void accept(T t)

        Supplier<String> supply = () -> "This is supplier";
        System.out.println("Supplied: " + supply.get()); // T get()

    }

}
