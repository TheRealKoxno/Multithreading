package mipt.org;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class RingProcessor {
    final Logger logger = Logger.getLogger("Ring");

    int size;
    int cordId;
    int packets;
    List<Node> nodes = new ArrayList<>();
    final List<DataPackage> packages;

    public RingProcessor(int size, int packets, List<DataPackage> packages, File file) {
        this.size = size;
        this.packets = packets;
        this.packages = packages;
        Random random = new Random();
        cordId=random.nextInt(size);
        System.out.printf("cord %d\n",cordId);
        init(file);

    }
    public void init(File file){
        Node first =new Node(cordId,0,null,size,packets,packages,logger);
        nodes.add(first);
        Node prev = first;

        for(int i=1;i<size;i++){
            Node node =new Node(cordId,i,null,size,packets,packages,logger);
            nodes.add(node);
            prev.setNextNode(node);
            prev = node;

        }
        prev.setNextNode(first);


        FileHandler fh;
        try {
            fh = new FileHandler(file.getAbsolutePath());
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void startProcessing(){
       List<Thread> threads = new ArrayList<>();
       for(Node node:nodes){
           Thread thread = new Thread(node);
           threads.add(thread);
           thread.start();
       }
       for(Thread thread:threads) {
           try {
               thread.join();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }

    public Logger getLogger() {
        return logger;
    }
}
