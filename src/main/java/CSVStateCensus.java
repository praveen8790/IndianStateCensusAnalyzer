import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class CSVStateCensus {
    @CsvBindByName(column = "State",required = true)
    private String state;
    @CsvBindByName(column = "Population",required = true)
    private Long population;

    @CsvBindByName(column = "AreaInSqKm")
    private Long areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm")
    private Long densityPerSqKm;

}
