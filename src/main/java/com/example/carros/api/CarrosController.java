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
    public ResponseEntity<Iterable<Carro>> Get(){
        return ResponseEntity.ok(carrosService.getCarros());
    }

    @GetMapping("/{id}")
    public ResponseEntity GetById(@PathVariable("id") Long id){
        var carro = carrosService.getCarroById(id);

        return carro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<Iterable<Carro>> GetById(@PathVariable("tipo") String tipo){
        var carros= carrosService.getCarroByTipo(tipo);

        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
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
