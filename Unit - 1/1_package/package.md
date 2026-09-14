In Java, a package is a way to organize related classes. If you are using the  **command line** , follow these steps.

## 1. Create the Package Structure

Suppose you want a package named `mypackage`.

Create the following folder structure:

```text
Project/
│
├── mypackage/
│   └── Student.java
│
└── Main.java
```

---

## 2. Create the Class Inside the Package

### Student.java

```java
package mypackage;

public class Student {

    public void display() {
        System.out.println("Student class inside package");
    }
}
```

* The first line must be the package declaration.
* The file must be stored inside the `mypackage` folder.

---

## 3. Access the Class from Another Class

### Main.java

```java
import mypackage.Student;

public class Main {

    public static void main(String[] args) {

        Student s = new Student();
        s.display();
    }
}
```

---

## 4. Compile Using Command Line

Open terminal in the `Project` directory and run:

```bash
javac mypackage/Student.java Main.java
```

This creates:

```text
Project/
│
├── mypackage/
│   ├── Student.java
│   └── Student.class
│
├── Main.java
└── Main.class
```

---

## 5. Run the Program

```bash
java Main
```

Output:

```text
Student class inside package
```

---

# Access Without Import

Instead of importing, you can use the fully qualified name.

### Main.java

```java
public class Main {

    public static void main(String[] args) {

        mypackage.Student s = new mypackage.Student();
        s.display();
    }
}
```

Compile and run:

```bash
javac mypackage/Student.java Main.java
java Main
```

---

# Example with Multiple Packages

```text
Project/
│
├── packageA/
│   └── One.java
│
├── packageB/
│   └── Two.java
│
└── Main.java
```

### packageA/One.java

```java
package packageA;

public class One {
    public void show() {
        System.out.println("Class One");
    }
}
```

### packageB/Two.java

```java
package packageB;

public class Two {
    public void show() {
        System.out.println("Class Two");
    }
}
```

### Main.java

```java
import packageA.One;
import packageB.Two;

public class Main {
    public static void main(String[] args) {

        One obj1 = new One();
        Two obj2 = new Two();

        obj1.show();
        obj2.show();
    }
}
```

Compile:

```bash
javac packageA/One.java packageB/Two.java Main.java
```

Run:

```bash
java Main
```

Output:

```text
Class One
Class Two
```

### Quick Rule to Remember

1. **Folder name = Package name**
2. **First line of the class = `package packageName;`**
3. **Use `import packageName.ClassName;` to access it**
4. **Compile from the project root directory**
