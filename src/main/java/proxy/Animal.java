package proxy;

public interface Animal {
    void printAnimalName();
    @Log
    void printAnimalSpeed(int speed);
}
