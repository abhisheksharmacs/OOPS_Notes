# Abstraction in Java

**Abstraction** means **showing only the essential features and hiding the implementation details**.

The user knows **what an object does**, but not necessarily **how it does it**.

---

## Real-Life Example

### Car

When you drive a car:

* You press the accelerator → car speeds up.
* You press the brake → car slows down.

You don't need to know:

* How fuel is injected.
* How the engine generates power.
* How the transmission works.

You only use the necessary features.

This is **abstraction**.

---

## Java Example

### Without worrying about implementation

```java
abstract class Vehicle {

    abstract void start();
}

class Car extends Vehicle {

    void start() {
        System.out.println("Car starts using a key");
    }
}

public class Main {
    public static void main(String[] args) {

        Vehicle v = new Car();
        v.start();
    }
}
```

**Output**

```text
Car starts using a key
```

Here:

* `Vehicle` says every vehicle must have a `start()` method.
* It does not tell how to start.
* `Car` provides the actual implementation.

The user only knows:

```java
v.start();
```

and doesn't care how it is implemented.

---

## How Abstraction is Achieved in Java

### 1. Abstract Classes

```java
abstract class Shape {
    abstract void draw();
}
```

An abstract class can have:

* Abstract methods
* Normal methods
* Variables
* Constructors

---

### 2. Interfaces

```java
interface Payment {
    void pay();
}
```

Classes provide the implementation.

```java
class UPI implements Payment {

    public void pay() {
        System.out.println("Payment through UPI");
    }
}
```

---

## Encapsulation vs Abstraction

| Encapsulation                    | Abstraction                                         |
| -------------------------------- | --------------------------------------------------- |
| Hides data                       | Hides implementation                                |
| Achieved using private variables | Achieved using abstract classes and / OR interfaces |
| Focuses on security of data      | Focuses on simplifying usage                        |
| "How to protect data?"           | "What should the user see?"                         |

### Example

```java
class Student {
    private int marks;   // Encapsulation
}
```

```java
abstract class Vehicle {
    abstract void start();   // Abstraction
}
```

---
