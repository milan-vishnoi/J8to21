
import java.util.List;
import java.util.Optional;

public class OptionalExamples {

    public static void main(String args[]) {
        Optional<String> opt1 = Optional.of("Java");
        Optional<String> opt2 = Optional.ofNullable(null);
        Optional<String> opt3 = Optional.empty();

        System.out.println("opt1- isEmpty():" + opt1.isEmpty() + " isPresent:" + opt1.isPresent() + " value:" + opt1.toString());
        System.out.println("opt2- isEmpty():" + opt2.isEmpty() + " isPresent:" + opt2.isPresent() + " value:" + opt2.toString());
        System.out.println("opt3- isEmpty():" + opt3.isEmpty() + " isPresent:" + opt3.isPresent() + " value:" + opt3.toString());

        System.out.println("-----\norElse and orElseGet");
        System.out.println("opt1- original value:" + opt1.get() + " orElse Value:" + opt1.orElse(getDefault()));
        System.out.println("opt1- original value:" + opt1.get() + " orElseGet Value:" + opt1.orElseGet(() -> getDefault()));
        System.out.println("opt2- original value:" + opt2.toString() + " orElse Value:" + opt2.orElse(getDefault()));
        System.out.println("opt3- original value:" + opt3.toString() + " orElseGet Value:" + opt3.orElseGet(() -> getDefault()));

        System.out.println("-----\nifPresent");
        opt1.ifPresent(x -> System.out.println("Opt1 is present, value:" + x));
        opt2.ifPresent(x -> System.out.println("Opt2 is present, value:" + x));
        opt3.ifPresent(x -> System.out.println("Opt3 is present, value:" + x));

        System.out.println("-----\nMap Length");
        Optional<String> opt4 = Optional.ofNullable("Java");
        Optional<Integer> len = opt4.map(x -> x.length());
        System.out.println(len.orElse(-1));

        System.out.println("-----\nFilter Length");
        Optional<String> filteredValue = opt4.filter(x -> x.length() == 4);
        System.out.println(filteredValue.orElse("No Strings Attached!"));

        System.out.println("-----\nFlatMap with Optional Length");
        Optional<Integer> flatMapLen = opt4.flatMap(x -> Optional.of(x.length()));
        System.out.println(flatMapLen.orElse(-1));

        System.out.println("-----\nStream + Optional");
        List<Optional<String>> optList = List.of(Optional.of("Java"), Optional.of("JavaScript"), Optional.empty());
        optList.stream().filter(x -> x.orElse("").length() > 4).forEach(System.out::println);
        optList.stream().flatMap(Optional::stream).forEach(System.out::println);
        optList.stream().flatMap(Optional::stream).filter(x -> x.length() > 4).forEach(System.out::println);

    }

    static String getDefault() {
        System.out.println("getDefault() called");
        return "default";
    }
}
