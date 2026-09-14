
### What is an Interface in Java?

An **interface** is a blueprint that specifies **what a class should do**, but not **how it should do it**.

It contains method declarations (contracts) that implementing classes must define.

#### Simple Syntax

```java
interface Animal {
    void sound();
}
```

Here, `Animal` is an interface. It says:

> Any class that wants to be an `Animal` must provide a `sound()` method.

---

### Implementing an Interface

```java
interface Animal {
    void sound();
}

class Dog implements Animal {

    public void sound() {
        System.out.println("Dog barks");
    }
}
```

The `Dog` class agrees to the contract defined by `Animal`, so it must implement `sound()`.

---

### Real-World Analogy

Think of a **remote control**.

The remote has buttons:

* Power
* Volume Up
* Volume Down

The remote specifies **what actions are available**, but it doesn't know how a particular TV performs those actions.

Similarly:

```java
interface Remote {
    void powerOn();
    void volumeUp();
}
```

Different TV brands can implement these methods differently.

---

### Why Do We Use Interfaces?

1. **Define a common contract**

   * Every implementing class follows the same rules.
2. **Achieve multiple inheritance**

   * A class can implement multiple interfaces.
3. **Increase flexibility**

   * Different classes can provide different implementations of the same interface.

---

### Key Point for Beginners

A class is a **complete implementation**:

```java
class Dog {
    void sound() {
        System.out.println("Dog barks");
    }
}
```

An interface is only a **promise/contract**:

```java
interface Animal {
    void sound();
}
```

It says:

> "Any class implementing me must provide a `sound()` method."

So, in one sentence:

**An interface is a contract that defines what methods a class must have, while the class provides the actual implementation of those methods.**
