package com.ttn.goals.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;


@Getter
@Setter
@ToString
public class ResponseDTO<T> {

    private Boolean status = true;
    private String message;
    private T data;
    private Object error;

    public ResponseDTO() {
    }

    public ResponseDTO(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseDTO(Boolean status, String message, T data, Object error) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.error = error;
    }

    public ResponseDTO(Boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    void setErrorResponse(Throwable e, String message) {
        this.message = StringUtils.isEmpty(message) ? e.getMessage() : message;
        this.status = false;
    }

    public void setFailureResponse(String message) {
        this.message = message;
        this.status = false;
    }

    void setSuccessResponse(T data, String message) {
        this.message = message;
        this.data = data;
        this.status = true;
    }

}
