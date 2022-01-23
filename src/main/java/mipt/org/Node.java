package mipt.org;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

public class Node implements Runnable,Send{

    Logger logger;
    private final int cordId;
    private final int nodeId;
    private  Node nextNode;
    final List<DataPackage> packages;
    List<DataPackage> initData = new ArrayList<>();
    private final int size;
    private final int packetCount;
    public Node(int cordId, int nodeId, Node nextNode, int size, int packetCount, List<DataPackage> packages, Logger logger) {
        this.cordId = cordId;
        this.nodeId = nodeId;
        this.nextNode = nextNode;
        this.size = size;
        this.packages = packages;
        this.packetCount=packetCount;
        this.logger = logger;
        generate(packetCount);
    }
    private void generate(int packetCount){
        Random random = new Random();
      while (initData.size() < packetCount){
           int r = random.nextInt(size);
           if (r==nodeId)continue;
           if (initData.stream().anyMatch(p -> p.getDstNode() == r)) continue;
           DataPackage dataPackage = new DataPackage(Integer.toString(r), r);
           initData.add(dataPackage);

      }
    }

    ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
    ExecutorService sender = Executors.newSingleThreadExecutor();

    @Override
    public synchronized void send(DataPackage dataPackage) {

        sender.execute(() -> {
            logger.info(String.format("%d Node %d send packet %d to node %d\n", System.nanoTime(), nodeId,
                    dataPackage.getId(), dataPackage.getDstNode()));
            dataPackage.removeFromBuffer();
            nextNode.receive(dataPackage);
        });


    }

    @Override
    public synchronized   void  receive(DataPackage dataPackage){
        pool.execute(() -> processing(dataPackage));
    }

    public  void processing(DataPackage dataPackage) {

        logger.info(String.format("%d Node %d receive packet %d to node %d\n",System.nanoTime(), nodeId,
                    dataPackage.getId(), dataPackage.getDstNode()));
            dataPackage.inc();
            pool.execute(() -> {
                logger.info(String.format("%d processing packet %d node %d\n",System.nanoTime(),
                        dataPackage.getId(),nodeId));
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (nodeId == dataPackage.getDstNode()) {

                    if (nodeId != cordId) {
                        logger.info(String.format("%d node %d resend %d to cord\n",System.nanoTime(), nodeId, dataPackage.getId()));
                        dataPackage.setDstNode(cordId);
                        send(dataPackage);
                    } else {
                        dataPackage.stop();
                        packages.add(dataPackage);
                    }
                } else {

                    send(dataPackage);
                }
            });
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100L *nodeId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(String.format("%d Node %d start send\n",System.nanoTime(),nodeId));
        for(DataPackage data:initData){
            send(data);
        }

            synchronized (packages){
               while (packages.size()<size*packetCount) {
                   Thread.yield();

               }
            }
        shutdown();
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public void shutdown(){
        pool.shutdownNow();
        sender.shutdown();
        logger.info(String.format("%d Node %d shutdown\n",System.nanoTime(),nodeId));

    }
}
