package com.cars24.csms.advice;

import com.cars24.csms.exceptions.CheckByUsername;
import com.cars24.csms.data.resp.APIResponse;
import com.cars24.csms.exceptions.ServiceAlreadyExistsException;
import com.cars24.csms.exceptions.UserServiceException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse> handleValidationExceptions(MethodArgumentNotValidException exception) {
        log.info("[handleValidationExceptions]");
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                errorMap.put(error.getField(), error.getDefaultMessage()));

        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatuscode(HttpStatus.OK.value());
        apiResponse.setSuccess(false);
        apiResponse.setMessage("Invalid data");
        apiResponse.setService("APPUSR-" + HttpStatus.OK.value());
        apiResponse.setData(errorMap);
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<APIResponse> handleUserServiceExceptions(UserServiceException exception) {
        log.error("[UserServiceException]", exception);
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatuscode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setSuccess(false);
        apiResponse.setMessage(exception.getMessage());
        apiResponse.setService("APPUSR-" + HttpStatus.BAD_REQUEST.value());
        apiResponse.setData(null);
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(ServiceAlreadyExistsException.class)
    public ResponseEntity<APIResponse> handleServiceAlreadyExistsException(ServiceAlreadyExistsException ex) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatuscode(HttpStatus.CONFLICT.value());
        apiResponse.setSuccess(false);
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setData(null);
        apiResponse.setService("SERVICE-CREATION");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<APIResponse> handleNullPointerException(NullPointerException exception) {
        log.error("[handleNullPointerException] - Unexpected null value encountered: {}", exception.getMessage());

        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatuscode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiResponse.setSuccess(false);
        apiResponse.setMessage("Unexpected server error occurred. Please try again later.");
        apiResponse.setService("APPUSR-" + HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse> handleGenericException(Exception exception) {
        log.error("[handleGenericException] - Unexpected error: {}", exception.getMessage());

        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatuscode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiResponse.setSuccess(false);
        apiResponse.setMessage("An unexpected error occurred. Please contact support.");
        apiResponse.setService("APPUSR-" + HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiResponse.setData(null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }

    @ExceptionHandler(CheckByUsername.class)
    public ResponseEntity<APIResponse> handleCheckByUserName(CheckByUsername exception) {
        log.error("[CheckByUserName]", exception);
        APIResponse resp = new APIResponse();
        resp.setStatuscode(HttpStatus.BAD_REQUEST.value());
        resp.setSuccess(false);
        resp.setMessage(exception.getMessage());
        resp.setService("APPUSR - " + HttpStatus.BAD_REQUEST.value());
        resp.setData(null);

        return ResponseEntity.badRequest().body(resp);
    }
}
