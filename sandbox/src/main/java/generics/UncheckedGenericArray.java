package generics;

public class UncheckedGenericArray<E> {
    private Object[] array;

    public UncheckedGenericArray(int arrayLength) {
        this.array = new Object[arrayLength];
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) array[index];
    }

    public int size() {
        return array.length;
    }
}
