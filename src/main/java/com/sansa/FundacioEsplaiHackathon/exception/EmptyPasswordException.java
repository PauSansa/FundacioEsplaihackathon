package com.sansa.FundacioEsplaiHackathon.exception;

public class EmptyPasswordException extends Exception{

    public EmptyPasswordException(){
        super();
    }
    public EmptyPasswordException(String msg){
        super(msg);
    }
}
