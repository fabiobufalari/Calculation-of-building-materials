package bufalari.com.contruction.exceptions;

public class CustomException extends RuntimeException {
    private final int httpStatus;
    private final String traceId;

    public CustomException(String message, int httpStatus, String traceId) {
        super(message);
        this.httpStatus = httpStatus;
        this.traceId = traceId;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getTraceId() {
        return traceId;
    }
}
