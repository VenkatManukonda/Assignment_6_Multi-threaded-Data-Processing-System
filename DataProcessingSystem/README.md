# Data Processing System in Java and Go

This repository contains a Data Processing System implemented in **Java** and **Go**, developed for an Advanced Programming Languages course. The project demonstrates safe concurrency, worker coordination, error handling, resource management, and logging using two different programming paradigms.



##  Project Overview

The system simulates multiple worker threads (or goroutines) processing tasks in parallel. Each worker:

- Retrieves tasks from a shared queue  
- Processes the task (with simulated delay)  
- Writes output to a shared results file  
- Logs its activity and errors  

The implementation prevents race conditions, deadlocks, and resource leaks.



## Technologies Used

| Language | Concurrency Model | Mechanisms Used |
|---------|-------------------|-----------------|
| **Java** | Thread-based | `ExecutorService`, `ReentrantLock`, `Condition`, `try-catch` |
| **Go**   | CSP model        | Goroutines, Channels, `sync.WaitGroup`, error returns |



## Folder Structure

```
/java
    Main.java
    Worker.java
    Task.java
    TaskQueue.java

/go
    main.go
```


# Java Version

### How It Works
- The shared `TaskQueue` uses a `ReentrantLock` + `Condition` for safe access.
- Workers run in an `ExecutorService`.
- Each worker retrieves a task, processes it, and writes to a file.
- Logging is used to track start, process, error, and end events.
- The program shuts down using `shutdownNow()` safely.

---

## Running the Java Version

### **Compile**
```bash
cd java/src
javac *.java
```

### **Run**
```bash
java Main
```

### **Output File**
```
java_output.txt
```

---

# Go Version

### How It Works
- A buffered channel acts as a concurrency-safe task queue.
- Goroutines serve as workers.
- `sync.WaitGroup` ensures all workers complete.
- Errors are handled through explicit return values.
- A shared file is written safely using serialized writes.

---

## Running the Go Version
```bash
cd go
go run main.go
```

### **Output File**
```
go_output.txt
```

---

# Sample Output (Console)

### Java
```
INFO: Worker 1 started
INFO: Worker 2 started
INFO: Worker 3 started
INFO: Worker 4 started
INFO: Worker 1 finished
```

### Go
```
2025/02/10 Worker 1 started
2025/02/10 Worker 2 started
2025/02/10 Worker 3 started
2025/02/10 Worker 4 started
2025/02/10 Worker 3 finished
```




