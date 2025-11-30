import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        TaskQueue taskQueue = new TaskQueue();
        ExecutorService executor = Executors.newFixedThreadPool(4);

        try (FileWriter writer = new FileWriter("java_output.txt")) {

            // Create workers
            for (int i = 1; i <= 4; i++) {
                executor.submit(new Worker(i, taskQueue, writer));
            }

            // Add tasks
            for (int i = 1; i <= 20; i++) {
                taskQueue.addTask(new Task(i));
            }

            Thread.sleep(3000); // Allow time for processing
            executor.shutdownNow(); // Gracefully interrupt workers

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
