package Threads;


public class MultithreadingDemo {

    // Shared resource used by both worker threads
    static class OrderCounter {
        private int count = 0;

        // Only one thread at a time can execute this method
        public synchronized void increment(String workerName, int orderId) {
            int before = count;                  // BREAKPOINT 1
            count = count + 1;                   // BREAKPOINT 2

            System.out.println(
                    workerName + " processed order " + orderId
                            + " | Count: " + before + " -> " + count
                            + " | Thread: " + Thread.currentThread().getName()
            );
        }

        public synchronized int getCount() {
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("1. Main thread started"); // BREAKPOINT 3

        OrderCounter counter = new OrderCounter();

        Runnable orderTask = () -> {
            String workerName = Thread.currentThread().getName();

            for (int i = 1; i <= 3; i++) {
                System.out.println(workerName + " is preparing order " + i);

                counter.increment(workerName, i);    // BREAKPOINT 4

                try {
                    Thread.sleep(500); // Simulate waiting for work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            System.out.println(workerName + " finished processing");
        };

        Thread worker1 = new Thread(orderTask, "Worker-1");
        Thread worker2 = new Thread(orderTask, "Worker-2");

        System.out.println("2. Starting workers");

        worker1.run();                          // BREAKPOINT 5
        worker2.run();                          // BREAKPOINT 6

        System.out.println("3. Main thread is waiting");

        worker1.join();                           // BREAKPOINT 7
        worker2.join();

        System.out.println("4. All workers finished");
        System.out.println("Total orders processed: " + counter.getCount());
        System.out.println("Main thread finished");
    }
}