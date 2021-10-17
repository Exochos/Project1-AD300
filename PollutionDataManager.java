package src.com.jeremyward;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class PollutionDataManager implements PollutionDataManagerInterface {

    private String[] riverNames;
    private PollutionData[][][] pollutionData;
    /**
     *
     * The main class we will be using for the program
     * @param countFile The count file which we will be using to count how many objects we will need.
     * @param dataFile The actual data which we will be reading into the objects we previously created
     *
     */

    public PollutionDataManager(File countFile, File dataFile) {

        // Here we open the count file and do stuff
        // Now with try catch!
        try {
            Scanner countScan = new Scanner(countFile);
            int riverCount = Integer.parseInt(countScan.nextLine());
            riverNames = new String[riverCount];
            pollutionData = new PollutionData[riverCount][12][];

            // Our loop to create the riverNames and populate the array
            for (int riverIndex = 0; riverIndex < riverCount; riverIndex++) {
                String countLine = countScan.nextLine();
                String[] countFields = countLine.split(",");
                riverNames[riverIndex] = countFields[0];

                for (int fieldIndex = 1; fieldIndex <= 12; fieldIndex++) {
                    int howBig = Integer.parseInt(countFields[fieldIndex]);
                    pollutionData[riverIndex][fieldIndex - 1] = new PollutionData[howBig];
                }
            }
        } catch (FileNotFoundException e) {
            riverNames = new String[0];
            e.printStackTrace();
        }

        try {
            Scanner dataScan = new Scanner(dataFile);
            dataScan.nextLine();
            for (int riverIndex = 0; riverIndex < pollutionData.length; riverIndex++) {
                for (int monthIndex = 0; monthIndex < pollutionData[riverIndex].length; monthIndex++) {
                    for (int readingIndex = 0; readingIndex < pollutionData[riverIndex][monthIndex].length; readingIndex++) {
                        String dataLine = dataScan.nextLine();
                        String[] dataFields = dataLine.split(",");
                        pollutionData[riverIndex][monthIndex][readingIndex] =
                                new PollutionData(riverIndex,
                                        Integer.parseInt(dataFields[1]),   // Month
                                        Integer.parseInt(dataFields[2]),   // Day
                                        Integer.parseInt(dataFields[3]),   // Arsenic
                                        Integer.parseInt(dataFields[4]),   // Lead
                                        Double.parseDouble(dataFields[5]), // Fertilizer
                                        Double.parseDouble(dataFields[6])  // Pesticide
                                );
                    }
                }
            }
        } catch (FileNotFoundException e) {
            riverNames = new String[0];
            e.printStackTrace();
        }
    }

    /**
     * retrieves the name of a river
     *
     * @param riverIndex the index of the river
     * @return the river name
     */
    @Override
    public String getRiverName(int riverIndex) {
        if (riverIndex > 0 && riverIndex < riverNames.length) {
            return riverNames[riverIndex];
        }
        else {
            return "Not A valid input";
        }
    }

    /**
     * retrieves the index associated with the specified river name (case-insensitive)
     *
     * @param riverName the name of the river
     * @return the associated river's index, or -1 if the river is not found
     */
    @Override
    public int findRiver(String riverName) {
        for (int i = 0; i < riverNames.length; i++) {
            if (riverNames[i].equals(riverName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * retrieves the count of rivers
     *
     * @return the number of rivers for which data is available
     */
    @Override
    public int getRiverCount() {
        return riverNames.length;
    }

    /**
     * retrieves the number of readings available for a specified river and month
     *
     * @param riverIndex  the index of the river
     * @param monthNumber the month number (not index!), 1-12
     * @return the number of data readings available
     */
    @Override
    public int getPollutionReadingCount(int riverIndex, int monthNumber) {
        return pollutionData[riverIndex][monthNumber-1].length;
    }

    /**
     * retrieves pollution data for a specified river, month, and day
     *
     * @param riverIndex  the index of the river
     * @param monthNumber the month number (not index!), 1-12
     * @param dayIndex    the day index (not date!)
     * @return the associated pollution data
     */
    @Override
    public PollutionData getPollutionData(int riverIndex, int monthNumber, int dayIndex) {
        return pollutionData[riverIndex][monthNumber - 1][dayIndex];
    }

    /**
     * generates a report showing each river's monthly average for arsenic, plus a yearly average
     *
     * @return arsenic report
     */
    @Override
    public String generateArsenicReport() {
        StringBuilder stringBuilder = new StringBuilder();
        String title = "ARSENIC REPORT\n\n";
        stringBuilder.append(title);
        stringBuilder.append(String.format("%-20s\t\t\t", "River"));
        for (int i = 1; i < 13; i++) {
            stringBuilder.append(String.format("%5s", i) + "\t");
        }
        stringBuilder.append(" AVG");
        stringBuilder.append("\n___________________\t\t\t\t");
        for (int i = 0; i < 13; i++) {
            stringBuilder.append(String.format("%5s", "_____\t"));
        }
        stringBuilder.append("\n");
        double one = 0;
        double two = 0;
        double average = 0.0;
        double averageTwo = 0.0;
        int countDays =0;
        for (int riverLoop = 0; riverLoop < riverNames.length; riverLoop++) {
            one = 0;

            stringBuilder.append(String.format("%-20s\t\t\t", riverNames[riverLoop]));
            for (int monthLoop = 0; monthLoop < pollutionData[riverLoop].length; monthLoop++) {
                one = 0;
                for (int dayLoop = 0; dayLoop < (pollutionData[riverLoop][monthLoop].length); dayLoop++) {
                    one += pollutionData[riverLoop][monthLoop][dayLoop].arsenic();
                }
                average = Double.valueOf(one / pollutionData[riverLoop][monthLoop].length);
                averageTwo += average;
                average = Math.round(average * 100) /100.00;
                String.format("%.3f", average);
                stringBuilder.append(String.format("%-5s",average) + "\t");
            }
            averageTwo = averageTwo / 12;
            stringBuilder.append(String.format("%.2f",averageTwo) + "\n");
            averageTwo = 0;
        }
        String singleString = stringBuilder.toString();
        return singleString;
    }
}
