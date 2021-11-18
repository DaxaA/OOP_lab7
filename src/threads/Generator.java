package threads;

import functions.basic.Log;

public class Generator extends Thread {
    private final Task task;
    private final MySemaphore mySemaphore;

    public Generator(Task task, MySemaphore mySemaphore) {
        this.task = task;
        this.mySemaphore = mySemaphore;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < task.getOperations() && !isInterrupted(); i++) {
                double base = Math.random() * 10;
                mySemaphore.beginW();
                task.setFunction(new Log(base));
                task.setLeftX(Math.random() * 100);
                task.setRightX(100 + Math.random() * 100);
                task.setStep(Math.random());
                System.out.println("simple" + i + " Source " + task.getLeftX() + " " + task.getRightX() + " " + task.getStep());
                mySemaphore.endW();
            }
        } catch (InterruptedException IE) {
            System.out.println("Generator interrupted");
        }
    }
}
