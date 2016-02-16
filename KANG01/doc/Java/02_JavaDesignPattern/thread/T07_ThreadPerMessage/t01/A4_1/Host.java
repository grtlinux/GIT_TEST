package kiea.priv.zzz.book.JavaDesignPattern.thread.T07_ThreadPerMessage.t01.A4_1;

public class Host {
    private Helper helper = new Helper();
    public void request(int count, char c) {
        System.out.println("    request(" + count + ", " + c + ") BEGIN");
        new HelperThread(helper, count, c).start();
        System.out.println("    request(" + count + ", " + c + ") END");
    }
}
