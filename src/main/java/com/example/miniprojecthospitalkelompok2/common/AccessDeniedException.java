package com.example.miniprojecthospitalkelompok2.common;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String message){
        super(message);
    }
}
