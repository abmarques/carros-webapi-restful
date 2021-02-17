package com.example.carros.domain;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<CarroDTO> getCarros(){

        return carroRepository.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public Optional<CarroDTO> getCarroById(long id) {

        return carroRepository.findById(id).map(CarroDTO::create);
    }

    public List<CarroDTO> getCarroByTipo(String tipo) {

        return carroRepository.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public Carro insert(Carro carro) {

        return carroRepository.save(carro);
    }

    public Carro update(Carro carro, Long id) {

        Assert.notNull(id, "Não foi possível atualizar o registro.");

        //Busca o carro no banco de dados
        Optional<Carro> optional = carroRepository.findById(id);

        if (optional.isPresent()){
            Carro db = optional.get();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id " + db.getId());

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

        if (getCarroById(id).isPresent())
            carroRepository.deleteById(id);
    }
}
