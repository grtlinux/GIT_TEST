package kiea.priv.zzz.book.JavaDesignPattern.thread.T00_Introduction1.t01.Others.Printer;

public class Main {
    public static void main(String[] args) {
        new Thread(new Printer("Good!")).start();
        new Thread(new Printer("Nice!")).start();
    }
}
