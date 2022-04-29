package net.javaguides.springbootcrudrestfulwebservices.exception;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class PedidosExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<Error> erros = criarListErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);

    }

    private List<Error> criarListErros(BindingResult bindingResult){
        List<Error> errors= new ArrayList<>();
        for (FieldError fieldError: bindingResult.getFieldErrors()){
           String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
           errors.add(new Error(mensagemUsuario));
        }
        return errors;
    }
    @Getter
    public class Erro{
        String mensagemUsuario;
        String mensagemDesenvolvedor;

        public Erro(String mensagemUsuario, String mensagemDesenvolvedor){
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }

    }


}
