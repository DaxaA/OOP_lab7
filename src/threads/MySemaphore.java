package threads;

public class MySemaphore {
    private volatile boolean can = true;

    public synchronized void beginW() throws InterruptedException {
        while(!can) {
            wait();
        }
    }
    public synchronized void endW() {
        can = false;
        notifyAll();
    }

    public synchronized void beginR() throws InterruptedException {
        while(can) {
            wait();
        }
    }
    public synchronized void endR() {
        can = true;
        notifyAll();
    }
}
