
import java.util.Scanner;

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
        int op = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Here are the options:");
        String options = """
        1. Using Class with Runnable Interface
        2. By Extending Thread Class
        3. Using Lambda Expression
        4. Wait Example
        5. Daemon & Non-Daemon Threads
        6. Join
        """;
        System.out.println(options);
        System.out.print("Choose your option:");

        op = sc.nextInt();
        Thread thread = null;
        Thread threadMonitor = new Thread() {

            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Monitor thread: Thread Count=" + Thread.activeCount());
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.println("Some exception Occured:" + e);
                    }
                }
            }

        };
        threadMonitor.setDaemon(true);
        threadMonitor.start();
        switch (op) {
            case 1 -> {
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
            }

            case 2 -> {
                System.out.println("-----\nBy Extending Thread Class\n-----");
                Thread t3 = new ChildThreadClass();
                t3.start();
            }

            case 3 -> {
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

                Thread t4 = new Thread(r, "Lambda-Thread-1");
                t4.start();
                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                    System.out.println("Exception:" + e);
                }
                t4.interrupt();
            }

            case 4 -> {
                System.out.println("-----\nWait Example\n-----");
                Object obj = new Object();
                Runnable r2 = () -> {
                    Thread ct = Thread.currentThread();
                    System.out.println("Within the thread " + ct);
                    try {
                        System.out.println("Before the waiting, thread state:" + ct.getState());
                        synchronized (obj) {
                            obj.wait();
                            System.out.println("Wait over, state:" + ct.getState());
                        }
                    } catch (Exception e) {
                        System.out.println("Exception:" + e);
                    }
                };

                Thread t5 = new Thread(r2, "Wait-Example-Thread");
                t5.start();
                //Added below delay to see the waiting state
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Exception:" + e);
                }
                System.out.println("After wait, state:" + t5.getState());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Exception:" + e);
                }
                synchronized (obj) {
                    obj.notifyAll();
                }
                System.out.println("Thread notified, state:" + t5.getState());
            }

            case 5 -> {
                System.out.println("-----\nDaemon & Non-Daemon(User) Threads\n-----");
                Runnable r1 = () -> {
                    Thread currentThread = Thread.currentThread();
                    System.out.println("Start of %s thread %s".formatted(currentThread.isDaemon() ? "Daemon" : "Non-Daemon(user)", currentThread));
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        System.out.println("Some exception occured:" + e);
                    }
                    System.out.println("Exiting %s thread %s".formatted(currentThread.isDaemon() ? "Daemon" : "Non-Daemon(user)", currentThread));
                };

                Thread daemonThread = new Thread(r1, "Daemon Thread");
                daemonThread.setDaemon(true);
                Thread nondaemonThread = new Thread(r1, "Non-Daemon(user) Thread");

                nondaemonThread.start();
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println("Some exception occurred:" + e);
                }
                daemonThread.start();
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println("Some exception occurred:" + e);
                }

            }

            case 6 -> {
                System.out.println("-----\nJoin(the main thread will wait)\n-----");
                Runnable r1 = () -> {
                    Thread currentThread = Thread.currentThread();
                    System.out.println("Start of %s thread %s".formatted(currentThread.isDaemon() ? "Daemon" : "Non-Daemon(user)", currentThread));
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        System.out.println("Some exception occured:" + e);
                    }
                    System.out.println("Exiting %s thread %s".formatted(currentThread.isDaemon() ? "Daemon" : "Non-Daemon(user)", currentThread));
                };

                thread = new Thread(r1);
                thread.start();

            }

            default ->
                System.out.println("Select correct option, current thread count:" + Thread.activeCount());

        }

        if (thread != null) {
            try {
                System.out.println("Main thread is waiting for thread %s to finish".formatted(thread));
                thread.join();
            } catch (Exception e) {
                System.out.println("Some exception occured:" + e);
            }

        }

        System.out.println("Exiting main thread");

    }

}
