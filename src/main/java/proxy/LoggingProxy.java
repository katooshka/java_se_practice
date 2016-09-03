package proxy;

import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;

import static java.lang.reflect.Proxy.newProxyInstance;

public class LoggingProxy {

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<T> objectInterface, T object, PrintStream printStream) {
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            printStream.println("Method invoked: " + method.getName());
            return method.invoke(object, args);
        };
        return (T) newProxyInstance(
                object.getClass().getClassLoader(),
                new Class[]{objectInterface},
                invocationHandler);
    }


}

