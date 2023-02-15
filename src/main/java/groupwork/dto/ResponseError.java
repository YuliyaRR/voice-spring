package groupwork.dto;

import org.springframework.http.HttpStatus;

public class ResponseError {
    private String message;
    private HttpStatus status;
    private int statusCode;

    public ResponseError(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.statusCode = status.value();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
