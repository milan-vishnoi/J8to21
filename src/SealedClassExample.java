
public sealed class SealedClassExample permits SubClass1, SubClass2 {

    public SealedClassExample() {
        System.out.println("This is constructor of SealedClass, called by object of:" + this.getClass().getName());

    }

    void print() {
        System.out.println("This is print method of SealedClass called by object of " + this.getClass().getName());
    }

    public static void main(String[] args) {

        SubClass1 sc1 = new SubClass1();
        SubClass2 sc2 = new SubClass2();
        sc1.print();
        sc1.printSubClass();
        sc2.print();
        sc2.printSubClass();

    }
}

final class SubClass1 extends SealedClassExample {

    public SubClass1() {
        System.out.println("Constructor of SubClass1. This is final class");
    }

    void printSubClass() {
        System.out.println("This is printSubClass of SubClass1");
    }

}

non-sealed class SubClass2 extends SealedClassExample {

    public SubClass2() {
        System.out.println("This is constructor of SubClass2. This is non-sealed class(open for extension by any class).");
    }

    void printSubClass() {
        System.out.println("This is printSubClass of SubClass2");
    }

}
