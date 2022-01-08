package mipt.org;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;



public class Main {
    public static void main(String[] args){

        File file = new File("work.txt");
        List<DataPackage> packageList = new ArrayList<>();
        RingProcessor ringProcessor = new RingProcessor(10, 3, packageList, file);
        ringProcessor.startProcessing();
        System.out.println(packageList.size());
        for(DataPackage data: packageList){
           System.out.printf("data %d node count %d time  %d avg %d buff time %d buffs %d\n", data.getId(), data.getCount(),
                   data.getStopTime() - data.getStartTime(),(data.getStopTime() - data.getStartTime())/data.getCount(),
                   data.getDiffBuffer(), data.getCountBuffer());
        }
        LongSummaryStatistics stat = packageList.stream().mapToLong(data -> (data.getStopTime() - data.getStartTime())).summaryStatistics();
        System.out.println(stat);
        LongSummaryStatistics buff = packageList.stream().mapToLong(DataPackage::getDiffBuffer).summaryStatistics();
        System.out.println(buff);
пше
    }
}
