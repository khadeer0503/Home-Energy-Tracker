package com.Home_Energy_Tracker.IOT_Ingestion_Service.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message)
    {
        super(message);
    }
    public ApiException(String message, Throwable cause)
    {
        super();
    }
}
