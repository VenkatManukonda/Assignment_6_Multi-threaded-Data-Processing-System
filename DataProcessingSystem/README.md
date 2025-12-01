Data Processing System – Java & Go

This project implements a parallel data processing system in both Java and Go.
It was developed for the Advanced Programming Languages course to demonstrate how different languages handle:

Concurrency

Synchronization

Worker coordination

Exception/error handling

Shared resource management

The system simulates multiple workers processing tasks at the same time and writing results safely to an output file.

Project Summary

Each version (Java and Go) includes:

A shared task queue

Multiple worker threads/goroutines

Simulated task processing delays

Logging for worker start, finish, and errors

Safe writing to a shared output file

Clean shutdown of all workers

Technologies Used
Language	Concurrency Model	Tools
Java	Thread-based	ExecutorService, ReentrantLock, Condition, try-catch
Go	CSP model	Goroutines, Channels, sync.WaitGroup
Folder Structure
/java
    Main.java
    Task.java
    Worker.java
    TaskQueue.java

/go
    main.go

Java Version
How it works

TaskQueue uses a lock + condition for thread-safe access.

Workers run inside an ExecutorService.

Each task is processed with a short delay.

Results are written safely to java_output.txt.

Run Java
cd java
javac *.java
java Main

Output File

java_output.txt

Go Version
How it works

A channel acts as a safe task queue.

Each goroutine takes tasks from the channel.

WaitGroup ensures all workers finish.

Results are written to go_output.txt.

Run Go
cd go
go run main.go

Output File

go_output.txt

Example Console Output
Java
Worker 1 started
Worker 2 started
Worker 3 started
Worker 4 started
Worker 1 finished

Go
Worker 1 started
Worker 2 started
Worker 3 started
Worker 4 started
Worker 3 finished
