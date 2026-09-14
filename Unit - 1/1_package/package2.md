If your **`Main` class is inside `packageA`** and you want to access a class from  **`packageB`** , you need to import that class.

### Folder Structure

```text
Project/
│
├── packageA/
│   └── Main.java
│
└── packageB/
    └── Student.java
```

---

### packageB/Student.java

```java
package packageB;

public class Student {

    public void display() {
        System.out.println("Hello from packageB");
    }
}
```

---

### packageA/Main.java

```java
package packageA;

import packageB.Student;

public class Main {

    public static void main(String[] args) {

        Student s = new Student();
        s.display();
    }
}
```

---

### Compile from the Project Directory

```bash
javac packageA/Main.java packageB/Student.java
```

### Run

```bash
java packageA.Main
```

Output:

```text
Hello from packageB
```

---

## Without Import

You can also use the fully qualified class name:

```java
package packageA;

public class Main {

    public static void main(String[] args) {

        packageB.Student s = new packageB.Student();
        s.display();
    }
}
```

---

## Important Access Modifier Rules

Suppose `Student` has these variables:

```java
package packageB;

public class Student {
    public int a = 10;
    protected int b = 20;
    int c = 30;          // default
    private int d = 40;
}
```

From `packageA.Main`:

```java
Student s = new Student();

System.out.println(s.a); // ✔ Allowed
System.out.println(s.b); // ✘ Not Allowed
System.out.println(s.c); // ✘ Not Allowed
System.out.println(s.d); // ✘ Not Allowed
```

Only **public** members are directly accessible across packages (unless inheritance is involved for `protected`).

---

### Cross-Package Inheritance Example

```java
package packageA;

import packageB.Student;

public class Main extends Student {

    public static void main(String[] args) {

        Main m = new Main();
        System.out.println(m.b); // ✔ protected accessible through inheritance
    }
}
```
