package com.sansa.FundacioEsplaiHackathon.exception;

public class UsernameAlreadyTakenException extends RuntimeException {

    public UsernameAlreadyTakenException(){
        super();
    }
    public UsernameAlreadyTakenException(String msg){
        super(msg);
    }
}
