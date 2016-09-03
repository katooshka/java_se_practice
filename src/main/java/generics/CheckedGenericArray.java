package generics;

import java.lang.reflect.Array;

public class CheckedGenericArray<E> {
    private E[] array;

    @SuppressWarnings("unchecked")
    public CheckedGenericArray(Class<E> eClass, int arrayLength) {
        this.array = (E[])Array.newInstance(eClass, arrayLength);
    }

    public E get(int index) {
        return array[index];
    }

    public int size() {
        return array.length;
    }
}
