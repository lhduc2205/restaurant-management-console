package exceptions;

public class ResourceAlreadyExistsException extends ApplicationRuntimeException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
