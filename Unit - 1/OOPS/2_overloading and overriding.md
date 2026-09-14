## 1. Method Overloading

**Definition:**
When multiple methods in the **same class** have the **same name** but  **different parameter lists** .

### Example

```java
class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    double add(double a, double b) {
        return a + b;
    }
}

public class Main {
    public static void main(String[] args) {
        Calculator c = new Calculator();

        System.out.println(c.add(10, 20));
        System.out.println(c.add(10, 20, 30));
        System.out.println(c.add(10.5, 20.5));
    }
}
```

### Rules for Overloading

✅ Method name must be same
✅ Parameter list must be different

Can differ by:

* Number of parameters
* Type of parameters
* Order of parameters

```java
void show(int x)
void show(double x)
void show(int x, int y)
void show(String s, int x)
```

❌ Changing only return type is not overloading

```java
int show()
double show()   // Error
```

---

## **2. overriding**

A child class inherits a method from the parent class but decides to provide its own version of that method.

### Simple Example

```java
class Vehicle {

    void start() {
        System.out.println("Vehicle is starting");
    }
}

class Car extends Vehicle {

    void start() {
        System.out.println("Car starts with a key");
    }
}
```

Here:

* `Vehicle` already has a method `start()`.
* `Car` inherits that method.
* But `Car` writes its **own implementation** of `start()`.

This is called  **method overriding** .

### Why do we override?

Because the child class wants behavior that is more specific to itself.

For example:

```java
class Animal {
    void sound() {
        System.out.println("Some animal sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Bark");
    }
}

class Cat extends Animal {
    void sound() {
        System.out.println("Meow");
    }
}
```

Every animal makes a sound, but each animal makes a **different** sound. So the child classes override the parent's `sound()` method.

### Key Idea

The method in the child class must have:

* Same name
* Same parameters
* Same return type (or compatible return type)

```java
class Parent {
    void display() { }
}

class Child extends Parent {
    void display() { }   // Overriding
}
```
