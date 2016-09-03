package proxy;

public class Horse implements Animal {

    @Override
    public void printAnimalName() {
        System.out.println("This is a horse");
    }

    @Override
    public void printAnimalSpeed(int speed) {
        System.out.print(speed + "km/h");
    }
}
