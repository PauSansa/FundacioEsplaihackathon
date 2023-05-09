package com.sansa.FundacioEsplaiHackathon.controller;

import com.sansa.FundacioEsplaiHackathon.exception.EmptyPasswordException;
import com.sansa.FundacioEsplaiHackathon.exception.UsernameAlreadyTakenException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorMessage handleBadCredentialsException(BadCredentialsException ex){
        return new ErrorMessage("Bad Credentials");
    }

    @ExceptionHandler(EmptyPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage handleEmptyPasswordException(EmptyPasswordException ex) {
        return new ErrorMessage("Password can't be empty");
    }

    @ExceptionHandler(UsernameAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorMessage handleUsernameAlreadyTakenException(UsernameAlreadyTakenException ex) {
        return new ErrorMessage(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage allExeptions(Exception ex) {
        return new ErrorMessage(ex.getMessage() + Arrays.toString(ex.getStackTrace()));
    }

    @Data
    @AllArgsConstructor
    private static class ErrorMessage {
        private String message;
    }
}
