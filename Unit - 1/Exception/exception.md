### Exception in Java

An **exception** is an event that occurs during program execution and disrupts the normal flow of the program.

For example, if you try to divide a number by zero, Java throws an exception.

```java
public class Main {
    public static void main(String[] args) {
        int a = 10;
        int b = 0;

        int c = a / b;   // Exception occurs here
        System.out.println(c);
    }
}
```

**Output:**

```
Exception in thread "main" java.lang.ArithmeticException: / by zero
```

---

## Why Exceptions are Used

* Prevent abrupt program termination.
* Allow handling errors gracefully.
* Help identify and debug problems.

---

## Exception Handling Keywords

### 1. try

Code that may cause an exception is placed inside a `try` block.

```java
try {
    int x = 10 / 0;
}
```

### 2. catch

Handles the exception.

```java
try {
    int x = 10 / 0;
}
catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
}
```

**Output:**

```
Cannot divide by zero
```

### 3. finally

Executes whether an exception occurs or not.

```java
try {
    int x = 10 / 0;
}
catch (ArithmeticException e) {
    System.out.println("Error");
}
finally {
    System.out.println("Always executed");
}
```

**Output:**

```
Error
Always executed
```

### 4. throw

Used to explicitly create an exception.

```java
public class Main {
    public static void main(String[] args) {
        throw new ArithmeticException("Invalid operation");
    }
}
```

### 5. throws

Used in method declaration to indicate that a method may throw an exception.

```java
class Test {
    static void checkAge(int age) throws Exception {
        if(age < 18)
            throw new Exception("Not eligible");
    }
}
```

---

## Types of Exceptions

### 1. Checked Exceptions

Checked at compile time.

Examples:

* `IOException`
* `SQLException`
* `FileNotFoundException`

```java
import java.io.*;

class Test {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("abc.txt");
    }
}
```

---

### 2. Unchecked Exceptions

Occur at runtime.

Examples:

* `ArithmeticException`
* `NullPointerException`
* `ArrayIndexOutOfBoundsException`

```java
int arr[] = {10,20,30};
System.out.println(arr[5]);  // Runtime Exception
```

---

## Common Exceptions in Java

| Exception                      | Cause                               |
| ------------------------------ | ----------------------------------- |
| ArithmeticException            | Divide by zero                      |
| NullPointerException           | Accessing member of null object     |
| ArrayIndexOutOfBoundsException | Invalid array index                 |
| NumberFormatException          | Invalid string-to-number conversion |
| ClassCastException             | Invalid type casting                |
| IOException                    | Input/output operation failure      |

---

A **multiple catch block** is used when different types of exceptions can occur in the same `try` block, and you want to handle each exception differently.

### Simple Example

```java
public class Test {
    public static void main(String[] args) {

        try {

            int arr[] = {10, 20, 30};

            int result = 10 / 0;      // ArithmeticException

            System.out.println(arr[5]); // ArrayIndexOutOfBoundsException

        }
        catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid array index");
        }

        System.out.println("Program continues...");
    }
}
```

### Output

```text
Cannot divide by zero
Program continues...
```

---

### What Happened?

1. Execution enters the `try` block.
2. `10 / 0` causes an `ArithmeticException`.
3. Java immediately leaves the `try` block.
4. The matching `catch (ArithmeticException e)` block executes.
5. The second catch block is skipped.
6. The program continues after all catch blocks.

---

### Another Example

```java
public class Test {
    public static void main(String[] args) {

        try {

            int arr[] = {10, 20, 30};

            System.out.println(arr[5]); // Exception

        }
        catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid array index");
        }

        System.out.println("Program continues...");
    }
}
```

### Output

```text
Invalid array index
Program continues...
```

---

### Important Rule

When using multiple catch blocks, place **more specific exceptions first** and  **more general exceptions later** .

✅ Correct:

```java
try {
    // code
}
catch (ArithmeticException e) {
    System.out.println("Arithmetic Error");
}
catch (Exception e) {
    System.out.println("General Error");
}
```

❌ Wrong:

```java
try {
    // code
}
catch (Exception e) {
    System.out.println("General Error");
}
catch (ArithmeticException e) {
    System.out.println("Arithmetic Error");
}
```

This gives a **compile-time error** because `Exception` already catches `ArithmeticException`.

---


### Nested `try-catch` in Java

A **nested try-catch** means placing one `try-catch` block inside another `try` or `catch` block.

It is useful when different parts of the code need separate exception handling.

### Simple Example

```java
public class Test {
    public static void main(String[] args) {

        try {

            System.out.println("Outer try starts");

            try {
                int x = 10 / 0;
            }
            catch (ArithmeticException e) {
                System.out.println("Inner catch: Divide by zero");
            }

            int arr[] = {10, 20, 30};
            System.out.println(arr[5]);

        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Outer catch: Invalid array index");
        }

        System.out.println("Program continues...");
    }
}
```

### Output

```text
Outer try starts
Inner catch: Divide by zero
Outer catch: Invalid array index
Program continues...
```

---

### How Execution Flows

```text
Outer Try
    |
    +--> Inner Try
    |       |
    |       +--> ArithmeticException
    |               |
    |         Inner Catch Handles It
    |
    +--> Array Exception Occurs
            |
      Outer Catch Handles It
```

---

## Example 2: Inner Exception Not Handled

```java
public class Test {
    public static void main(String[] args) {

        try {

            try {
                int x = 10 / 0;
            }

            catch (NullPointerException e) {
                System.out.println("Null Error");
            }

        }

        catch (ArithmeticException e) {
            System.out.println("Outer catch handled ArithmeticException");
        }
    }
}
```

### Output

```text
Outer catch handled ArithmeticException
```

### Why?

The inner catch can only handle `NullPointerException`.

```java
catch (NullPointerException e)
```

But the exception generated is:

```java
ArithmeticException
```

So Java looks outside and finds a matching catch in the outer block.

---

## Example 3: Nested Try Inside Catch

```java
public class Test {
    public static void main(String[] args) {

        try {

            int x = 10 / 0;

        }
        catch (ArithmeticException e) {

            System.out.println("First exception handled");

            try {
                int arr[] = {1, 2, 3};
                System.out.println(arr[10]);
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Second exception handled");
            }
        }
    }
}
```

### Output

```text
First exception handled
Second exception handled
```

---

### Real-Life Analogy

Imagine a university office:

```text
Dean Office (Outer Try)
        |
        +--> Examination Cell (Inner Try)
                |
                +--> Handles Exam Issues
        |
        +--> If another issue occurs
                |
          Dean Office handles it
```

The inner `try-catch` handles local problems. If it cannot handle them, the exception moves to the outer `try-catch`.

### Key Point

A nested `try-catch` creates a hierarchy:

```text
Inner try-catch
       ↓
Outer try-catch
       ↓
JVM
```

An exception is first searched for in the  **nearest catch block** . If not found, Java moves outward until a matching catch is found.
