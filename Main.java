package src.com.jeremyward;
/**
 * @author Jeremy Ward
 * @date 10/12/21
 */
import java.io.File;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        // Static files for input
        File countFile = new File("RiverPollutionDataCounts.txt");
        File dataFile = new File("RiverPollutionData.txt");

        // Create a new manager which we will use to call all future classes
        PollutionDataManager pdm = new PollutionDataManager(countFile, dataFile);
        System.out.println(pdm.generateArsenicReport());
    }
}
