package reflection;

public class Base<T> {
    public String field;

    public void printSomething(String name) {
        System.out.println("Hello " + name);
    }

    public void printSomething2() {
        System.out.println("Hello world");
    }
}
