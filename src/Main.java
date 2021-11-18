import functions.Functions;
import functions.basic.Log;
import threads.*;

public class Main {
    private static final int count = 100;

    public static void main(String[] args) {
        complicatedThreads();
    }

    public static void nonThread() {
        Task task = new Task(count);
        double base;
        for (int i = 0; i < count; i++) {
            base = Math.random() * 10;
            task.setFunction(new Log(base));
            task.setLeftX(Math.random() * 100);
            task.setRightX(100 + Math.random() * 100);
            task.setStep(Math.random());
            System.out.println("nonThread" + i + " Source " + task.getLeftX() + " " + task.getRightX() + " " + task.getStep());
            double integral = Functions.integral(task.getFunction(), task.getLeftX(), task.getRightX(), task.getStep());
            System.out.println("nonThread" + i + " Result " + task.getLeftX() + " " + task.getRightX() + " " + task.getStep() + ": " + integral);
        }
    }

    public static void simpleThreads() {
        Task task = new Task(count);
        SimpleGenerator simpleGenerator = new SimpleGenerator(task);
        SimpleIntegrator simpleIntegrator = new SimpleIntegrator(task);
        Thread generator = new Thread(simpleGenerator);
        Thread integrator = new Thread(simpleIntegrator);
        generator.start();
        integrator.start();
    }

    public static void complicatedThreads() {
        Task task = new Task(count);
        MySemaphore mySemaphore = new MySemaphore();
        Generator generator = new Generator(task, mySemaphore);
        Integrator integrator = new Integrator(task, mySemaphore);
        generator.start();
        integrator.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        generator.interrupt();
        integrator.interrupt();
    }
}