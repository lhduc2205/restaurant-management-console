package exceptions;

public class NotFoundException extends ApplicationRuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
