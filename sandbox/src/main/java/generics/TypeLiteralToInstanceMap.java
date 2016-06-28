package generics;

import java.util.HashMap;
import java.util.Map;

public class TypeLiteralToInstanceMap {
    private final Map<TypeLiteral<?>, Object> map = new HashMap<>();

    public <T> void put(TypeLiteral<T> key, T value) {
        map.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(TypeLiteral<T> key) {
        return (T) map.get(key);
    }
}
