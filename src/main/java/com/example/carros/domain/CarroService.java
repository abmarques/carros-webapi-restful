package com.example.carros.domain;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public Iterable<Carro> getCarros(){

        return carroRepository.findAll();
    }

    public Optional<Carro> getCarroById(long id) {

        return carroRepository.findById(id);
    }

    public Iterable<Carro>  getCarroByTipo(String tipo) {
        return carroRepository.findByTipo(tipo);
    }

    public Carro insert(Carro carro) {
        return carroRepository.save(carro);
    }

    public Carro update(Carro carro, Long id) {

        Assert.notNull(id, "Não foi possível atualizar o registro.");

        //Busca o carro no banco de dados
        Optional<Carro> optional = getCarroById(id);
        if (optional.isPresent()){
            Carro db = optional.get();
            //Copiar as propriedades
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id " + db.getId());

            //Atualiza o carro
            carroRepository.save(db);

            return db;
        }else {
            throw  new RuntimeException("Não foi possível atualizar o registro.");
        }

        //usando LAMBDA
        /*getCarroById(id).map(db -> {
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id " + db.getId());

            carroRepository.save(db);

            return db;

        }).orElseThrow(() -> new RuntimeException("Não foi possível atualizar o registro."));*/



    }

    public void delete(Long id) {
        Optional<Carro> carro = getCarroById(id);

        if (carro.isPresent())
            carroRepository.deleteById(id);
    }
}
