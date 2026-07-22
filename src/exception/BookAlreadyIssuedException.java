package exception;

public class BookAlreadyIssuedException extends LibraryException {
    public BookAlreadyIssuedException(String message) {
        super(message);
    }
}