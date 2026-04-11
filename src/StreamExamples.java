
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StreamExamples {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7);
        List<Integer> outputList;
        System.out.println("Choose the test case:");
        System.out.println("1.Vertical Processing\n2.Lazy Processing\n3.filter-map-forEach");
        System.out.println("4.filter-map-collect\n5.filter-map-reduce\n6.filter-map-count");
        System.out.println("7.Any Match\n8.All Match\n9.Map vs FlatMap");
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        sc.close();

        switch (testCase) {
            case 1 -> {
                System.out.println("Vertical Processing:");
                List.of(1, 2, 3, 4).stream()
                        .filter(x -> {
                            System.out.println("filter:" + x);
                            return x % 2 == 0;
                        })
                        .map(x -> {
                            System.out.println(x + " passed filter, map:" + x);
                            return x * 2;
                        })
                        .forEach(x -> {
                            System.out.println("output:" + x);
                        });
            }

            case 2 -> {
                System.out.println("-----\nLazy Processing:");
                List.of(1, 2, 3, 4).stream()
                        .filter(x -> {
                            System.out.println("filter:" + x);
                            return x % 2 == 0;
                        })
                        .map(x -> {
                            System.out.println(x + " passed filter, map:" + x);
                            return x * 2;
                        });

            }

            case 3 -> {
                System.out.println("------\nfilter map forEach:");
                list.stream().filter(x -> x % 2 == 0).map(x -> x * 2).forEach(System.out::println);
            }

            case 4 -> {
                System.out.println("-----\nFilter Map Collect:" + list);
                outputList = list.stream().filter(x -> x > 2).map(x -> x / 2).peek(System.out::println).collect(Collectors.toList());
                System.out.println("Output list: " + outputList);
            }

            case 5 -> {
                System.out.println("-----\nfilter map reduce:");
                int result = list.stream().filter(x -> x > 2).map(x -> x / 2).reduce(0, (a, b) -> {
                    System.out.println("A:" + a + " B:" + b);
                    return a + b;
                });
                System.out.println("Output list Sum: " + result);
            }

            case 6 -> {
                System.out.println("-----\nfilter map count");
                long count = list.stream().filter(x -> x > 2).map(x -> x / 2).count();
                System.out.println("Output list Sum: " + count);
            }

            case 7 -> {
                System.out.println("-----\nAny Matched?");
                boolean anyMatched = list.stream().filter(x -> x % 2 == 0).anyMatch(x -> {
                    System.out.println("Match: " + x);
                    return x > 2;
                });
                System.out.println(anyMatched);
            }

            case 8 -> {
                System.out.println("-----\nAll Matched?");
                boolean b = list.stream().filter(x -> x % 2 == 0).map(x -> x * 1.1).allMatch(x -> x > 3);
                System.out.println(b);
            }

            case 9 -> {
                System.out.println("-----\nMap vs FlatMap");
                List<List<Integer>> nestedList = List.of(List.of(1, 2), List.of(3, 4), List.of(5, 6));
                nestedList.stream().map(x -> {
                    System.out.println("Map:" + x);
                    return x;
                }).forEach(element -> System.out.println("Element:" + element));
                nestedList.stream().flatMap(x -> {
                    System.out.println("Map:" + x);
                    return x.stream();
                }).forEach(element -> System.out.println("Element:" + element));
            }

            default -> {
                System.out.print("You have selected a wrong case");
            }
        };

    }

}
