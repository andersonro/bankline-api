package com.dio.santander.bankline.api.controller;

import com.dio.santander.bankline.api.dto.CorrentistaNovo;
import com.dio.santander.bankline.api.dto.MovimentacaoNova;
import com.dio.santander.bankline.api.model.Correntista;
import com.dio.santander.bankline.api.model.Movimentacao;
import com.dio.santander.bankline.api.repository.CorrentistaRepository;
import com.dio.santander.bankline.api.repository.MovimentacaoRepository;
import com.dio.santander.bankline.api.service.CorrentistaService;
import com.dio.santander.bankline.api.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoRepository repository;

    @Autowired
    private MovimentacaoService service;

    @GetMapping
    public List<Movimentacao> findAll(){
        return repository.findAll();
    }

    @PostMapping
    public void save(@RequestBody MovimentacaoNova movimentacaoNova){
        service.save(movimentacaoNova);
    }

    @GetMapping("/{idConta}")
    public List<Movimentacao> findAll(@PathVariable("idConta") Integer idConta){
        return repository.findByIdConta(idConta);
    }
}
