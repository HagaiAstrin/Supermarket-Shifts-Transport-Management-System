package Domain;

public class DomainException extends Exception{
    public DomainException(String error) {
        super(error);
    }
    public static String raise(String error){
        return error;
    }
}
