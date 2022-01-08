package mipt.task1.ships;

import mipt.task1.tunnels.Go;

public class BananaShip extends Ship {
    public BananaShip(Go tunnel, int num, int capacity) {
        super(tunnel, num, capacity);

    }

    @Override
    public String getCargo() {
        return super.getCargo() +
                String.format("Корабль %d с бананами %d шт.", getNum(), getCapacity());
    }
}
