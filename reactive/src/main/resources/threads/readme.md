

A Java thread is the smallest unit of execution within a program. 
It is a lightweight subprocess that runs independently but shares the same memory 
space as the process, allowing multiple tasks to execute concurrently.

Why Use Threads?
1. Concurrency: Threads enable concurrent execution of tasks, allowing multiple operations to run simultaneously, improving the responsiveness of applications.
2. Resource Sharing: Threads within the same process share the same memory space, making it easier to share data and resources between them without the need for complex inter-process communication.
3. Performance: by executing sub-tasks concurrently on multi-core processors.
4. Responsiveness: In GUI applications, threads can keep the user interface responsive while performing time-consuming tasks in the background.
5. Simplified Design: Complex applications can often be designed more modular by assigning different responsibilities to different threads.

Types of Threads:
1. User Threads: These are the threads created by the application to perform specific tasks. They can be either daemon or non-daemon threads.
2. Daemon Threads: These are background threads that do not prevent the JVM from exiting when all user threads have finished. They are typically used for tasks such as garbage collection or background monitoring.

Thread Lifecycle:
1. New:  created (new Thread())  but not yet started (start() not called).
2. Runnable: Ready to run but is waiting for the CPU to schedule it.
3. Running: executing its task.
4. Terminated: has completed its task or has been stopped.
5. Waiting: waiting indefinitely for another thread to perform a particular action, such as releasing a lock or notifying it. (e.g., calling Object.wait(), Thread.join()).
6. Timed Waiting: waiting for a specified amount of time for another thread to perform a particular action (e.g., calling Thread.sleep(), Object.wait(timeout), Thread.join(timeout)).
7. Blocked: A thread is in the blocked state when it is waiting for a resource or another thread to release a lock.

```
start() :
 - initializes a new thread of execution 
 - calls the run() method
run() :
    - runs on the main thread or current thread
    - contains the code that defines the thread's task
    - can be overridden to specify the thread's behavior
join() :
    - allows one thread to wait for the completion of another thread
    - blocks the calling thread until the thread on which join() is called has finished executing
sleep() :
    - causes the current thread to pause execution for a specified period of time
    - does not release any locks held by the thread
yield() :
    - causes the current thread to temporarily pause and allow other threads to execute
    - does not guarantee that the current thread will be paused or that other threads will execute immediately
stop() :
    - deprecated method that was used to forcefully terminate a thread
    - terminate in the middle of critical operations, leaving shared data in an inconsistent state
    - should use more controlled shutdown mechanisms (e.g., using a volatile boolean flag or interrupting the thread)
interrupt() :
    - signals a thread to stop what it is doing and do something else (e.g., terminate or handle the interruption)
    - does not forcefully stop the thread but sets an interrupt flag that the thread can check and respond to
    
```

## sleep() vs wait() vs join() — Quick Difference

| Method     | What it does                                  | Lock released? | Used for                         |
|------------|-----------------------------------------------|----------------|----------------------------------|
| `sleep()`  | Pauses current thread for a fixed time        | ❌ No          | Delay / timing                  |
| `wait()`   | Pauses until notified (`notify/notifyAll`)    | ✅ Yes         | Thread communication            |
| `join()`   | Waits for another thread to finish            | ❌ No          | Thread coordination (ordering)  |

---

### 🧠 In your head:
- `sleep()` → *"I rest for X time"*
- `wait()` → *"I wait until someone wakes me up"*
- `join()` → *"I wait until that thread is done"*
- 
Setting Thread Priority
- Threads can be assigned a priority level sing the setPriority(): (Thread.MIN_PRIORITY (1), Thread.NORM_PRIORITY (5), Thread.MAX_PRIORITY (10)).
-  Higher-priority threads are more likely to be scheduled first, but again, this depends on the JVM and underlying OS.
- Example:  in a game engine, you might give rendering threads higher priority than background data loading.



Concurrency :

synchronized : control access to a particular resource or block of code
- Each object in Java has an intrinsic lock (also called a monitor lock).
- When a thread enters a synchronized method or block, it attempts to acquire the lock of the object.
- If the lock is available, the thread acquires it and proceeds. If not, the thread enters a BLOCKED state until the lock is released.
- The lock is automatically released when the synchronized block/method completes or throws an exception.
- synchronized blocks can be used to synchronize on any object, while synchronized methods implicitly synchronize on the instance of the class (for non-static methods) or the Class object (for static methods).


volatile :
- a keyword used to indicate that a variable's value may be modified by multiple threads
- ensures that changes to the variable are immediately visible to all threads, preventing issues with caching

atomic variables :
- provide thread-safe operations on single variables without the need for locks/synchronization
- use compare-and-set (CAS) to ensure thread safety
- (CAS) is a hardware-level atomic instruction used to achieve synchronization without locking:
“Update a value ONLY IF it is still what I expect it to be.”

It has 3 parts:
Expected value → what I think the value currently is
New value → what I want to set
Actual value in memory


```
if (currentValue == expectedValue)
    update it to newValue
else
    fail and retry

```

CAS avoids locks by doing this:

Instead of:
LOCK → read → modify → write → UNLOCK
It does:
read → compute → CAS retry loop

Example of Compare and Swap
To illustrate this, consider the following example:


We have a variable, currentValue = 27
We expect this value to be expectedValue = 27
We want to swap it to newValue = 99
In this case, since currentValue equals expectedValue, the value will successfully swap to 99, and the operation will return true. If, however, currentValue was not equal to expectedValue, the variable would retain its original value, and the operation would return false.

ressource : https://www.linkedin.com/pulse/compare-swap-elham-moharrami-o2dwf


## Synchronized vs Volatile vs Atomic

| Feature        | Synchronized                                              | Volatile                          | Atomic                              |
|----------------|----------------------------------------------------------|-----------------------------------|-------------------------------------|
| Applies to     | Methods / blocks                                         | Variables                         | Variables                           |
| Purpose        | Ensures mutual exclusion and consistency (via locks)     | Ensures visibility (no atomicity) | Provides atomic operations (no locks) |
| Performance    | Lower (due to locking)                                   | Higher than synchronized          | Higher than both synchronized and volatile |
| Concurrency    | Prone to deadlocks / livelocks                           | Immune (no locks)                 | Immune (no locks)                  |

Avoiding Deadlocks

A deadlock occurs when two or more threads are waiting for each other to release locks, and none can proceed. This usually happens when multiple locks are acquired in an inconsistent order.
To avoid deadlocks:
1. Always acquire locks in a consistent order across all threads.
2. Use tryLock() with a timeout to avoid waiting indefinitely for a lock.
3. Avoid holding multiple locks at the same time if possible.

Thread Coordination
- You may need inter-thread communication, memory visibility, and coordinated execution.
  For example, in a producer-consumer setup, the producer adds items to a queue and the consumer waits until items are available.

ava provides three methods to support this:

```
wait(): Causes the current thread to release the lock and wait until another thread calls notify() or notifyAll().
notify(): Wakes up a single waiting thread.
notifyAll(): Wakes up all waiting threads


```

Lock (ReentrantLock) :
- an interface that provides more flexible and sophisticated locking mechanisms than synchronized blocks
- used for Try-locking with timeouts, Interruptible locks , Fair locking order
- allows for features such as tryLock() and lockInterruptibly()
- synchronized auto-releases when the block exits, even on exception. ReentrantLock does NOT — you must handle it yourself.

Condition :
- an interface that provides a way for threads to wait for certain conditions to be met before proceeding
- typically used in conjunction with Lock to manage thread synchronization and coordination

Executor Framework : 
- creating and managing individual threads manually becomes inefficient 
- simplifies thread management and improves performance using thread pools.

Thread Pool : 
- solves : overhead due to the cost of thread creation, increased memory usage, and the complexity of managing the thread lifecycle
- fixed by: reusing a fixed number of threads to execute multiple task

## Java Concurrency: Quick Comparison

| Aspect              | Raw Threads (`Thread`)                  | ExecutorService (Thread Pool)        |
|--------------------|----------------------------------------|-------------------------------------|
| Level              | Low-level                              | High-level                          |
| Thread Creation    | New thread every time                  | Reuses threads (pool)               |
| Performance        | Expensive                              | Efficient                           |
| Scalability        | Poor (risk of too many threads)        | Good (controlled pool size)         |
| Task Management    | Manual                                 | Managed by framework                |
| Results Handling   | Manual                                 | `Future`, `Callable`                |
| Lifecycle Control  | Hard                                   | Easy (`shutdown()`, etc.)           |
| Use Case           | Learning / simple tasks                | Real-world concurrent applications  |


