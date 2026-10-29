public class RaceDemo {

    static int count = 0;// 两个线程共享这个变量

    static synchronized void increase(){
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

        t1.join();     // 等 t1 执行完
        t2.join();     // 等 t2 执行完

        System.out.println("两个线程各加 10 万次，预期结果 = 200000");
        System.out.println("实际结果 = " + count);
    }
}