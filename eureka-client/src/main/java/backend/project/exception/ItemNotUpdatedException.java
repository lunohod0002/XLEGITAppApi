package backend.project.exception;

public class ItemNotUpdatedException extends RuntimeException {
    public ItemNotUpdatedException(String message) {
        super(message);
    }
}
