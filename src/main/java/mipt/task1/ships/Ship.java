package mipt.task1.ships;

import mipt.task1.tunnels.Go;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public  abstract class Ship implements Runnable{
    final private int num;
    final private Go tunnel;
    final private int capacity;


    public Ship(Go tunnel, int num, int capacity) {
        this.tunnel = tunnel;
        this.num=num;
        this.capacity=capacity;
        System.err.println(getCargo() + " создан");

    }

    public String getCargo(){
       return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " ";
    }

    public int getCapacity() {
        return capacity;
    }


    public int getNum() {
        return num;
    }

    @Override
    public void run() {
        tunnel.go(this);
    }
}
