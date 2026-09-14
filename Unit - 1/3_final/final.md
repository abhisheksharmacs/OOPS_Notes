### `final` Keyword in Java

`final` is a **non-access modifier** used to restrict changes.

It can be applied to:

1. Variables
2. Methods
3. Classes

---

## 1. Final Variable

A final variable can be assigned  **only once** .

```java
class Test {
    final int x = 10;

    void display() {
        // x = 20;   // Error
        System.out.println(x);
    }
}
```

### Example

```java
final double PI = 3.14159;
```

Once initialized, `PI` cannot be changed.

---

## 2. Final Method

A final method **cannot be overridden** by a subclass.

```java
class Parent {
    final void show() {
        System.out.println("Parent Method");
    }
}

class Child extends Parent {
    // void show() { }   // Error
}
```

### Why use it?

When you want a method's implementation to remain unchanged in all subclasses.

---

## 3. Final Class

A final class  **cannot be inherited** .

```java
final class Vehicle {
    void start() {
        System.out.println("Starting");
    }
}

// class Car extends Vehicle { } // Error
```

### Real Example

```java
String
```

The Java `String` class is final, so no class can extend it.

---

## Final Reference Variable and Objects

A common interview question:

```java
final StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");    // Allowed

// sb = new StringBuilder("New"); // Error
```

### Why?

`final` makes the  **reference variable constant** , not the object itself.

* Object contents can change.
* Reference cannot point to another object.

---

## Comparison

| Usage              | Meaning              |
| ------------------ | -------------------- |
| `final variable` | Value cannot change  |
| `final method`   | Cannot be overridden |
| `final class`    | Cannot be inherited  |

---

### Memory Trick

**Final = No Further Modification**

* Final Variable → No reassignment
* Final Method → No overriding
* Final Class → No inheritance

Think of `final` as Java's way of saying: **"This is the last version; don't change it."**
