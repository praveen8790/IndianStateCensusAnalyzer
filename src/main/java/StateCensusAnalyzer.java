import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;

public class StateCensusAnalyzer {
    public static final String filepath = "J:\\StateCensusData.csv";
    public Integer stateCensus(String censuspath,String delimeter) throws CensusException {
        int entry = 0;
        try(Reader reader = Files.newBufferedReader(Paths.get(censuspath))){

            BufferedReader br =
                    new BufferedReader(new FileReader(censuspath));
            String[]line = br.readLine().split(delimeter);
            if(!line[0].contains("State")||!line[1].contains("Population")||!line[2].contains("AreaInSqKm")||!line[3].contains("DensityPerSqKm"))
                throw new CensusException(CensusException.ExceptionType.INVALID_HEADER,"wrong header");

            Scanner sc = new Scanner(new FileReader(censuspath));
            System.out.println(sc.delimiter());
            CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder<CSVStateCensus>(reader)
                        .withType(CSVStateCensus.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
            Iterator<CSVStateCensus> iterator = csvToBean.iterator();
            while(iterator.hasNext()){
                iterator.next();
                entry += 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            throw new CensusException(CensusException.ExceptionType.INVALID_HEADER,"wrong header");
        }catch (RuntimeException e) {
            throw new CensusException(CensusException.ExceptionType.INVALID_DELIMETER,"wrong delimeter");
        }catch (IOException e){
            if(!(censuspath.contains(".csv")))
                throw new CensusException(CensusException.ExceptionType.INVALID_EXTENSION,"should be .csv file");
            throw new CensusException(CensusException.ExceptionType.INVALID_TYPE,"not correct path");
        }
        return entry;
    }
}
