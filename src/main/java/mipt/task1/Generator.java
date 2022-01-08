package mipt.task1;

import mipt.task1.ships.BananaShip;
import mipt.task1.ships.BreadShip;
import mipt.task1.ships.ClothesShip;
import mipt.task1.ships.Ship;
import mipt.task1.tunnels.Tunnel;

import java.util.Random;

public class Generator extends Thread{
    Random random = new Random();
    Tunnel tunnel;
    int i = 1;
    public Generator(Tunnel tunnel) {
        this.tunnel = tunnel;
    }

    @Override
    public void run() {
       while (true) {
           Ship ship = null;
           int capacity = 10;
           int r = random.nextInt(100);
           if (r > 80) capacity = 100;
           if (r < 20) capacity = 50;
           r= random.nextInt(3);
           if (r == 0) ship = new BreadShip(tunnel, i, capacity);
           if (r == 1) ship = new BananaShip(tunnel, i, capacity);
           if (r == 2) ship = new ClothesShip(tunnel, i, capacity);
           new Thread(ship).start();
           i++;
           try {
               Thread.sleep(random.nextInt(1000));
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
}
