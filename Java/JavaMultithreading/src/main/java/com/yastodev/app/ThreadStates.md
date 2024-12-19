# Thread States in Java

This document provides a concise overview of the different states a thread can occupy in Java, along with a brief explanation of the Main Thread.

---

## Thread States

### 1. **New State**
- A thread is in the "New" state by default when it is created.
- In this state, the thread's code has not yet started execution.

### 2. **Active State**
- A thread transitions to the "Active" state when the `start()` method is invoked.
- The Active state consists of two sub-states:

  **a. Runnable State:**
    - The thread is ready to run and awaits its time slice from the thread scheduler.
    - Multiple threads share CPU time intervals in a multithreading environment.
    - Threads in the Runnable state run for short durations and return to the Runnable state when their time slice ends.

  **b. Running State:**
    - A thread enters the Running state when the thread scheduler allocates CPU time.
    - After completing its time slice, the thread may move back to the Runnable state to await its next turn.

### 3. **Waiting/Blocked State**
- A thread enters the Waiting or Blocked state when it cannot proceed temporarily.

  **Examples:**
    - **Waiting State:** Thread T1 waits for a resource (e.g., a camera) already in use by another thread, T2.
    - **Blocked State:** Multiple threads (e.g., T2 and T3) attempt the same operation simultaneously and must wait for CPU allocation.

- The thread scheduler resolves the queue of waiting/blocked threads by allocating CPU time based on priority.

### 4. **Timed Waiting State**
- Prevents starvation by limiting the wait time for a thread.
- A thread enters the Timed Waiting state when the `sleep()` method is invoked with a specified duration.
- After the time expires, the thread resumes execution.

**Example:**
- Thread T1 performing a critical task doesn’t block T2 indefinitely due to timed waiting constraints.

### 5. **Terminated State**
- A thread enters the Terminated state when it has completed its task.
- Termination can occur:
    - **Normally:** When the thread finishes its execution.
    - **Abnormally:** Due to unexpected events like exceptions or segmentation faults.
- A terminated thread is no longer active and cannot be restarted.

---

## Main Thread
- The Main Thread is the entry point for code execution in Java programs.
- Each program has a default Main Thread provided by the JVM.
- This thread begins execution at the `main()` method and can spawn additional threads for multitasking.

---

This guide summarizes the thread lifecycle in Java, detailing the various states and their significance in multithreading programming.

