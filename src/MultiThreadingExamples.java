
class ThreadedClass implements Runnable {

    static int count = 0;

    @Override
    public void run() {
        count++;
        System.out.println("This is thread " + count);
    }

}

public class MultiThreadingExamples {

    public static void main(String[] args) {
        System.out.println("This is main of MultiThreadingExamples");
        ThreadedClass td = new ThreadedClass();
        Thread t1 = new Thread(td);
        Thread t2 = new Thread(td);
        System.out.println("Name   State isAlive toString             Priority");
        System.out.println(t1.getName() + " " + t1.getState() + " " + t1.isAlive() + " " + t1.toString() + " " + t1.getPriority());
        System.out.println(t2.getName() + " " + t2.getState() + " " + t2.isAlive() + " " + t2.toString() + " " + t2.getPriority());
        t1.start();
        t2.start();
        System.out.println("Is Alive:" + t1.isAlive() + " " + t2.isAlive());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception Occured:" + e);
        }

        System.out.println("State of Thread:" + t1.getState() + " " + t2.getState());

    }

}
