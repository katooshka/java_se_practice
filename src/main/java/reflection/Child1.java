package reflection;

public class Child1 extends Base<String> {
    public final int publicFinalIntField = 2;
    private int privateIntField = 3;
    public static int publicStaticIntField = 4;

    public int getPrivateIntField() {
        return privateIntField;
    }
}
