
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExamples {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7);
        List<Integer> outputList;
        System.out.println("Choose the test case:");
        System.out.println("1.Vertical Processing\n2.Lazy Processing\n3.filter-map-forEach");
        System.out.println("4.filter-map-collect\n5.filter-map-reduce\n6.filter-map-count");
        System.out.println("7.Any Match\n8.All Match\n9.Map vs FlatMap");
        System.out.println("10.Stream Generation\n11.Parallel Stream\n12.Spliterator");
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

            case 10 -> {
                System.out.println("-----\nStream generation");
                System.out.println("IntStream generate:");
                int sumRandom = IntStream.generate(() -> (int) (Math.random() * 10))
                        .peek(x -> System.out.println("Generated Number:" + x))
                        .takeWhile(n -> n != 3).sum();
                System.out.println("Random sum:" + sumRandom);
                System.out.print("Stream.of:");
                int product = Stream.of(1, 2, 3, 4).peek(x -> System.out.print(" " + x)).reduce(1, (a, b) -> a * b);
                System.out.println("\nProduct using Stream:" + product);
                System.out.print("Arrays Stream:");
                int[] arr = {2, 3, 4, 5, 6};
                long countEven = Arrays.stream(arr).peek(x -> System.out.print(" " + x)).filter(x -> x % 2 == 0).count();
                System.out.println("\nCount even:" + countEven);

            }

            case 11 -> {

                list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                System.out.println("-----\nParallel Stream:");
                System.out.print("Without parallel():");
                list.stream().forEach(x -> System.out.print(" " + x));
                System.out.print("\nWith parallel():");
                list.stream().parallel().forEach(x -> System.out.print(" " + x));

            }

            case 12 -> {
                System.out.println("-----\nSpliterator:");
                Spliterator<Integer> s = new Random().ints(10, 0, 10).spliterator();
                String characteristics
                        = "Concurrent " + s.hasCharacteristics(Spliterator.CONCURRENT) + "\n"
                        + "Distinct " + s.hasCharacteristics(Spliterator.DISTINCT) + "\n"
                        + "Immutable " + s.hasCharacteristics(Spliterator.IMMUTABLE) + "\n"
                        + "NonNull " + s.hasCharacteristics(Spliterator.NONNULL) + "\n"
                        + "Ordered " + s.hasCharacteristics(Spliterator.ORDERED) + "\n"
                        + "Sized " + s.hasCharacteristics(Spliterator.SIZED) + "\n"
                        + "Sorted " + s.hasCharacteristics(Spliterator.SORTED) + "\n"
                        + "Subsized " + s.hasCharacteristics(Spliterator.SUBSIZED);
                System.out.println(characteristics);
                System.out.println("Size " + s.getExactSizeIfKnown());
                System.out.println("Estimate Size " + s.estimateSize());
                System.out.print("Try Advance of Spliterator:");
                s.tryAdvance(System.out::println);
            }

            default -> {
                System.out.print("You have selected a wrong case");
            }
        };

    }

}
