package mipt.task1.ships;

import mipt.task1.tunnels.Go;

public class BreadShip extends Ship {


    public BreadShip(Go tunnel, int num, int capacity) {
        super(tunnel, num, capacity);
    }

    @Override
    public String getCargo() {
        return
                super.getCargo() + String.format("Корабль %d с хлебом %d шт.", getNum(), getCapacity());
    }
}
