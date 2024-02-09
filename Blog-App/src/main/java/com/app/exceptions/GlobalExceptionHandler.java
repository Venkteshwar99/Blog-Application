package com.app.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandle(ResourceNotFoundException exception){
		
		String message = exception.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
     @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException( MethodArgumentNotValidException exception){
    	 Map<String, String> map = new HashMap<>();
    	 exception.getBindingResult().getAllErrors().forEach((error)->{
    		String field =((FieldError)error).getField();
    		 String defaultMessage = error.getDefaultMessage();
    		 map.put(field, defaultMessage);
    	 });
    	 
    	 
    	 return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
	}
	
}
