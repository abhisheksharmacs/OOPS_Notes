# Complete Tutorial: Threads in Java

## 1. What is a Thread?

A  **thread is a lightweight unit of execution inside a process** .

In simple words:

> **A thread is an independent path of execution within a program.**

For example, imagine a shopping application:

* One task handles customer login.
* One task processes payments.
* One task updates the order status.
* One task sends notifications.

These tasks can execute independently using  **threads** .

### Process vs Thread

Think of a  **process as a shop** .

The shop has different sections:

```text
                    SHOP / PROCESS
                         |
          +--------------+--------------+
          |              |              |
       Billing        Inventory      Customer Service
       Thread 1        Thread 2         Thread 3
```

The **shop = Process**

The **workers/sections performing tasks = Threads**

So:

> **A process is a running program, while a thread is a smaller execution unit inside that process.**

---

# 2. Why Do We Need Threads?

Suppose we have this program:

```java
public class Demo {
    public static void main(String[] args) {

        downloadFile();

        playMusic();

        printDocument();
    }

    static void downloadFile() {
        System.out.println("Downloading...");
    }

    static void playMusic() {
        System.out.println("Playing music...");
    }

    static void printDocument() {
        System.out.println("Printing document...");
    }
}
```

The operations happen sequentially:

```text
Download
   ↓
Music
   ↓
Print
```

If downloading takes 10 seconds, the other tasks have to wait.

With threads:

```text
             Program
                |
       +--------+--------+
       |        |        |
   Download   Music    Print
   Thread 1  Thread 2  Thread 3
```

They can execute concurrently.

---

# 3. Creating a Thread in Java

There are two traditional ways to create a thread:

### Method 1

Extend the `Thread` class.

### Method 2

Implement the `Runnable` interface.

---

# 4. Creating Thread by Extending `Thread`

Create a class that extends `Thread`.

```java
class MyThread extends Thread {

    public void run() {
        System.out.println("Thread is running");
    }
}
```

Then create its object:

```java
public class Main {
    public static void main(String[] args) {

        MyThread t1 = new MyThread();

        t1.start();
    }
}
```

Output:

```text
Thread is running
```

### Important

We use:

```java
t1.start();
```

NOT:

```java
t1.run();
```

Why?

Because:

```java
start()
```

creates a **new thread** and then JVM calls `run()`.

Whereas:

```java
run()
```

is simply a normal method call.

---

# 5. `start()` vs `run()`

This is very important for students.

### Using `start()`

```java
MyThread t1 = new MyThread();

t1.start();
```

Conceptually:

```text
main thread
     |
     +---- creates Thread
              |
              ↓
          new thread
              |
              ↓
          run() executes
```

### Using `run()`

```java
t1.run();
```

There is  **no new thread** .

It behaves like:

```java
t1.someMethod();
```

So:

> **`start()` starts a new thread; `run()` only executes the method in the current thread.**

---

# 6. Creating Multiple Threads

```java
class MyThread extends Thread {

    public void run() {

        for(int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName()
                    + " : " + i);
        }
    }
}
```

Main:

```java
public class Main {

    public static void main(String[] args) {

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.start();
        t2.start();
    }
}
```

Possible output:

```text
Thread-0 : 1
Thread-1 : 1
Thread-0 : 2
Thread-1 : 2
Thread-1 : 3
Thread-0 : 3
...
```

Notice that the order isn't necessarily fixed.

That's because the  **thread scheduler decides which thread gets CPU time** .

---

# 7. Naming Threads

We can give threads meaningful names.

```java
MyThread t1 = new MyThread();
MyThread t2 = new MyThread();

t1.setName("Download Thread");
t2.setName("Music Thread");

t1.start();
t2.start();
```

Inside the thread:

```java
System.out.println(Thread.currentThread().getName());
```

Output:

```text
Download Thread
Music Thread
```

---

# 8. Creating Thread Using `Runnable`

The second approach is:

```java
implements Runnable
```

Example:

```java
class MyTask implements Runnable {

    public void run() {
        System.out.println("Thread is running");
    }
}
```

Then:

```java
public class Main {

    public static void main(String[] args) {

        MyTask task = new MyTask();

        Thread t1 = new Thread(task);

        t1.start();
    }
}
```

Here there are two objects:

```text
MyTask object
     |
     ↓
Runnable task
     |
     ↓
Thread object
     |
     ↓
start()
```

---

# 9. Why Do We Need `Runnable`?

This is where students often ask:

> "If we already have `Thread`, why do we need `Runnable`?"

Because Java supports  **single inheritance** .

Suppose:

```java
class Employee extends Person
```

Now Employee also needs to perform a task in a thread.

We cannot write:

```java
class Employee extends Person, Thread
```

Java doesn't support multiple class inheritance.

But we can do:

```java
class Employee extends Person implements Runnable {

    public void run() {
        System.out.println("Employee task");
    }
}
```

This is one of the major advantages of `Runnable`.

---

# 10. Thread vs Runnable

| Thread                              | Runnable                      |
| ----------------------------------- | ----------------------------- |
| Extend `Thread`                   | Implement `Runnable`        |
| Cannot extend another class         | Can extend another class      |
| Task and thread are tightly coupled | Task and thread are separated |
| Less flexible                       | More flexible                 |
| Suitable for simple examples        | Preferred for reusable tasks  |

### My recommendation for teaching

Tell students:

> **Use `Thread` when you are simply learning or demonstrating threads. In real applications, prefer `Runnable` or higher-level concurrency APIs because task and thread are separated.**

---

# 11. Real-Life Analogy

Think about a restaurant.

### Thread approach

```text
Chef = Thread
Cooking = run()
```

The chef itself represents the thread.

### Runnable approach

```text
Cooking Task = Runnable
Chef = Thread
```

The cooking task is separate from the worker executing it.

This is a much better design.

---

# 12. Lambda Expression with Runnable

Because `Runnable` is a functional interface, we can write:

```java
Runnable task = () -> {
    System.out.println("Thread is running");
};

Thread t1 = new Thread(task);

t1.start();
```

Or even:

```java
Thread t1 = new Thread(() -> {
    System.out.println("Thread is running");
});

t1.start();
```

This is the modern Java style.

---

# 13. Important Thread Methods

Some commonly used methods are:

```text
start()
run()
sleep()
join()
getName()
setName()
currentThread()
isAlive()
```

---

# 14. `sleep()`

`sleep()` pauses the current thread for a specified amount of time.

Example:

```java
class MyThread extends Thread {

    public void run() {

        for(int i = 1; i <= 5; i++) {

            System.out.println(i);

            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
```

Here:

```java
Thread.sleep(1000);
```

means approximately:

```text
Sleep for 1000 milliseconds
        =
       1 second
```

---

# 15. `join()`

Suppose:

```java
Thread t1 = new Thread(...);
Thread t2 = new Thread(...);
```

If we write:

```java
t1.start();
t1.join();

t2.start();
```

The main thread waits until `t1` finishes.

Conceptually:

```text
Main
 |
 +---- start t1
 |
 |    t1 running
 |       |
 |       ↓
 |     finish
 |
 +---- start t2
```

So:

> **`join()` makes one thread wait for another thread to finish.**

---

# 16. Thread Lifecycle

A simplified lifecycle:

```text
             NEW
              |
              | start()
              ↓
           RUNNABLE
              |
              ↓
          RUNNING
          /     \
         /       \
     sleep()    waiting
       |          |
       ↓          ↓
    TIMED      WAITING
   WAITING         |
       \           /
        \         /
         ↓       ↓
          RUNNABLE
              |
              ↓
         TERMINATED
```

Strictly speaking, Java's `Thread.State` uses states such as:

```text
NEW
RUNNABLE
BLOCKED
WAITING
TIMED_WAITING
TERMINATED
```

---

# 17. The Real Problem: Shared Resources

Now we reach the most important part.

Suppose two threads access the same bank account.

```java
class BankAccount {

    int balance = 1000;

    void withdraw(int amount) {

        if(balance >= amount) {

            System.out.println("Processing withdrawal...");

            balance = balance - amount;

            System.out.println("Withdrawal successful");
        }
        else {
            System.out.println("Insufficient balance");
        }
    }
}
```

Suppose:

```text
Balance = ₹1000
```

Two threads simultaneously try:

```text
Thread 1 → Withdraw ₹1000

Thread 2 → Withdraw ₹1000
```

Both may check:

```java
balance >= amount
```

before either updates the balance.

This can produce an incorrect result.

This is called a:

# Race Condition

> A **race condition** occurs when multiple threads access shared data concurrently and the final result depends on the timing/order of execution.

---

# 18. Synchronization

To solve this problem, Java provides:

```java
synchronized
```

Example:

```java
class BankAccount {

    int balance = 1000;

    synchronized void withdraw(int amount) {

        if(balance >= amount) {

            System.out.println(
                Thread.currentThread().getName()
                + " is withdrawing..."
            );

            balance = balance - amount;

            System.out.println(
                "Remaining balance = " + balance
            );
        }
        else {
            System.out.println("Insufficient balance");
        }
    }
}
```

Now only **one thread at a time** can execute the synchronized method on the same object.

---

# 19. Simple Understanding of Synchronization

Think of a  **single washroom with one key** .

```text
              WASHROOM
                 |
             [ ONE KEY ]
                 |
        +--------+--------+
        |                 |
     Person 1          Person 2
        |
      enters
        |
      uses
        |
      exits
        |
      key returned
                          |
                        enters
```

Only one person can use it at a time.

Similarly:

```text
Thread 1 → LOCK → Critical Section → UNLOCK
Thread 2 → waits
Thread 2 → LOCK → Critical Section → UNLOCK
```

---

# 20. Synchronized Block

Instead of synchronizing the entire method:

```java
synchronized void withdraw() {
    // entire method locked
}
```

we can synchronize only a particular section:

```java
void withdraw(int amount) {

    synchronized(this) {

        if(balance >= amount) {
            balance -= amount;
        }
    }
}
```

This is useful when only a small part of the method accesses shared data.

---

# 21. What is a Critical Section?

A **critical section** is a part of the program where shared resources are accessed or modified.

Example:

```java
synchronized(this) {

    balance = balance - amount;
}
```

This is the critical section.

We want:

> **Only one thread at a time to execute the critical section.**

---

# 22. Synchronization Example

Complete example:

```java
class Counter {

    int count = 0;

    synchronized void increment() {
        count++;
    }
}
```

Two threads:

```java
public class Main {

    public static void main(String[] args)
            throws InterruptedException {

        Counter c = new Counter();

        Thread t1 = new Thread(() -> {

            for(int i = 0; i < 1000; i++) {
                c.increment();
            }

        });

        Thread t2 = new Thread(() -> {

            for(int i = 0; i < 1000; i++) {
                c.increment();
            }

        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(c.count);
    }
}
```

Expected:

```text
2000
```

Synchronization ensures that the increment operation is protected.

---

# 23. What Happens Without Synchronization?

If we write:

```java
void increment() {
    count++;
}
```

instead of:

```java
synchronized void increment() {
    count++;
}
```

the final result may be less than:

```text
2000
```

Why?

Because:

```java
count++;
```

is not actually one indivisible operation.

Conceptually:

```text
Read count
   ↓
Add 1
   ↓
Write count
```

Two threads can interfere with each other.

---

# 24. Deadlock

Now comes one of the most interesting thread problems.

> **Deadlock occurs when two or more threads are permanently waiting for resources held by each other.**

Imagine:

```text
Thread 1 has Resource A
Thread 2 has Resource B

Thread 1 wants Resource B
Thread 2 wants Resource A
```

Neither can continue.

```text
        Thread 1
           |
     has Lock A
           |
     wants Lock B
           ↑
           |
     has Lock B
           |
        Thread 2
```

Both are waiting forever.

---

# 25. Java Deadlock Example

```java
class Resource {

    public void method1(Resource r) {

        synchronized(this) {

            System.out.println("Thread 1 locked Resource 1");

            synchronized(r) {

                System.out.println(
                    "Thread 1 locked Resource 2"
                );
            }
        }
    }

    public void method2(Resource r) {

        synchronized(this) {

            System.out.println("Thread 2 locked Resource 2");

            synchronized(r) {

                System.out.println(
                    "Thread 2 locked Resource 1"
                );
            }
        }
    }
}
```

Main:

```java
public class Main {

    public static void main(String[] args) {

        Resource r1 = new Resource();
        Resource r2 = new Resource();

        Thread t1 = new Thread(() -> {
            r1.method1(r2);
        });

        Thread t2 = new Thread(() -> {
            r2.method2(r1);
        });

        t1.start();
        t2.start();
    }
}
```

Possible situation:

```text
T1 → locks r1
T2 → locks r2

T1 → wants r2
T2 → wants r1

T1 → WAITING
T2 → WAITING
```

Nobody can proceed.

**Deadlock!**

---

# 26. Four Conditions for Deadlock

A classic deadlock generally requires four conditions:

### 1. Mutual Exclusion

Only one thread can use a resource at a time.

### 2. Hold and Wait

A thread holds one resource while waiting for another.

### 3. No Preemption

A resource cannot simply be forcibly taken away.

### 4. Circular Wait

Threads form a circular chain of waiting.

```text
T1 → R2
↑     ↓
R1 ← T2
```

If we can break one of these conditions, we can prevent deadlock.

---

# 27. How to Prevent Deadlock?

A common practical technique is:

> **Always acquire locks in the same order.**

For example:

```text
Thread 1:
Lock A
Lock B

Thread 2:
Lock A
Lock B
```

Instead of:

```text
Thread 1:
Lock A
Lock B

Thread 2:
Lock B
Lock A
```

The second approach can create circular waiting.

---

# 28. Thread Safety

A class is considered **thread-safe** when multiple threads can use it concurrently without causing incorrect behavior.

For example:

```java
class Counter {

    private int count = 0;

    synchronized void increment() {
        count++;
    }
}
```

The synchronization helps make the shared update thread-safe.

---

# 29. Complete Conceptual Flow

I would teach the topic in this sequence:

```text
                    THREAD
                       |
          +------------+------------+
          |                         |
       PROCESS                    THREAD
          |                         |
       Program               Unit of execution
                                    |
                         +----------+----------+
                         |                     |
                    Extend Thread        Implement Runnable
                         |                     |
                       run()                 run()
                         |                     |
                      start()               Thread object
                                               |
                                             start()
                                               |
                                         Multiple Threads
                                               |
                                               ↓
                                        Shared Resource
                                               |
                                               ↓
                                         Race Condition
                                               |
                                               ↓
                                        Synchronization
                                               |
                                               ↓
                                          synchronized
                                               |
                                               ↓
                                          Thread Safety
                                               |
                                               ↓
                                            Deadlock
                                               |
                                               ↓
                                      Deadlock Prevention
```

---

# 30. The Most Important Difference to Tell Students

### Thread

```java
class MyThread extends Thread {

    public void run() {
        // task
    }
}
```

### Runnable

```java
class MyTask implements Runnable {

    public void run() {
        // task
    }
}

Thread t = new Thread(new MyTask());
```

The key idea is:

> **`Thread` represents the worker/execution mechanism, while `Runnable` represents the task to be performed.**

That's why `Runnable` generally provides better separation of concerns.

---

# 31. A Practical Example Combining Everything

Let's create a  **banking example** .

```java
class BankAccount {

    private int balance = 1000;

    synchronized void withdraw(int amount) {

        String name = Thread.currentThread().getName();

        System.out.println(name +
                " wants to withdraw ₹" + amount);

        if(balance >= amount) {

            System.out.println(name +
                    " is processing...");

            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {
                System.out.println(e);
            }

            balance -= amount;

            System.out.println(name +
                    " withdrawal successful");

            System.out.println(
                    "Remaining balance: ₹" + balance);
        }
        else {

            System.out.println(name +
                    " - Insufficient balance");
        }
    }
}
```

Main:

```java
public class Main {

    public static void main(String[] args)
            throws InterruptedException {

        BankAccount account = new BankAccount();

        Runnable task = () -> {
            account.withdraw(1000);
        };

        Thread t1 = new Thread(task, "Customer 1");
        Thread t2 = new Thread(task, "Customer 2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Transaction processing completed");
    }
}
```

Because `withdraw()` is synchronized:

```text
Customer 1
    ↓
gets lock
    ↓
withdraws ₹1000
    ↓
balance = ₹0
    ↓
releases lock
    ↓
Customer 2
    ↓
gets lock
    ↓
checks balance
    ↓
Insufficient balance
```

Without synchronization, both customers could potentially pass the balance check before either update occurs.

---

# 32. One Important Modern Java Point

For beginners, we teach:

```text
Thread
Runnable
synchronized
```

But in real Java applications, we usually don't manually create a new thread for every task.

We often use:

```text
ExecutorService
Thread Pool
Callable
Future
CompletableFuture
```

For example:

```java
ExecutorService executor =
        Executors.newFixedThreadPool(3);

executor.submit(() -> {
    System.out.println("Task running");
});

executor.shutdown();
```

This is a more scalable approach because the application can **reuse a pool of threads** instead of constantly creating new ones.

---

# 33. Quick Revision Table

| Concept          | Meaning                                      |
| ---------------- | -------------------------------------------- |
| Process          | Running program                              |
| Thread           | Unit of execution                            |
| `Thread`       | Class used to create/manage threads          |
| `Runnable`     | Represents a task                            |
| `run()`        | Contains thread task                         |
| `start()`      | Starts a new thread                          |
| `sleep()`      | Temporarily pauses current thread            |
| `join()`       | Waits for another thread to finish           |
| Race condition   | Threads incorrectly compete over shared data |
| Synchronization  | Controls access to shared resources          |
| `synchronized` | Provides intrinsic locking                   |
| Critical section | Code accessing shared resource               |
| Deadlock         | Threads wait indefinitely for each other     |
| Thread-safe      | Safe for concurrent access                   |

## The one-line story for students

You can summarize the entire chapter like this:

> **Threads allow multiple tasks to execute concurrently; when those threads share resources, race conditions can occur, so we use synchronization to protect critical sections—but improper locking can lead to deadlock.**

That gives you a very natural teaching progression:

**Thread → Creating Thread → Thread vs Runnable → `start()`/`run()` → Multiple Threads → `sleep()` → `join()` → Shared Resource → Race Condition → Synchronization → Critical Section → Deadlock → Deadlock Prevention → Thread Pool.**
