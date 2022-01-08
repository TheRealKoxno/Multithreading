package mipt.task1.ports;

import mipt.task1.ships.Ship;

public class BananaPort extends Port {
    public BananaPort() {
        super("Бананы");
    }

    @Override
    public synchronized void load(Ship ship) {
        super.load(ship);

    }
}
