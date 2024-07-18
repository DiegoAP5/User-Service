package project.UserService.infraestructure.inputs.dtos.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder @Getter
public class BaseResponse {

    private Object data;
    private String message;
    private Boolean success;
    private HttpStatus httpStatus;

    public ResponseEntity<BaseResponse> toResponseEntity() {
        return new ResponseEntity<>(this, httpStatus);
    }
}
