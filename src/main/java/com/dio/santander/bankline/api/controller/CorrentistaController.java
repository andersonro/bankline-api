package com.dio.santander.bankline.api.controller;

import com.dio.santander.bankline.api.dto.CorrentistaNovo;
import com.dio.santander.bankline.api.model.Correntista;
import com.dio.santander.bankline.api.repository.CorrentistaRepository;
import com.dio.santander.bankline.api.service.CorrentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/correntistas")
public class CorrentistaController {

    @Autowired
    private CorrentistaRepository repository;

    @Autowired
    private CorrentistaService service;

    @GetMapping
    public List<Correntista> findAll(){
        return repository.findAll();
    }

    @PostMapping
    public void save(@RequestBody CorrentistaNovo correntistaNovo){
        service.save(correntistaNovo);
    }

    @DeleteMapping
    public void delete(@RequestBody Integer id){
        Correntista correntista = repository.findById(id).orElse(null);

        if (correntista != null){
            repository.delete(correntista);
        }

    }
}
