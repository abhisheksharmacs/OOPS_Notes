### Encapsulation in Java

**Encapsulation** means **binding data (variables) and methods (functions) together into a single unit (class)** and **restricting direct access to the data**.

Think of it like a **capsule** that hides its internal details and only exposes what is necessary.

### Real-Life Example

Consider an **ATM machine**:

* You can deposit money.
* You can withdraw money.
* You can check balance.

But you **cannot directly access or modify** the bank's internal database.

The ATM hides the internal data and provides controlled access through methods.

This is the idea behind encapsulation.

---

### Java Example

```java
class BankAccount {

    private double balance;   // hidden data

    public void deposit(double amount) {
        balance = balance + amount;
    }

    public void showBalance() {
        System.out.println("Balance = " + balance);
    }
}

public class Main {
    public static void main(String[] args) {

        BankAccount acc = new BankAccount();

        // acc.balance = 1000;  // Error (private)

        acc.deposit(1000);
        acc.showBalance();
    }
}
```

**Output:**

```text
Balance = 1000.0
```

Here:

* `balance` is **private** → cannot be accessed directly from outside the class.
* `deposit()` and `showBalance()` provide controlled access.

---

### Using Getters and Setters

A common way to implement encapsulation is through **getter** and **setter** methods.

```java
class Student {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

Usage:

```java
Student s = new Student();

s.setName("Rahul");
System.out.println(s.getName());
```

---

### Why Encapsulation?

1. **Data Hiding**

   * Protects data from unauthorized access.
2. **Better Control**

   * You can validate data before storing it.
3. **Security**

   * Prevents accidental modification of important data.
4. **Maintainability**

   * Internal implementation can change without affecting other classes.

---
