package mipt.task1.tunnels;

import mipt.task1.ports.Loading;
import mipt.task1.ports.BananaPort;
import mipt.task1.ports.BreadPort;
import mipt.task1.ports.ClothesPort;
import mipt.task1.ships.BananaShip;
import mipt.task1.ships.BreadShip;
import mipt.task1.ships.ClothesShip;
import mipt.task1.ships.Ship;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tunnel implements Go {
    // очередь
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    // причалы
    Loading breadPort = new BreadPort();
    Loading bananaPort = new BananaPort();
    Loading clothesPort = new ClothesPort();
    // пройти через причал
    @Override
    public synchronized void go(Ship ship) {
       executorService.execute(() -> {

           System.err.println(ship.getCargo() + " вошел в туннель");
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.err.println(ship.getCargo() + " покинул в туннель");
           // add the ship in turn for loading
           if (ship instanceof BreadShip) breadPort.load(ship);
           if (ship instanceof BananaShip) bananaPort.load(ship);
           if (ship instanceof ClothesShip) clothesPort.load(ship);
       });
    }
}
