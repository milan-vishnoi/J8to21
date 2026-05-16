
public class NestedClassExamples {

    private static class InnerClass1 {

        InnerClass1() {
            System.out.println("Inner Static Class Constructor called");
        }

        @Override
        public String toString() {
            // printClass(); Can't class because printClass is non-static
            return "toString of " + this.getClass() + " called";
        }
    }

    private class InnerClass2 {

        public InnerClass2() {
            System.out.println("Inner Instance Member class Constructor called from " + this.getClass());
        }

        @Override
        public String toString() {
            printClass();
            return "toString of " + this.getClass() + " called";
        }

        public void printInner() {
            System.out.println("Called printInner() for Instance Member class");
        }

    }

    public static void main(String[] args) {
        NestedClassExamples.InnerClass1 staticObj = new NestedClassExamples.InnerClass1();

        System.out.println(staticObj);
        System.out.println("-----------");

        NestedClassExamples nestedClassObj = new NestedClassExamples();
        NestedClassExamples.InnerClass2 instanceObj = nestedClassObj.new InnerClass2();
        System.out.println(instanceObj);
        instanceObj.printInner();
        System.out.println("-----------");

        NestedClassExamples.InnerClass2 anonClass = new NestedClassExamples().new InnerClass2() {
            @Override
            public void printInner() {
                System.out.println("Called printInner() for Instance Member Anonymous class");
            }

        };
        System.out.println(anonClass);
        anonClass.printInner();

    }

    public void printClass() {
        System.out.println("We are in printClass of OuterClass:" + this.getClass());
    }

}
