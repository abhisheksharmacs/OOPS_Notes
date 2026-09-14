In Java, **checked and unchecked exceptions** are mainly different in  **whether the compiler forces you to handle them** .

### 1. Checked Exception

A **checked exception** is checked by the compiler at  **compile time** .

If a method can generate a checked exception, Java expects you to either:

* handle it using `try-catch`, **or**
* declare it using `throws`.

Common examples:

* `IOException`
* `SQLException`
* `FileNotFoundException`
* `ClassNotFoundException`

#### Example

```java
import java.io.*;

class Demo {
    public static void main(String[] args) throws IOException {

        FileReader file = new FileReader("data.txt");

        System.out.println("File opened");
    }
}
```

Here, `FileReader` can throw `FileNotFoundException`, which is a  **checked exception** .

So Java will complain if you don't handle it:

```java
FileReader file = new FileReader("data.txt");
```

You can handle it with:

```java
try {
    FileReader file = new FileReader("data.txt");
}
catch (FileNotFoundException e) {
    System.out.println("File not found");
}
```

Or declare it:

```java
public static void main(String[] args) throws FileNotFoundException
```

---

## 2. Unchecked Exception

An **unchecked exception** is  **not checked by the compiler** .

You can handle it with `try-catch`, but Java does **not force you** to do so.

These generally occur because of programming mistakes.

Common examples:

* `ArithmeticException`
* `NullPointerException`
* `ArrayIndexOutOfBoundsException`
* `NumberFormatException`
* `IllegalArgumentException`

### Example

```java
class Demo {
    public static void main(String[] args) {

        int a = 10;
        int b = 0;

        System.out.println(a / b);

        System.out.println("Hello");
    }
}
```

This code  **compiles successfully** .

But when you run it:

```text
Exception in thread "main" java.lang.ArithmeticException: / by zero
```

Java did not force us to handle it because `ArithmeticException` is  **unchecked** .

We can still handle it:

```java
try {
    int result = 10 / 0;
}
catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
}
```

---

## The important hierarchy

The easiest way to understand it is:

```text
                    Throwable
                       |
          +------------+------------+
          |                         |
       Error                    Exception
                                    |
                       +------------+------------+
                       |                         |
                RuntimeException          Other Exceptions
                       |                         |
                  UNCHECKED                  CHECKED
```

Examples:

```text
CHECKED
   ↓
IOException
SQLException
FileNotFoundException

UNCHECKED
   ↓
RuntimeException
   ↓
ArithmeticException
NullPointerException
ArrayIndexOutOfBoundsException
NumberFormatException
```

### Simple rule to remember

| Checked                   | Unchecked                          |
| ------------------------- | ---------------------------------- |
| Checked by compiler       | Not checked by compiler            |
| Compile-time checking     | Usually detected at runtime        |
| Must handle or declare    | No compulsory handling             |
| `IOException`           | `ArithmeticException`            |
| `SQLException`          | `NullPointerException`           |
| `FileNotFoundException` | `ArrayIndexOutOfBoundsException` |

**One-line memory trick:**

> **Checked = Compiler asks you to handle it.**
> **Unchecked = Compiler doesn't force you to handle it.**
