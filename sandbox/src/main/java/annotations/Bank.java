package annotations;

class Bank {

    private final Object lock = new Object();

    @GuardedBy("lock")
    private final int moneyAmount = 1000;

    @LogTimings
    int getMoneyAmount() {
        synchronized (lock) {
            return moneyAmount;
        }
    }
}

