public class RaceFixed {

    static int count = 0;

    // 唯一的变化：加了 synchronized
    static synchronized void increase() {
        count++;
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) increase();
        }, "线程A");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) increase();
        }, "线程B");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("预期 200000，实际 = " + count);
    }
}