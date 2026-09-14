# Polymorphism in Java

**Polymorphism** means  **"many forms"** .

In Java, polymorphism allows the **same method call** to behave differently depending on the object that invokes it.

---

## Real-Life Example

Imagine a remote control with a **Power On** button.

* TV → turns on TV
* AC → turns on AC
* Speaker → turns on Speaker

Same action ( **powerOn()** ) but different behavior.

This is polymorphism.

---

# Types of Polymorphism in Java

## 1. Compile-Time Polymorphism (Static Polymorphism)

Achieved using  **Method Overloading** .

The compiler decides which method to call based on the parameters.

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

### Output

```text
30
60
31.0
```

Here, the same method name `add()` has multiple forms.

---

## 2. Runtime Polymorphism (Dynamic Polymorphism)

Achieved using  **Method Overriding** .

The method to execute is determined at runtime based on the actual object.

### Example

```java
class Animal {

    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {

    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {

    @Override
    void sound() {
        System.out.println("Cat meows");
    }
}

public class Main {

    public static void main(String[] args) {

        Animal a;

        a = new Dog();
        a.sound();

        a = new Cat();
        a.sound();
    }
}
```

### Output

```text
Dog barks
Cat meows
```

Notice:

```java
Animal a;
```

The reference type is `Animal`, but the behavior depends on the actual object (`Dog` or `Cat`).

This is runtime polymorphism.

---

# Important Point

```java
Animal a = new Dog();
```

### What can be accessed?

The **reference type** (`Animal`) decides:

* Which variables can be accessed
* Which methods are visible

The **object type** (`Dog`) decides:

* Which overridden method will execute

Example:

```java
class Animal {
    void sound() {
        System.out.println("Animal Sound");
    }
}

class Dog extends Animal {

    void sound() {
        System.out.println("Dog Bark");
    }

    void run() {
        System.out.println("Dog Running");
    }
}

public class Main {
    public static void main(String[] args) {

        Animal a = new Dog();

        a.sound();  // Allowed

        // a.run(); // Compilation Error
    }
}
```

Even though the object is `Dog`, the reference is `Animal`, so `run()` is not visible.

---

# Can Constructors be Polymorphic?

**No.**

Constructors:

* Cannot be overridden.
* Therefore cannot participate in runtime polymorphism.

```java
Animal a = new Dog();
```

Here polymorphism applies to methods, not constructors.

---

# Interview Definition

> Polymorphism is the ability of an object to take many forms. In Java, it allows the same method call to produce different behavior depending on the object. It is achieved through Method Overloading (compile-time polymorphism) and Method Overriding (runtime polymorphism).

---

## Memory Trick

* **Overloading → One class, Many methods**
* **Overriding → Parent reference, Child behavior**
