package io.github.vhal9.apivendas.controllers;

import io.github.vhal9.apivendas.exceptions.PedidoNaoEncontradoException;
import io.github.vhal9.apivendas.exceptions.RegraDeNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
