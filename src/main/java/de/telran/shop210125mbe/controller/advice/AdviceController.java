package de.telran.shop210125mbe.controller.advice;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AdviceController {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class) // для всех контроллеров
    public String handleNoSuchElementException(NoSuchElementException exception) {
        return  exception.getMessage();
    }

    // === Для обработки ошибок Validation с помощью @Validated
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class) // для всех контроллеров
    public String handleNoSuchElementException(ConstraintViolationException exception) {
        return  "Не корректные данные при проверке на уровне контроллера - "+exception.getMessage();
    }

    // === Для обработки ошибок Validation с помощью @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class) //показываем сразу все ошибки!
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(fe -> fe.getDefaultMessage()).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

//    @ResponseStatus(HttpStatus.CONFLICT)
//    @ExceptionHandler(Exception.class) // обработчик для всех остальніх типов исключений
//    public String handleException(Exception exception) {
//        return  "Извините, что-то пошло не так! Попробуйте позже...("+exception.getMessage()+")";
//    }
}
