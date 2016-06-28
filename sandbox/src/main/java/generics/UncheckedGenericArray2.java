package generics;

public class UncheckedGenericArray2<E> {
    private E[] array;

    @SuppressWarnings("unchecked")
    public UncheckedGenericArray2(int arrayLength) {
        this.array = (E[]) new Object[arrayLength];
    }

    public E get(int index) {
        return array[index];
    }

    public int size() {
        return array.length;
    }
}
