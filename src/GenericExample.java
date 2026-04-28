// Generics were introduced in Java 5

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
        obj = new GenericExample<Number>(10.2);
        GenericExample<String> obj1 = new GenericExample<>("Name");

    }

}
