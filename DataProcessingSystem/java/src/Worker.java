import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class Worker implements Runnable {

    private final int workerId;
    private final TaskQueue taskQueue;
    private final Logger logger = Logger.getLogger(Worker.class.getName());
    private final FileWriter writer;

    public Worker(int workerId, TaskQueue queue, FileWriter writer) {
        this.workerId = workerId;
        this.taskQueue = queue;
        this.writer = writer;
    }

    @Override
    public void run() {
        logger.info("Worker " + workerId + " started");

        try {
            while (true) {
                Task task = taskQueue.getTask(); // Thread-safe access
                processTask(task);
            }
        } catch (InterruptedException e) {
            logger.warning("Worker " + workerId + " interrupted.");
        } catch (IOException e) {
            logger.severe("Worker " + workerId + " failed to write output.");
        }

        logger.info("Worker " + workerId + " finished.");
    }

    private void processTask(Task task) throws IOException {
        try {
            Thread.sleep(200); // Simulate processing time
            writer.write("Worker " + workerId + " processed task " + task.getId() + "\n");
            writer.flush();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}