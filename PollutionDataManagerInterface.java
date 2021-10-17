package src.com.jeremyward;

/**
 *  represents the contract for PollutionDataManager work
 */
public interface PollutionDataManagerInterface {

    // constructor should take two File parameters:
    // (1) count file
    // (2) data file
    // if it can't find the count file, it should create 0-sized arrays, not throw a FileNotFoundException

    /**
     * retrieves the name of a river
     *
     * @param riverIndex the index of the river
     * @return the river name
     */
    public String getRiverName(int riverIndex);

    /**
     * retrieves the index associated with the specified river name (case-insensitive)
     *
     * @param riverName the name of the river
     * @return the associated river's index, or -1 if the river is not found
     */
    public int findRiver(String riverName);

    /**
     * retrieves the count of rivers
     *
     * @return the number of rivers for which data is available
     */
    public int getRiverCount();

    /**
     * retrieves the number of readings available for a specified river and month
     *
     * @param riverIndex  the index of the river
     * @param monthNumber the month number (not index!), 1-12
     * @return the number of data readings available
     */
    public int getPollutionReadingCount(int riverIndex, int monthNumber);

    /**
     * retrieves pollution data for a specified river, month, and day
     *
     * @param riverIndex  the index of the river
     * @param monthNumber the month number (not index!), 1-12
     * @param dayIndex    the day index (not date!)
     * @return the associated pollution data
     */
    public PollutionData getPollutionData(int riverIndex, int monthNumber, int dayIndex);

    /**
     * generates a report showing each river's monthly average for arsenic, plus a yearly average
     * @return  arsenic report
     */
    public String generateArsenicReport();

    // if doing extra credit, use this signature
    //public PollutionAverage[] getTopNPollutedRivers(PollutionDataManager.PollutionType pollutionType, int howMany)

}