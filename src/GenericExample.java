// Generics were introduced in Java 5

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericExample<T> {

    T value;

    public GenericExample(T t) {
        System.out.println("Constructor is called with value:" + t);
        this.value = t;
        this.getType();
        System.out.println("Creating and object of " + t.getClass());
    }

    public void getType() {
        if (this.value instanceof Number) {
            System.out.println("This is a Number");
        } else if (this.value instanceof String) {
            System.out.println("This is some String");
        } else {
            System.out.println("Neither is that a Number nor is that a String");
        }
    }

    public static void main(String[] args) {
        GenericExample<Number> obj = new GenericExample<>(10);
        obj = new GenericExample<>(10.2);
        GenericExample<String> obj1 = new GenericExample<>("Name");

        //Advanced Generics
        System.out.println("-----\nAdvanced Genrics\n-----");

        List<? extends Number> readOnlyList;
        List<Integer> intList = Arrays.asList(10, 20, 30);
        List<Double> doubleList = Arrays.asList(10.1, 20.2, 30.3);

        readOnlyList = intList;
        System.out.println("Read only list assigned int list:" + readOnlyList);
        readOnlyList = doubleList;
        System.out.println("Read only list assigned double list:" + readOnlyList);

        //Below two line will give compiler error
        //readOnlyList.add(10);
        //readOnlyList.add(10.1);
        System.out.println("");
        List<? super Number> readWriteList = new ArrayList<>();

        //Below two lines will compiler error
        //readWriteList = intList;
        //readWriteList = doubleList;
        readWriteList.add(10);
        readWriteList.add(10.1);
        readWriteList.add(20);
        readWriteList.add(20.2);
        System.out.println("Read-Write Number list:" + readWriteList);

    }

}
