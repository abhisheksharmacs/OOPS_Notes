### What is `static` in Java?

## `static` is a keyword that makes a member (variable, method, block, or nested class) belong to the  **class itself** , rather than to individual objects.

---

## 1. Static Variable (Class Variable)

A static variable is shared by all objects of the class.

```java
class Student {
    static String college = "KIET";
    String name;
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student();

        System.out.println(s1.college);
        System.out.println(s2.college);
    }
}
```

**Memory View**

```
Student Class
 └── college = "KIET"   (only one copy)

s1 Object
 └── name

s2 Object
 └── name
```

Even if 100 students are created, there is only **one copy** of `college`.

---

## 2. Static Method

A static method belongs to the class and can be called without creating an object.

```java
class Calculator {
    static int add(int a, int b) {
        return a + b;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(Calculator.add(10, 20));
    }
}
```

Called using:

```java
ClassName.methodName();
```

---

## Why is `main()` Static?

```java
public static void main(String[] args)
```

The JVM starts execution before creating any object.

If `main()` were non-static, the JVM would need an object first.

Therefore, it is declared `static` so the JVM can call it directly:

```java
Main.main(args);
```

---

## 3. Static Variable Example

```java
class Counter {
    static int count = 0;

    Counter() {
        count++;
    }
}

public class Main {
    public static void main(String[] args) {
        new Counter();
        new Counter();
        new Counter();

        System.out.println(Counter.count);
    }
}
```

Output:

```
3
```

All objects share the same `count`.

---

## 4. Static Methods Cannot Access Non-Static Members Directly

```java
class Test {
    int x = 10;

    static void show() {
        System.out.println(x);  // Error
    }
}
```

Why?

Because `x` belongs to an object, but `show()` belongs to the class.

There may be many objects, so Java doesn't know which object's `x` to use.

Correct way:

```java
class Test {
    int x = 10;

    static void show() {
        Test t = new Test();
        System.out.println(t.x);
    }
}
```

---

## 5. Static Block

Used for initialization that should happen only once when the class is loaded.

```java
class Demo {
    static {
        System.out.println("Static Block Executed");
    }

    public static void main(String[] args) {
        System.out.println("Main Method");
    }
}
```

Output:

```
Static Block Executed
Main Method
```

The static block runs before `main()`.

---

## Real-World Analogy

Consider a college:

* **College Name** → same for all students → `static`
* **Student Name** → different for each student → non-static

```java
class Student {
    static String college = "KIET";
    String name;
}
```

| Member      | Belongs To |
| ----------- | ---------- |
| `college` | Class      |
| `name`    | Object     |

---

## Interview Question

What is the difference between static and non-static?

| Static                     | Non-Static                     |
| -------------------------- | ------------------------------ |
| Belongs to class           | Belongs to object              |
| One copy only              | Separate copy per object       |
| Accessed using class name  | Accessed using object          |
| Loaded when class loads    | Created when object is created |
| Can be used without object | Requires object                |

### Easy Rule to Remember

**If something should be common to all objects, make it `static`.
If each object should have its own value, don't make it `static`.**

Yes,  **you can access a static member using an object reference** , but it is  **not recommended** .

Example:

```java
class Student {
    static String college = "KIET";
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student();

        System.out.println(s.college);      // Allowed
        System.out.println(Student.college); // Recommended
    }
}
```

Output:

```text
KIET
KIET
```

Both statements work because `college` belongs to the  **class** , not to the object.

---

### Why is using the object discouraged?

Consider:

```java
class Student {
    static String college = "KIET";
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student();

        s1.college = "AKTU";

        System.out.println(s2.college);
    }
}
```

Output:

```text
AKTU
```

At first glance, it looks like changing `s1.college` affected only `s1`, but actually there is only **one shared `college` variable** for the entire class.

Java internally treats:

```java
s1.college = "AKTU";
```

as:

```java
Student.college = "AKTU";
```

---

### The same applies to static methods

```java
class Test {
    static void show() {
        System.out.println("Hello");
    }
}

public class Main {
    public static void main(String[] args) {
        Test t = new Test();

        t.show();      // Allowed
        Test.show();   // Recommended
    }
}
```

Both work, but `Test.show()` clearly indicates that `show()` belongs to the class.

---

### Interview-style answer

**Can a static member be accessed using an object reference?**

Yes. Java allows static variables and methods to be accessed through an object reference, but since they belong to the class rather than the object, the recommended approach is to access them using the class name.

```java
obj.staticMember;      // Allowed but not recommended
ClassName.staticMember; // Recommended
```
