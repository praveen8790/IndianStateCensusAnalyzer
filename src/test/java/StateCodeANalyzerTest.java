import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StateCodeANalyzerTest {
    @Test
    public void givenPath_WhenCorrectShouldReturnNumOfEntriesAs37() throws CensusException {
        String path = "J:\\StateCode.csv";
        StateCodeANalyzer stateCodeANalyzer = new StateCodeANalyzer();
        int numOfEntries = stateCodeANalyzer.stateCode(path,",");
        Assert.assertEquals(37, numOfEntries);
    }

    @Test
    public void givenPath_WhenWrong_ShouldReturn_WrongFileExceptionType() {
        String path = "J:\\Stat.csv";
        StateCodeANalyzer stateCodeANalyzer = new StateCodeANalyzer();
        try {
            int entries = stateCodeANalyzer.stateCode(path,",");
        } catch (CensusException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CensusException.ExceptionType.INVALID_TYPE, e.type);
        }
    }

    @Test
    public void givenPath_ButWithWrongFileExtension_ShouldReturn_WrongExtensionExceptionType() {
        String path = "J:\\StateCode.cv";
        StateCodeANalyzer stateCodeANalyzer = new StateCodeANalyzer();
        try {
            int entries = stateCodeANalyzer.stateCode(path,",");
        } catch (CensusException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CensusException.ExceptionType.INVALID_EXTENSION, e.type);
        }
    }

    @Test
    public void givenPathWithWrongDelimitedFileShouldReturnInvalidDelimiterExceptionType() {
        String path = "J:\\test4state.csv";
        StateCodeANalyzer stateCodeANalyzer = new StateCodeANalyzer();
        try {
            int entries = stateCodeANalyzer.stateCode(path,"/");
        } catch (CensusException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CensusException.ExceptionType.INVALID_DELIMETER, e.type);
        }
    }

    @Test
    public void givenFileWithWrongHeaderShouldReturnInvalidHeaderExceptionType() {
        String path = "J:\\test5state.csv";
        StateCodeANalyzer stateCodeANalyzer = new StateCodeANalyzer();
        try {
            int entries = stateCodeANalyzer.stateCode(path,",");
        } catch (CensusException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(CensusException.ExceptionType.INVALID_HEADER, e.type);
        }
    }
}