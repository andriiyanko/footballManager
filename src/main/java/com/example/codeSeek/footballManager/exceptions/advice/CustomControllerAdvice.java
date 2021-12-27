package com.example.codeSeek.footballManager.exceptions.advice;

import com.example.codeSeek.footballManager.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class CustomControllerAdvice {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(Exception e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND; //404

        return new ResponseEntity<>(new ErrorResponse(httpStatus,e.getMessage()),httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleMyExceptions(Exception e){
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ModelAndView modelAndView = new ModelAndView();

        //converting stackTrace to String
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String stacktrace = stringWriter.toString();
        ErrorResponse errorResponse = new ErrorResponse(httpStatus, e.getMessage(), stacktrace);
        modelAndView.addObject("errorMsg", errorResponse);
        modelAndView.setViewName("/errors");

        return modelAndView;
    }

}
