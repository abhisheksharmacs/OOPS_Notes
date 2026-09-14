# Inheritance in Java

**Inheritance means creating a new class from an existing class so that the new class can reuse the existing class's properties and methods.**

Think:

> **Existing class → Parent/Superclass**
> **New class → Child/Subclass**

### Basic syntax

```java
class Parent {
    int x = 10;

    void display() {
        System.out.println("Parent method");
    }
}

class Child extends Parent {
    int y = 20;
}
```

Here:

```text
        Parent
          ↑
          |
        Child
```

`Child` **inherits** `x` and `display()` from `Parent`.

So we can do:

```java
public class Main {
    public static void main(String[] args) {

        Child obj = new Child();

        System.out.println(obj.x);
        System.out.println(obj.y);

        obj.display();
    }
}
```

Output:

```text
10
20
Parent method
```

The important point is:

**We created a `Child` object, but that object can access members inherited from `Parent`.**

---

# Why do we need inheritance?

Suppose we have:

```java
class Employee {
    String name;
    double salary;

    void work() {
        System.out.println("Employee is working");
    }
}
```

Now suppose we want:

```java
Manager
Developer
Tester
```

All of them are employees.

Instead of writing:

```java
class Manager {
    String name;
    double salary;
    void work() { }
}

class Developer {
    String name;
    double salary;
    void work() { }
}

class Tester {
    String name;
    double salary;
    void work() { }
}
```

we can write:

```java
class Employee {
    String name;
    double salary;

    void work() {
        System.out.println("Employee is working");
    }
}

class Manager extends Employee {
    void manage() {
        System.out.println("Manager is managing");
    }
}

class Developer extends Employee {
    void code() {
        System.out.println("Developer is coding");
    }
}

class Tester extends Employee {
    void test() {
        System.out.println("Tester is testing");
    }
}
```

Now:

```text
                 Employee
                /    |    \
               /     |     \
          Manager Developer Tester
```

Each child gets the common functionality from `Employee`.

---

# What exactly is inherited?

A child class can inherit accessible:

* variables
* methods

For example:

```java
class Employee {
    String name = "Rahul";

    void work() {
        System.out.println("Working");
    }
}

class Developer extends Employee {
    void code() {
        System.out.println("Writing code");
    }
}
```

Then:

```java
Developer d = new Developer();

System.out.println(d.name);  // inherited variable
d.work();                    // inherited method
d.code();                    // Developer's own method
```

So the object has access to both:

```text
Employee members
       +
Developer members
```

---

# `extends` is the keyword

Inheritance is established using:

```java
extends
```

Example:

```java
class Animal {
    void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking");
    }
}
```

Now:

```java
Dog d = new Dog();

d.eat();   // inherited
d.bark();  // own method
```

---

# A very important concept: IS-A relationship

Inheritance represents an  **IS-A relationship** .

For example:

```text
Dog IS-A Animal
Car IS-A Vehicle
Manager IS-A Employee
Developer IS-A Employee
```

Therefore:

```java
class Dog extends Animal
```

makes sense.

But:

```java
class Engine extends Car
```

usually does **not** make sense because:

> Engine is not a Car.

That's a useful rule when deciding whether inheritance is appropriate.

---

# Types of inheritance in Java

Java supports these forms:

### 1. Single inheritance

```text
A
|
B
```

```java
class A { }

class B extends A { }
```

---

### 2. Multilevel inheritance

```text
A
|
B
|
C
```

```java
class A { }

class B extends A { }

class C extends B { }
```

Therefore `C` can access accessible members of both `B` and `A`.

---

### 3. Hierarchical inheritance

```text
       A
     /   \
    B     C
```

```java
class A { }

class B extends A { }

class C extends A { }
```

---

### 4. Multiple inheritance — NOT supported with classes

Java does **not** allow:

```text
       A     B
        \   /
          C
```

meaning:

```java
class C extends A, B { }   // ❌
```

Why?

Because it can create ambiguity.

Java solves this kind of requirement using  **interfaces** .

```java
interface A {
}

interface B {
}

class C implements A, B {
}
```

So:

> **One class can extend only one class, but it can implement multiple interfaces.**

---

# One very important thing: constructors are NOT inherited

Suppose:

```java
class Parent {

    Parent() {
        System.out.println("Parent constructor");
    }
}

class Child extends Parent {

    Child() {
        System.out.println("Child constructor");
    }
}
```

When we write:

```java
Child c = new Child();
```

Output:

```text
Parent constructor
Child constructor
```

This does **not** mean that the constructor was inherited.

Instead, the  **parent constructor is called when the child object is created** .

This happens through:

```java
super();
```

implicitly.

So Java conceptually does:

```java
Child() {
    super();
    System.out.println("Child constructor");
}
```

We'll want to explore `super` carefully after this because it is one of the most important parts of inheritance.

---

# Inheritance + access modifiers

This connects directly with what you were exploring earlier.

Suppose:

```java
class Parent {

    private int a = 10;
    int b = 20;
    protected int c = 30;
    public int d = 40;
}
```

Child:

```java
class Child extends Parent {

    void display() {

        // System.out.println(a);  // ❌ private

        System.out.println(b);    // ✅
        System.out.println(c);    // ✅
        System.out.println(d);    // ✅
    }
}
```

The `private` member belongs to the parent and is **not directly accessible** inside the child.

So inheritance does **not** mean:

> "Child gets unrestricted access to everything in Parent."

Access modifiers still apply.

---
