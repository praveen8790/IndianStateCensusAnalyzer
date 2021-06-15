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

public class StateCodeANalyzer {
    public int stateCode(String path,String delimeter) throws CensusException{
        int entry = 0;
        try(Reader reader = Files.newBufferedReader(Paths.get(path))){
            BufferedReader br =
                    new BufferedReader(new FileReader(path));
            String[]line = br.readLine().split(delimeter);
            if(!line[0].contains("State")||!line[1].contains("StateCode"))
                throw new CensusException(CensusException.ExceptionType.INVALID_HEADER,"wrong header");

            Scanner sc = new Scanner(new FileReader(path));
            System.out.println(sc.delimiter());
            CsvToBean<StateCode> csvToBean = new CsvToBeanBuilder<StateCode>(reader)
                    .withType(StateCode.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<StateCode> iterator = csvToBean.iterator();
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
            if(!(path.contains(".csv")))
                throw new CensusException(CensusException.ExceptionType.INVALID_EXTENSION,"should be .csv file");
            throw new CensusException(CensusException.ExceptionType.INVALID_TYPE,"not correct path");
        }
        return entry;
    }
}
