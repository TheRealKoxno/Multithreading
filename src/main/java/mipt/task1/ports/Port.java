package mipt.task1.ports;

import mipt.task1.ships.Ship;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Port implements Loading {
    private final String name;

    public Port(String name) {
        this.name = name;
    }

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    @Override
    public synchronized void load(Ship ship) {
          executorService.execute(() -> {
              System.err.println(ship.getCargo() + " пришел на причал " + name + " для погрузки");
              try {
                  Thread.sleep((ship.getCapacity()/10)* 1000L);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.err.println(ship.getCargo() + " покинул причал " + name);
          });
    }

}
