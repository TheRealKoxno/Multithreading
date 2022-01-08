package mipt.org;

public interface Send {
    void send(DataPackage dataPackage);
    void  receive(DataPackage dataPackage) throws InterruptedException;
}
