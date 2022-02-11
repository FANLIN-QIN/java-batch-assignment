package RESTdemo.exception;

public class Exception extends RuntimeException{
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public Exception(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public Exception() {
        super();
    }
}
