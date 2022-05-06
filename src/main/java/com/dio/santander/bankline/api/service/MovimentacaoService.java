package com.dio.santander.bankline.api.service;

import com.dio.santander.bankline.api.dto.MovimentacaoNova;
import com.dio.santander.bankline.api.model.Correntista;
import com.dio.santander.bankline.api.model.Movimentacao;
import com.dio.santander.bankline.api.model.MovimentacaoTipo;
import com.dio.santander.bankline.api.repository.CorrentistaRepository;
import com.dio.santander.bankline.api.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository repository;

    @Autowired
    private CorrentistaRepository correntistaRepository;

    public void save(MovimentacaoNova movimentacaoNova){
        Movimentacao movimentacao = new Movimentacao();

        Double valor = movimentacaoNova.getValor();
        if (movimentacaoNova.getTipo()==MovimentacaoTipo.DESPESA)
            valor = valor * -1;

        movimentacao.setDataHora(LocalDateTime.now());
        movimentacao.setDescricao(movimentacaoNova.getDescricao());
        movimentacao.setIdConta(movimentacaoNova.getIdConta());
        movimentacao.setValor(valor);
        movimentacao.setTipo(movimentacaoNova.getTipo());

        Correntista correntista = correntistaRepository.findById(movimentacao.getIdConta()).orElse(null);
        if (correntista != null){
            correntista.getConta().setSaldo(correntista.getConta().getSaldo() + valor);
            correntistaRepository.save(correntista);
        }

        repository.save(movimentacao);
    }
}
