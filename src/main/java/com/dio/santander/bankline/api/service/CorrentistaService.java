package com.dio.santander.bankline.api.service;

import com.dio.santander.bankline.api.dto.CorrentistaNovo;
import com.dio.santander.bankline.api.model.Conta;
import com.dio.santander.bankline.api.model.Correntista;
import com.dio.santander.bankline.api.repository.CorrentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CorrentistaService {

    @Autowired
    private CorrentistaRepository repository;

    public void save(CorrentistaNovo correntistaNovo){
        Correntista correntista = new Correntista();

        correntista.setCpf(correntistaNovo.getCpf());
        correntista.setNome(correntistaNovo.getNome());

        Conta conta = new Conta();
        conta.setSaldo(0.0);
        conta.setNumero(new Date().getTime());

        correntista.setConta(conta);

        repository.save(correntista);
    }
}
