package com.sansa.FundacioEsplaiHackathon.exception;

public class UsernameAlreadyTakenException extends Exception {

    public UsernameAlreadyTakenException(){
        super();
    }
    public UsernameAlreadyTakenException(String msg){
        super(msg);
    }
}
