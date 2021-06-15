import com.opencsv.bean.CsvBindByName;

public class StateCode {
    @CsvBindByName(column = "State",required = true)
    public String statename;
    @CsvBindByName(column = "StateCode",required = true)
    public String Code;
}
