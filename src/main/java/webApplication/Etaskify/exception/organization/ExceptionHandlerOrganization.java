package webApplication.Etaskify.exception.organization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import webApplication.Etaskify.exception.ErrorResponse;

import java.time.OffsetDateTime;

import static webApplication.Etaskify.exception.ErrorCode.MS9_BAD_REQUEST_004;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerOrganization {

    @ExceptionHandler(OrganizationAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> organizationAlreadyExistException(OrganizationAlreadyExistException exception,
                                                                           WebRequest webRequest) {

        var path = ((ServletWebRequest) webRequest).getRequest().getRequestURL().toString();
        log.error("Exception {}", exception.getLocalizedMessage());
        exception.printStackTrace();
        return ResponseEntity.status(400).body(ErrorResponse.builder()
                .status(400)
                .code(MS9_BAD_REQUEST_004.toString())
                .message("Bad request")
                .detail("Given Organization name already exists")
                .timestamp(OffsetDateTime.now())
                .path(path)
                .build());
    }
}
