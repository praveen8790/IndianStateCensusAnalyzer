import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StateCensusAnalyzerTest {
    @Test
    public void givenPath_WhenCorrectShouldReturnNumOfEntriesAs29() throws CensusException {
        String path = "J:\\StateCensusData.csv";
        StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
        int numOfEntries = stateCensusAnalyser.stateCensus(path,",");
        Assert.assertEquals(29, numOfEntries);
    }

    @Test
    public void givenPath_WhenWrong_ShouldReturn_WrongFileExceptionType() {
        String path = "J:\\SeCensusData.csv";
        StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
        try {
            int entries = stateCensusAnalyser.stateCensus(path,",");
        } catch (CensusException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CensusException.ExceptionType.INVALID_TYPE, e.type);
        }
    }

    @Test
    public void givenPath_ButWithWrongFileExtension_ShouldReturn_WrongExtensionExceptionType() {
        String path = "J:\\StateCensusData.c";
        StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
        try {
            int entries = stateCensusAnalyser.stateCensus(path,",");
        } catch (CensusException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CensusException.ExceptionType.INVALID_EXTENSION, e.type);
        }
    }

    @Test
    public void givenPathWithWrongDelimitedFileShouldReturnInvalidDelimiterExceptionType() {
        String path = "J:\\test4.csv";
        StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
        try {
            int entries = stateCensusAnalyser.stateCensus(path,"/");
        } catch (CensusException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CensusException.ExceptionType.INVALID_DELIMETER, e.type);
        }
    }

    @Test
    public void givenFileWithWrongHeaderShouldReturnInvalidHeaderExceptionType() {
        String path = "J:\\test5.csv";
        StateCensusAnalyzer stateCensusAnalyser = new StateCensusAnalyzer();
        try {
            int entries = stateCensusAnalyser.stateCensus(path,",");
        } catch (CensusException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CensusException.ExceptionType.INVALID_HEADER, e.type);
        }
    }
}