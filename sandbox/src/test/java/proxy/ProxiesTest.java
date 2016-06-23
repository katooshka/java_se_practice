package proxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintStream;
import java.util.NavigableMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProxiesTest {
    @Mock
    private Horse horse;

    @Mock
    private PrintStream stream;

    @Test
    public void logProxyTest() {
        Animal proxy = LoggingProxy.newInstance(Animal.class, horse, stream);

        horse.printAnimalSpeed(60);
        verify(horse).printAnimalSpeed(60);
        verifyZeroInteractions(stream);

        reset(horse, stream);

        proxy.printAnimalSpeed(60);
        verify(horse).printAnimalSpeed(60);
        verify(stream).println("Method invoked: printAnimalSpeed");
    }

    @Test
    public void annotatedLogProxyTest() {
        Animal proxy = AnnotatedLogProxy.newInstance(Animal.class, horse, stream);

        proxy.printAnimalName();
        verifyZeroInteractions(stream);

        proxy.printAnimalSpeed(60);
        verify(stream).println("Method invoked: printAnimalSpeed");
    }

    @Test
    public void NavigableMapProxyTest() {
        NavigableMap<Integer, String> dummyMap = NavigableMapInstance.getInstance();
        NavigableMap<Integer, String> proxyMap = NavigableMapProxy.newInstance();

        assertEquals(dummyMap.size(), proxyMap.size());
        assertEquals(dummyMap.entrySet(), proxyMap.entrySet());
        assertEquals(dummyMap.isEmpty(), proxyMap.isEmpty());
        proxyMap.clear();
    }
}
