package mipt.task1.ports;

import mipt.task1.ships.Ship;

public class ClothesPort extends Port {
    public ClothesPort() {
        super("Одежда");
    }

    @Override
    public synchronized void load(Ship ship) {
        super.load(ship);
    }
}
