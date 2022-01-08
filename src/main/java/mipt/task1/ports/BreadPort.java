package mipt.task1.ports;

import mipt.task1.ships.Ship;

public class BreadPort extends Port {

    public BreadPort() {
        super("Хлеб");
    }

    @Override
    public synchronized void load(Ship ship) {
        super.load(ship);
    }
}
