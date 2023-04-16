package com.Projet.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import java.util.Map;
import java.util.HashMap;
import org.springframework.validation.FieldError;



import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import com.Projet.error.DataNotFoundException;







@ControllerAdvice
public class GlobalExceptionHandler {

	    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
	    @ResponseStatus(HttpStatus.UNAUTHORIZED)
	    @ResponseBody
	    public ErrorResponses handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException ex) {
	        return new ErrorResponses("UNAUTHORIZED", ex.getMessage());
	    }

	    @ExceptionHandler(UsernameNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    @ResponseBody
	    public ErrorResponses handleUsernameNotFoundException(UsernameNotFoundException ex) {
	        return new ErrorResponses("NOT_FOUND", ex.getMessage());
	    }

	    @ExceptionHandler(AccessDeniedException.class)
	    @ResponseStatus(HttpStatus.FORBIDDEN)
	    @ResponseBody
	    public ErrorResponses handleAccessDeniedException(AccessDeniedException ex) {
	        return new ErrorResponses("FORBIDDEN", ex.getMessage());
	    }

	    @ExceptionHandler(IllegalArgumentException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    public ErrorResponses handleIllegalArgumentException(IllegalArgumentException ex) {
	        return new ErrorResponses("BAD_REQUEST", ex.getMessage());
	    }
	    
	    @ExceptionHandler(DataNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    @ResponseBody
	    public ErrorResponses handleDataNotFoundException(DataNotFoundException ex) {
	        return new ErrorResponses("NOT_FOUND", ex.getMessage());
	    }

	    @ExceptionHandler(RuntimeException.class)
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    @ResponseBody
	    public ErrorResponses handleRuntimeException(RuntimeException ex) {
	        return new ErrorResponses("INTERNAL_SERVER_ERROR", ex.getMessage());
	    }
	    
	    @ExceptionHandler(MethodArgumentNotValidException.class)
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		    Map<String, String> errors = new HashMap<>();
		    ex.getBindingResult().getAllErrors().forEach((error) -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        errors.put(fieldName, errorMessage);
		    });
		    return errors;
		}
}