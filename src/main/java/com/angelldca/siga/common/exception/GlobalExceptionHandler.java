package com.angelldca.siga.common.exception;


import com.angelldca.siga.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        List<ErrorField> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErrorField(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        ApiError apiError = new ApiError("Validation Error", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail(apiError));
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResponse<String>> handleAllUncaughtException(Exception ex, WebRequest request) {
        if (request.getDescription(false).contains("swagger") || request.getDescription(false).contains("api-docs")) {
            throw new RuntimeException(ex); // Re-lanza la excepción si viene de swagger
        }

        ApiError apiError = new ApiError("An unexpected error occurred.",
                List.of(new ErrorField("internal", "Internal server error.")));
        return ResponseEntity.internalServerError().body(ApiResponse.<ApiError>fail(apiError));
    }

    @ExceptionHandler(BusinessRuleValidationException.class)
    public ResponseEntity<ApiResponse<String>> handleBusinessRuleValidationException(BusinessRuleValidationException ex) {
        ApiError apiError = new ApiError(
                ex.getStatus(),
                ex.getMessage(),
                List.of(ex.getBrokenRule().getErrorField())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.<ApiError>fail(apiError));
    }

}
