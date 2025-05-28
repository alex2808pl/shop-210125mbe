package de.telran.shop210125mbe.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class AdviceController {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class) // для всех контроллеров
    public String handleNoSuchElementException(NoSuchElementException exception) {
        return  exception.getMessage();
    }

//    @ResponseStatus(HttpStatus.CONFLICT)
//    @ExceptionHandler(Exception.class) // обработчик для всех остальніх типов исключений
//    public String handleException(Exception exception) {
//        return  "Извините, что-то пошло не так! Попробуйте позже...("+exception.getMessage()+")";
//    }
}
