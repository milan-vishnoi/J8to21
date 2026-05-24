
import java.util.*;

public class CollectionExamples {

    public static void main(String[] args) {
        List<Integer> list;
        Set<String> set;
        Deque<Integer> deque;
        Map<Integer, String> map;
        Integer[] arr = {1, 2, 3};
        list = Arrays.asList(arr);
        System.out.println("----\nList\n----");
        System.out.println("Fixed size list:" + list);
        // Can change the element
        list.set(1, 4);
        System.out.println("Fixed size list update:" + list);
        Collections.sort(list);
        System.out.println("Fixed size list sorted:" + list);
        // list.add(5); Giving the UnsupportedOperationException because the list is fixed size
        list = List.of(3, 4, 5, 6);
        //list.set(2, 3); Giving the UnsupportedOperationException becasuse list is Read only
        System.out.print("Read only list:" + list);

        System.out.print("Printing using iterator:");
        Iterator<Integer> listIterator = list.iterator();
        while (listIterator.hasNext()) {
            System.out.print(" " + listIterator.next());
            if (!listIterator.hasNext()) {
                System.out.println("");
            }
        }

        System.out.print("Printing using for each loop:");
        for (int element : list) {
            System.out.print(" " + element);
            if (list.lastIndexOf(element) == list.size() - 1) {
                System.out.println("");
            }
        }

        System.out.print("Printing using forEach method:");
        //Added in Java 8
        list.forEach(x -> System.out.print(" " + x));
        System.out.println("");

        System.out.println("----\nSet\n----");
        set = new HashSet<>();
        System.out.printf("Adding one:%b Set:%s\n", set.add("One"), set);
        System.out.printf("Adding two:%b Set:%s\n", set.add("Two"), set);
        System.out.printf("Adding One again:%b Set:%s\n", set.add("One"), set);

        System.out.println("----\nDeque\n----");
        deque = new ArrayDeque<>();
        System.out.printf("Add first:%b Deque:%s Peek:%s", deque.offerFirst(1), deque, deque.peek());
        System.out.printf("\nAdd last:%b Deque:%s Peek:%s Peek First:%s Peek Last:%s", deque.offerLast(10), deque, deque.peek(), deque.peekFirst(), deque.peekLast());
        System.out.printf("\nRemove last:%s Deque:%s Peek:%s", deque.pollLast(), deque, deque.peek());

        System.out.println("\n----\nMap\n----");
        map = new HashMap<>();
        System.out.printf("Add 1:%s Map:%s", map.put(1, "One"), map);
        System.out.printf("\nAdd 2:%s Map:%s", map.put(2, "two"), map);
        System.out.printf("\nUpdate 1(ek):%s Map:%s", map.put(1, "Ek"), map);
        System.out.printf("\nPut if absent 2(do):%s Map:%s", map.putIfAbsent(2, "do"), map);
        System.out.printf("\nRemove using key value{2,\"do\"}, removed:%s Map:%s", map.remove(2, "do"), map);
        System.out.printf("\nRemove 2:%s Map:%s", map.remove(2), map);
        System.err.printf("\nKey Set:%s Value:%s", map.keySet(), map.values());
        System.out.println("\n-----------------------");

        set.add("Four");
        set.add("Three");
        System.out.println("Normal set:" + set);
        SequencedSet<String> sequencedSet = new LinkedHashSet<>(); // HashSet doesn't implement SequencedSet
        sequencedSet.add("One");
        sequencedSet.add("Two");
        sequencedSet.add("Three");
        sequencedSet.add("Four");
        System.out.println("Sequenced Set:" + sequencedSet);
        System.out.println("Reversed Sequenced Set:" + sequencedSet.reversed());
        sequencedSet.addFirst("Zero");
        System.out.println("Squenced Set after zero addition:" + sequencedSet);

    }

}
