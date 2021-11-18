package threads;

import functions.basic.Log;

public class SimpleGenerator implements Runnable {
    private final Task task;
    public SimpleGenerator(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < task.getOperations(); i++) {
            double base = Math.random() * 10;
            synchronized (task) {
                task.setFunction(new Log(base));
                task.setLeftX(Math.random() * 100);
                task.setRightX(100 + Math.random() * 100);
                task.setStep(Math.random());
            }
            System.out.println("simple" + i + " Source " + task.getLeftX() + " " + task.getRightX() + " " + task.getStep());
            Thread.sleep(2);
            }
        } catch (InterruptedException e) {
            System.out.println("Generator interrupted");
        }
    }
}
