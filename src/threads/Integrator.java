package threads;

import functions.Functions;

public class Integrator extends Thread {
    private final Task task;
    private final MySemaphore mySemaphore;

    public Integrator(Task task, MySemaphore mySemaphore) {
        this.task = task;
        this.mySemaphore = mySemaphore;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < task.getOperations() && !isInterrupted(); i++) {
                mySemaphore.beginR();
                double integral = Functions.integral(task.getFunction(), task.getLeftX(), task.getRightX(), task.getStep());
                System.out.println("simple" + i + " Result " + task.getLeftX() + " " + task.getRightX() + " " + task.getStep() + ": " + integral);
                mySemaphore.endR();
            }
        } catch (InterruptedException IE) {
            System.out.println("Integrator interrupted");
        }
    }
}
