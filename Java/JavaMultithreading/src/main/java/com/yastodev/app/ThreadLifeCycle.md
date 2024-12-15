# Java Thread Lifecycle and Execution Mechanism

## **Thread Lifecycle**
The **thread lifecycle** refers to the various states a thread goes through during its execution in Java. These states are managed by the **JVM** and the **thread scheduler**, which determines which thread runs at a given time.

---

### **1. Thread Lifecycle States**

1. **New (Created)**:
    - A thread is in this state when it is first created using the `Thread` class or `Runnable` interface, but its `start()` method has not yet been called.
    - Example:
      ```java
      Thread t = new Thread(); // Thread created
      ```
    - At this point, the thread object exists, but no system resources are allocated for execution.

2. **Runnable**:
    - A thread enters this state when you call its `start()` method.
    - It is now ready to run, but **it does not start executing immediately**. The thread must wait for the thread scheduler to assign CPU time.
    - Example:
      ```java
      t.start(); // Moves to runnable state
      ```

3. **Running**:
    - When the thread scheduler picks a thread from the runnable pool, the thread enters the **running** state and starts executing its `run()` method.
    - Note:
        - The thread scheduler decides **which thread runs and for how long**, often using time slicing or preemption.
        - The `run()` method contains the logic that the thread executes.
    - Example:
      ```java
      public void run() {
          System.out.println("Thread is running");
      }
      ```

4. **Blocked/Waiting**:
    - A thread enters this state when it is temporarily **unable to proceed**.
    - Common reasons:
        - Waiting for a resource (I/O, lock, etc.).
        - Explicitly put to sleep using `Thread.sleep()` or waiting using `Object.wait()`.
    - Example:
      ```java
      synchronized (lock) {
          lock.wait(); // Waiting for a lock to be released
      }
      ```

5. **Terminated (Dead)**:
    - A thread enters the terminated state after completing its `run()` method or when it is explicitly stopped.
    - Once a thread is terminated, it cannot be restarted.
    - Example:
      ```java
      t.join(); // Wait for the thread to finish
      ```

---

### **2. Execution Mechanism of Threads**
When a thread is started (`start()`), the JVM uses an internal component called the **thread scheduler** to manage its execution. Here’s what happens under the hood:

#### **Key Steps in Execution Mechanism**
1. **Thread Creation**:
    - A thread object is created and initialized.
    - This involves setting up the thread’s priority, name, and other attributes.

2. **Calling `start()`**:
    - The thread transitions from the "new" state to the "runnable" state.
    - This registers the thread with the thread scheduler.

3. **Run Method Execution**:
    - The thread scheduler picks a thread from the runnable pool and runs its `run()` method.
    - Threads do **not necessarily run in the order they were started**. The scheduler uses algorithms like **round-robin**, **priority-based**, or **time-slicing** to allocate CPU time.

4. **Context Switching**:
    - When multiple threads are running, the scheduler periodically pauses one thread and starts another. This is called **context switching**.
    - The state of the paused thread is saved, so it can resume later without losing progress.

5. **Completion**:
    - Once the `run()` method finishes execution, the thread moves to the terminated state and cannot be restarted.

---

### **3. Lifecycle Example in Code**

```java
public class ThreadLifecycleExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("Thread is running...");
            try {
                Thread.sleep(1000); // Blocked state
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread finished!");
        });

        System.out.println("Thread State: " + t1.getState()); // NEW
        t1.start();
        System.out.println("Thread State: " + t1.getState()); // RUNNABLE

        Thread.sleep(500); // Give time for the thread to run
        System.out.println("Thread State: " + t1.getState()); // TIMED_WAITING or RUNNABLE

        t1.join(); // Wait for the thread to finish
        System.out.println("Thread State: " + t1.getState()); // TERMINATED
    }
}
```

---

### **4. Key Points**
- **Threads are not guaranteed to execute immediately after `start()`.**
    - The thread scheduler controls when and how threads are executed.
- **Parallelism vs Concurrency**:
    - Java threads allow concurrent programming, which **simulates parallelism** on systems with fewer cores than threads.
- **Run vs Start**:
    - Directly calling `run()` does not start a new thread. Instead, it runs on the current thread (e.g., the main thread).

---

### **5. Thread Lifecycle Visualization**
Here’s a simplified diagram of the thread lifecycle:

```
[New] --> [Runnable] --> [Running] --> [Terminated]
            ^               |
            |               v
        [Blocked/Waiting]
```

This lifecycle helps us understand how threads behave in a multi-threaded environment and how Java manages execution for efficiency.
