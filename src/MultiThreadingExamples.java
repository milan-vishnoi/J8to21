
class ThreadedClass implements Runnable {

    static int count = 0;

    @Override
    public void run() {
        count++;
        System.out.println("This is thread " + count);
    }

}

class ChildThreadClass extends Thread {

    Thread currentThread = Thread.currentThread();

    //Below method is optional to override
    @Override
    public void run() {
        currentThread = Thread.currentThread();
        System.out.println("Within run of " + this.getClass());
        System.out.println(toString());
    }

    @Override
    public String toString() {
        String details = """
                        Thread Metadata: %s 
                        Thread State: %s
                        Thread isAlive: %s
                        Priority: %s
                         """
                .formatted(super.toString(), currentThread.getState(), currentThread.isAlive(), currentThread.getPriority());

        return details;

    }

}

public class MultiThreadingExamples {

    public static void main(String[] args) {
        System.out.println("-----\nUsing Class with Runnable Interface\n-----");
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

        System.out.println("-----\nBy Extending Thread Class\n-----");
        Thread t3 = new ChildThreadClass();
        t3.start();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Exception Occured:" + e);
        }

        System.out.println("-----\nUsing Lambda Expression\n-----");
        Runnable r = () -> {
            System.out.println("Within the Lambda Expression");
            Thread currentThread = Thread.currentThread();
            System.out.println("Current Thread:" + currentThread);
            int count = 0;
            while (!currentThread.isInterrupted()) {
                System.out.println("Ping " + ++count + "... The thread is not interrupted");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Exception:" + e);
                    System.out.println("Thread Interrupted. Closing.....");
                    return;
                }
            }

        };

        Thread t4 = new Thread(r);
        t4.setPriority(8);
        t4.start();
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
        t4.interrupt();

    }

}
