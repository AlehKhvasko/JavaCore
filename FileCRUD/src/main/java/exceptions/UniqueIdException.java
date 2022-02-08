package exceptions;

public class UniqueIdException extends Exception{
    public UniqueIdException(String errorMessage){
        super(errorMessage);
    }
}
