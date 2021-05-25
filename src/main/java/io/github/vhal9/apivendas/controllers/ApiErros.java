package io.github.vhal9.apivendas.controllers;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErros {

    @Getter
    private List<String> erros;

    public ApiErros(List<String> erros) {

        this.erros = erros;

    }

    public ApiErros(String mensagemErro) {

        this.erros = Arrays.asList(mensagemErro);
        
    }

}
