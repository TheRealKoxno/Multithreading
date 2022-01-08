package mipt.task1.ships;

import mipt.task1.tunnels.Go;

public class ClothesShip extends Ship {


    public ClothesShip(Go tunnel, int num, int capacity) {
        super(tunnel, num, capacity);
    }

    @Override
    public String getCargo() {
        return super.getCargo() + String.format("Корабль %d с одеждой %d шт.", getNum(), getCapacity());
    }
}
