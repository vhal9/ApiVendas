package io.github.vhal9.apivendas.controllers;

import io.github.vhal9.apivendas.exceptions.PedidoNaoEncontradoException;
import io.github.vhal9.apivendas.exceptions.RegraDeNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraDeNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleRegraDeNegocioException(RegraDeNegocioException ex) {

        return new ApiErros(ex.getMessage());

    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handlePedidoNotFoundException(PedidoNaoEncontradoException ex){

        return new ApiErros(ex.getMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleMethodNotValidException(MethodArgumentNotValidException ex) {

        List<String> erros = ex.getBindingResult().getAllErrors()
                .stream()
                .map( erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
        
        return new ApiErros(erros);

    }

}
