package mipt.task1;


import mipt.task1.tunnels.Tunnel;


public class Main {
    public static void main(String[] args) {

        Tunnel tunnel = new Tunnel();
        Generator generator = new Generator(tunnel);
        generator.start();
    }
}
