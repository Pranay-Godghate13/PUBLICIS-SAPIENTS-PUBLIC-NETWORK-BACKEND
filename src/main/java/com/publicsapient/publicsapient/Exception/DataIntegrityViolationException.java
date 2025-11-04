package com.publicsapient.publicsapient.Exception;

public class DataIntegrityViolationException extends RuntimeException{
    private String dataName;
    private String data;
    private String message;

    DataIntegrityViolationException()
    {

    }

    DataIntegrityViolationException(String dataName,String data,String message)
    {
        super(String.format("%s not found %s:%s", dataName,data,message));
        this.dataName=dataName;
        this.data=data;
        this.message=message;
    }
}
