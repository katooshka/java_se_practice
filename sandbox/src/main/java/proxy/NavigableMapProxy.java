package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.NavigableMap;

import static java.lang.reflect.Proxy.newProxyInstance;

public class NavigableMapProxy {
    @SuppressWarnings("unchecked")
    public static NavigableMap<Integer, String> newInstance() {
        return (NavigableMap<Integer, String>) newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{NavigableMap.class},
                new NavigableMapInvocationHandler());
    }

    private static class NavigableMapInvocationHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            Class<?> type = method.getReturnType();
            switch (type.toString()) {
                case "byte":
                    return (byte) 0;
                case "char":
                    return (char) 0;
                case "boolean":
                    return false;
                case "short":
                    return (short) 0;
                case "int":
                    return 0;
                case "long":
                    return (long) 0;
                case "float":
                    return (float) 0;
                case "double":
                    return (double) 0;
                default:
                    return null;
            }
        }
    }
}

