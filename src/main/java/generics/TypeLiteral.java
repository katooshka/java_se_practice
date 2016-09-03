package generics;

public abstract class TypeLiteral<T> {
    public String getGenericType() {
        return getClass().getGenericSuperclass().toString();
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof TypeLiteral && ((TypeLiteral) object).getGenericType().equals(getGenericType());
    }

    @Override
    public int hashCode() {
        return getGenericType().hashCode();
    }
}
