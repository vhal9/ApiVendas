package io.github.vhal9.apivendas.controllers;

import io.github.vhal9.apivendas.models.entity.Produto;
import io.github.vhal9.apivendas.repositorys.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/produtos/")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping()
    public List<Produto> listarProdutos(@RequestBody Produto filtroProduto) {

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtroProduto, matcher);

        return produtoRepository.findAll(example);

    }


    @GetMapping("{id}")
    public Produto buscarProdutoById(@PathVariable Integer id) {

        return produtoRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvarProduto(@RequestBody @Valid Produto produto) {

        return produtoRepository.save(produto);

    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarProduto(@RequestBody @Valid Produto produto) {

        produtoRepository
                .findById(produto.getId())
                .map(produtoExistente -> {
                    produtoRepository.save(produto);
                    return produtoExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Produto não encontrado"));

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirProduto(@PathVariable Integer id) {

        produtoRepository
                .findById(id)
                .map(produto -> {
                    produtoRepository.delete(produto);
                    return produto;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

    }
}
