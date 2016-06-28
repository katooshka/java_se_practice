package generics;

import java.util.HashMap;
import java.util.Map;

public class ClassToInstanceMap {
    private final Map<Class<?>, Object> map = new HashMap<>();

    public <T> void put(Class<T> key, T value) {
        map.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> tClass) {
        return (T) map.get(tClass);
    }
}
