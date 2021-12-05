package com.example.BankAccounts.MyUtil;

import org.springframework.http.HttpStatus;

public class MyException extends Exception{
    public HttpStatus code;
    public String message;

    public MyException(){
        super();
    }
    public MyException(HttpStatus code, String message){
        super();
        this.code = code;
        this.message = message;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
