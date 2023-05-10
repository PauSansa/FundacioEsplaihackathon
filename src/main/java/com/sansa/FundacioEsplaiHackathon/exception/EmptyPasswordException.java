package com.sansa.FundacioEsplaiHackathon.exception;

public class EmptyPasswordException extends RuntimeException{

    public EmptyPasswordException(){
        super();
    }
    public EmptyPasswordException(String msg){
        super(msg);
    }
}
