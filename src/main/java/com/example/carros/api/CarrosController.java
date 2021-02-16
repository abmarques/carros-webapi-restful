package com.example.carros.api;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    @Autowired
    private CarroService carrosService;

    @GetMapping
    public Iterable<Carro> Get(){
        return carrosService.getCarros();
    }

    @GetMapping("/{id}")
    public Optional<Carro> GetById(@PathVariable("id") Long id){
        return carrosService.getCarroById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public Iterable<Carro> GetById(@PathVariable("tipo") String tipo){
        return carrosService.getCarroByTipo(tipo);
    }

    @PostMapping
    public String PostCarro(@RequestBody Carro carro){
        Carro c = carrosService.insert(carro);
        return "carro salvo com sucesso: " + c.getId();
    }

    @PutMapping("/{id}")
    public String PostCarro(@PathVariable("id") Long id, @RequestBody Carro carro){
        Carro newC = carrosService.update(carro, id);
        return "carro atualizado com sucesso: " + newC.getId();
    }

    @DeleteMapping("/{id}")
    public String PostCarro(@PathVariable("id") Long id){
        carrosService.delete(id);
        return "carro deletado com sucesso";
    }
}
