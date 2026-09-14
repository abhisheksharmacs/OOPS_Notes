class Student {
    static String college = "KIET";
    String name;
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student();
        s1.name = "John";
        System.out.println(s1.college + "  " + s1.name);

        s1.college = "IIT";
        s1.name = "Abhishek";

        s2.name = "Smith";
        System.out.println(s2.college + "  " + s2.name);
    }
}