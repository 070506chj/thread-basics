public class LockScopeDemo {

    static int count = 0;

    static class Locker {
        synchronized void increase() {
            count++;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== 情况1：两个线程用【同一个】 Locker 对象 ===");
        runCase(true);

        Thread.sleep(200);
        count = 0;

        System.out.println();
        System.out.println("=== 情况2：两个线程各用【不同的】 Locker 对象 ===");
        runCase(false);
    }

    static void runCase(boolean shareLock) throws Exception {
        Locker l1 = new Locker();
        Locker l2 = shareLock ? l1 : new Locker();   // 关键差异

        Thread t1 = new Thread(() -> { for (int i = 0; i < 100_000; i++) l1.increase(); });
        Thread t2 = new Thread(() -> { for (int i = 0; i < 100_000; i++) l2.increase(); });
        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("预期 200000，实际 = " + count);
    }
}