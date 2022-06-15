package webApplication.Etaskify.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

import static webApplication.Etaskify.exception.ErrorCode.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler  {

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(RoleNotFoundException exception,
                                                                       WebRequest webRequest) {

        var path = ((ServletWebRequest) webRequest).getRequest().getRequestURL().toString();
        log.error("Exception {}", exception.getLocalizedMessage());
        exception.printStackTrace();
        return ResponseEntity.status(400).body(ErrorResponse.builder()
                .status(400)
                .code(MS9_BAD_REQUEST_001.toString())
                .message("Bad request")
                .detail("Role not found")
                .timestamp(OffsetDateTime.now())
                .path(path)
                .build());
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> EmailAlreadyExistException(EmailAlreadyExistException exception,
                                                                       WebRequest webRequest) {

        var path = ((ServletWebRequest) webRequest).getRequest().getRequestURL().toString();
        log.error("Exception {}", exception.getLocalizedMessage());
        exception.printStackTrace();
        return ResponseEntity.status(400).body(ErrorResponse.builder()
                .status(400)
                .code(MS9_BAD_REQUEST_002.toString())
                .message("Bad request")
                .detail("Given email already exists ")
                .timestamp(OffsetDateTime.now())
                .path(path)
                .build());
    }

    @ExceptionHandler(EmailOrPasswordInvalid.class)
    public ResponseEntity<ErrorResponse> EmailOrPasswordInvalid(EmailOrPasswordInvalid exception,
                                                                    WebRequest webRequest) {

        var path = ((ServletWebRequest) webRequest).getRequest().getRequestURL().toString();
        log.error("Exception {}", exception.getLocalizedMessage());
        exception.printStackTrace();
        return ResponseEntity.status(400).body(ErrorResponse.builder()
                .status(400)
                .code(MS9_BAD_REQUEST_003.toString())
                .message("Bad request")
                .detail("Email or password is invalid ")
                .timestamp(OffsetDateTime.now())
                .path(path)
                .build());
    }


}

