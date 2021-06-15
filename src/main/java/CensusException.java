public class CensusException extends Exception{
    public enum ExceptionType {
        INVALID_TYPE,INVALID_EXTENSION,INVALID_DELIMETER,INVALID_HEADER
    }
    public ExceptionType type;
    public CensusException(ExceptionType type, String message)
    {
        super(message);
        this.type = type;
    }

}
