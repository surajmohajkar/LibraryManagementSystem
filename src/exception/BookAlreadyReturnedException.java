package exception;

public class BookAlreadyReturnedException extends LibraryException {
    public BookAlreadyReturnedException(String message) {
        super(message);
    }
}