package threads;

import functions.Function;
import functions.Functions;

public class SimpleIntegrator implements Runnable {
    private final Task task;

    public SimpleIntegrator(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        try {
            Function fun;
            double left;
            double right;
            double step;
            for (int i = 0; i < task.getOperations(); i++) {
                Thread.currentThread().sleep(2);
                synchronized (task) {
                    fun = task.getFunction();
                    left = task.getLeftX();
                    right = task.getRightX();
                    step = task.getStep();
                }
                double integral = Functions.integral(fun, left, right, step);
                System.out.println("simple" + i + " Result " + task.getLeftX() + " " + task.getRightX() + " " + task.getStep() + ": " + integral);
            }
        } catch (InterruptedException e) {
            System.out.println("Integrator interrupted");
        }
    }
}
