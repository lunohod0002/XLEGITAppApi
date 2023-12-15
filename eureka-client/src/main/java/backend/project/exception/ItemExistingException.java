package backend.project.exception;

public class ItemExistingException extends RuntimeException{
    public ItemExistingException(String message) {
        super(message);
    }
}
