package kiea.priv.zzz.book.JavaDesignPattern.thread.T07_ThreadPerMessage.t01.Q5;

public class Service {
    public static void service() {
        System.out.print("service");
        for (int i = 0; i < 50; i++) {
            System.out.print(".");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("done.");
    }
}