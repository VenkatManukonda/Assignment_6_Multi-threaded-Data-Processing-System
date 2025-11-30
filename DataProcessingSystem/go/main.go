package main

import (
	"fmt"
	"log"
	"os"
	"sync"
	"time"
)

// Task represents a single processing job
type Task struct {
	ID int
}

// Worker function
func worker(id int, tasks <-chan Task, wg *sync.WaitGroup, file *os.File) {
	defer wg.Done()

	log.Printf("Worker %d started", id)

	for task := range tasks {
		// Simulate processing time
		time.Sleep(200 * time.Millisecond)

		_, err := file.WriteString(
			fmt.Sprintf("Worker %d processed task %d\n", id, task.ID),
		)

		if err != nil {
			log.Printf("Worker %d error writing file: %v", id, err)
		}
	}

	log.Printf("Worker %d finished", id)
}

func main() {
	// Create output file
	file, err := os.Create("go_output.txt")
	if err != nil {
		log.Fatalf("Error creating output file: %v", err)
	}
	defer file.Close()

	// Channel as a safe concurrent queue
	taskChan := make(chan Task, 10)

	var wg sync.WaitGroup

	// Start workers
	for i := 1; i <= 4; i++ {
		wg.Add(1)
		go worker(i, taskChan, &wg, file)
	}

	// Add tasks
	for i := 1; i <= 20; i++ {
		taskChan <- Task{ID: i}
	}

	close(taskChan) // Signal workers to stop after finishing
	wg.Wait()       // Wait for all workers to finish
}
