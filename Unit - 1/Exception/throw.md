### What is `throw` in Java?

The `throw` keyword is used to  **explicitly create and throw an exception** .

When Java executes a `throw` statement, the normal flow of the program stops immediately, and control is transferred to the nearest matching `catch` block.

### Syntax

```java
throw new ExceptionType("Error Message");
```

Example:

```java
throw new ArithmeticException("Division by zero is not allowed");
```

---

## Example 1: Throwing a Built-in Exception

```java
class Test {
    public static void main(String[] args) {

        int age = 15;

        if(age < 18) {
            throw new ArithmeticException(
                "Not eligible to vote");
        }

        System.out.println("Eligible to vote");
    }
}
```

Output:

```text
Exception in thread "main"
java.lang.ArithmeticException: Not eligible to vote
```

Since no `catch` block exists, the exception reaches the JVM.

---

## Example 2: Using `throw` with `try-catch`

```java
class Test {
    public static void main(String[] args) {

        try {

            int age = 15;

            if(age < 18) {
                throw new ArithmeticException(
                    "Not eligible to vote");
            }

            System.out.println("Eligible");

        } catch(ArithmeticException e) {

            System.out.println(e.getMessage());

        }

        System.out.println("Program continues...");
    }
}
```

Output:

```text
Not eligible to vote
Program continues...
```

---

## Flow of Execution

```text
try block
    |
condition true
    |
throw exception
    |
catch block
    |
remaining program
```

When `throw` executes:

```java
throw new ArithmeticException("Error");
```

the next statement is  **not executed** .

```java
throw new ArithmeticException("Error");

System.out.println("Hello"); // Never executes
```

---

## Example 3: Validation Using `throw`

A very common use case is validating user input.

```java
class Bank {

    static void withdraw(int balance, int amount) {

        if(amount > balance) {
            throw new ArithmeticException(
                "Insufficient Balance");
        }

        System.out.println("Withdrawal successful");
    }

    public static void main(String[] args) {

        withdraw(5000, 7000);
    }
}
```

Output:

```text
Exception in thread "main"
java.lang.ArithmeticException:
Insufficient Balance
```

---
