package reflection;

import collections.guava.transforms.Post;
import org.junit.Test;
import org.mockito.AdditionalMatchers;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;

public class ReflectionTest {

    @Test
    public void classObjects_ShouldBeSameIffClassesAreSame() throws ClassNotFoundException {
        Class booleanClass = boolean.class;
        Class postClass = Class.forName("collections.guava.transforms.Post");
        Class stringClass = String.class;
        Class secondStringClass = "QWERTY".getClass();

        assertSame(stringClass, secondStringClass);
        assertNotEquals(booleanClass, postClass);
        assertNotEquals(stringClass, postClass);
        assertNotEquals(stringClass, booleanClass);
    }

    @Test
    public void primitiveClass_ShouldNotBeEqualToItsWrapperClass() {
        assertNotEquals(int.class, Integer.class);
    }

    @Test
    public void className_ShouldContainPackage() {
        assertEquals("java.lang.String", "sd".getClass().getName());
    }

    @Test
    public void arrayClassName_ShouldStartWithSquareBracketAndEndWithSemicolon() {
        String[] stringArray = new String[3];
        assertEquals("[Ljava.lang.String;", stringArray.getClass().getName());
    }

    @Test
    public void primitiveArrayClassName_ShouldStartWithSquareBracketAndContainPrimitiveShortcut() {
        int[] intArray = new int[5];
        assertEquals("[I", intArray.getClass().getName());
    }

    @Test
    public void arrayOfArraysClassName_ShouldContainTwoSquareBracketsAndOneSemicolon() {
        String[][] stringMatrix = new String[3][3];
        assertEquals("[[Ljava.lang.String;", stringMatrix.getClass().getName());
    }

    @Test
    public void getClass_ShouldNotDependOnReferenceType() {
        assertEquals("java.util.ArrayList", ((List<String>) new ArrayList<String>()).getClass().getName());
    }

    @Test
    public void stringAndArraysClasses_ShouldNotBeEqual() {
        String[] strings = new String[5];
        Integer[] integers = new Integer[5];
        assertNotEquals(strings.getClass(), integers.getClass());
    }

    @Test
    public void genericClass_ShouldNotDependOnItsTypeAgrument() {
        List<String> strings = new ArrayList<>();
        strings.add("dd");
        List<Integer> integers = new ArrayList<>();
        integers.add(5);
        assertEquals(strings.getClass(), integers.getClass());
    }

    @Test
    public void testClassModifiers() throws ClassNotFoundException {
        assertTrue(Modifier.isPublic(Integer.class.getModifiers()));
        assertTrue(Modifier.isFinal(Integer.class.getModifiers()));

        assertTrue(Modifier.isPublic(Post.class.getModifiers()));
        assertFalse(Modifier.isFinal(Post.class.getModifiers()));
    }

    @Test
    public void primitiveClasses_ShouldNotBeEqualToTheirWrapperClasses() {
        assertEquals(int.class, Integer.TYPE);
        assertNotEquals(Integer.class, Integer.TYPE);
        assertEquals(double.class, Double.TYPE);
        assertNotEquals(Double.class, Double.TYPE);
    }

    @Test
    public void getSuperclass_ShouldNotDependOnGenericTypes() {
        assertEquals(Base.class.getSuperclass(), Object.class);
        assertEquals(Child1.class.getSuperclass(), Base.class);
        assertEquals(Child1.class.getSuperclass(), Child2.class.getSuperclass());
        assertEquals(Child3.class.getSuperclass(), Base.class);
    }

    @Test
    public void getGenericSuperclass_ShouldDependOnGenericTypes() {
        assertEquals("reflection.Child1", Child1.class.getName());
        assertEquals("reflection.Base<java.lang.String>", Child1.class.getGenericSuperclass().getTypeName());
        ParameterizedType child1GenericSuperclass = (ParameterizedType) (Child1.class.getGenericSuperclass());
        assertEquals("java.lang.String", child1GenericSuperclass.getActualTypeArguments()[0].getTypeName());

        assertEquals("reflection.Child4", Child4.class.getName());
        assertEquals("reflection.Base<E>", Child4.class.getGenericSuperclass().getTypeName());
        ParameterizedType child4GenericSuperclass = (ParameterizedType) (Child4.class.getGenericSuperclass());
        assertEquals("E", child4GenericSuperclass.getActualTypeArguments()[0].getTypeName());
    }

    @Test
    public void changeValuesViaReflection_classObjectFieldValues_ShouldBeChanged() throws Exception {
        Field publicStaticIntField = Child1.class.getField("publicStaticIntField");
        assertNotEquals(200, Child1.publicStaticIntField);
        publicStaticIntField.set(null, 200);
        assertEquals(200, Child1.publicStaticIntField);

        Child1 testChild = new Child1();
        Field privateIntField = Child1.class.getDeclaredField("privateIntField");
        privateIntField.setAccessible(true);
        privateIntField.set(testChild, 500);
        assertEquals(500, testChild.getPrivateIntField());
    }

    @Test
    public void finalField_CouldBeChangedButWithoutGuaranties() throws Exception {
        Child1 testChild = new Child1();
        Field field = Child1.class.getDeclaredField("publicFinalIntField");
        assertEquals(2, testChild.publicFinalIntField);
        field.setAccessible(true);
        field.set(testChild, 100);
    }

    @Test
    public void testMethodCall() throws Exception {
        Method method = Base.class.getMethod("printSomething", String.class);
        Base base = mock(Base.class);
        method.invoke(base, "kate");
        verify(base).printSomething("kate");

        verify(base, never()).printSomething(not(eq("kate")));
        verify(base, never()).printSomething2();
    }

    @Test
    public void testConstructorCall() throws Exception {
        Constructor<Foo> constructor = Foo.class.getDeclaredConstructor(int.class);
        Foo instance = constructor.newInstance(5);
        assertEquals(Foo.class, instance.getClass());

        Child1 instance2 = Child1.class.getConstructor().newInstance();
        assertEquals(Child1.class, instance2.getClass());
    }

    @Test
    public void enumSuperClasses_ControlSuperClassesGiven_ShouldBeEqual() {
        assertEquals(Enum.class, DayOfWeek.class.getSuperclass());
        assertEquals(Object.class, DayOfWeek.class.getSuperclass().getSuperclass());
        assertTrue(DayOfWeek.class.isEnum());
        assertTrue(Modifier.isFinal(DayOfWeek.class.getModifiers()));
    }
}
