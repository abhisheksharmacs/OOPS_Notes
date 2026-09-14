### User-Defined Exception in Java

A **user-defined exception** is an exception that **we create ourselves** according to the requirement of our application.

For example, suppose a college system has a rule:

> Student marks cannot be greater than 100.

Java does not have a specific `InvalidMarksException`, so we can create one.

### Step 1: Create our exception class

```java
class InvalidMarksException extends Exception {

    InvalidMarksException(String message) {
        super(message);
    }
}
```

Here:

* `InvalidMarksException` → our own exception
* `extends Exception` → makes it a **checked exception**
* `super(message)` → sends the message to the parent `Exception` class

### Step 2: Use the exception

```java
class Student {

    static void checkMarks(int marks) throws InvalidMarksException {

        if (marks > 100) {
            throw new InvalidMarksException("Marks cannot be greater than 100");
        }

        System.out.println("Valid marks");
    }

    public static void main(String[] args) {

        try {
            checkMarks(120);
        }
        catch (InvalidMarksException e) {
            System.out.println(e.getMessage());
        }
    }
}
```

### Output

```text
Marks cannot be greater than 100
```

### What actually happens?

The flow is:

```text
checkMarks(120)
       ↓
marks > 100 ?
       ↓
     YES
       ↓
throw new InvalidMarksException(...)
       ↓
catch (InvalidMarksException e)
       ↓
e.getMessage()
```

Notice the three important things:

```java
class InvalidMarksException extends Exception
```

**Create** the exception.

```java
throw new InvalidMarksException("Marks cannot be greater than 100");
```

**Throw** the exception.

```java
catch (InvalidMarksException e)
```

**Handle** the exception.

---

### User-defined checked vs unchecked exception

We can create both.

**Checked user-defined exception:**

```java
class MyException extends Exception {
}
```

**Unchecked user-defined exception:**

```java
class MyException extends RuntimeException {
}
```

So the choice of parent class determines whether our custom exception is  **checked or unchecked** .

**Simple formula:**

```text
User-defined Exception
        |
        +-- extends Exception
        |       → Checked
        |
        +-- extends RuntimeException
                → Unchecked
```

A very practical next step is to understand  **`throw` vs `throws` vs `try-catch` using one user-defined exception** , because these three are often confused.
