package mipt.org;

public class DataPackage {
    private static int generator = 100;
    // id of package
    private final int id;
    // data
    private final String data;
    private int dstNode;
    private final long  startTime;
    private  long  stopTime;
    // counter of node through which passed
    private int count = 0;
    private int countBuffer = 0;
    private long diffBuffer = 0;
    private long bufferTime = 0;


    public DataPackage(String data, int dstNode) {
        this.data = data;
        this.id = generator;
        generator++;
        this.startTime = System.nanoTime() - 100;
        this.dstNode = dstNode;
    }
    public void stop() {
        this.stopTime =  System.nanoTime();
    }

    public void inc() {
        count++;
        addToBuffer();
    }
    public void  addToBuffer() {
        countBuffer++;
        bufferTime = System.nanoTime();
    }
    public void removeFromBuffer() {
        diffBuffer = System.nanoTime() - bufferTime;
    }

    public static int getGenerator() {
        return generator;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public int getDstNode() {
        return dstNode;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getStopTime() {
        return stopTime;
    }

    public int getCount() {
        return count;
    }

    public int getCountBuffer() {
        return countBuffer;
    }

    public long getDiffBuffer() {
        return diffBuffer;
    }

    public long getBufferTime() {
        return bufferTime;
    }

    public void setDstNode(int dstNode) {
        this.dstNode = dstNode;
    }
}
