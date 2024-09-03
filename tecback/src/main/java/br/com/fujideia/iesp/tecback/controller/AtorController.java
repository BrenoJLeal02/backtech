package br.com.fujideia.iesp.tecback.controller;



import br.com.fujideia.iesp.tecback.model.Ator;
import br.com.fujideia.iesp.tecback.repository.AtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/ator")
public class AtorController {

    @Autowired
    private AtorRepository atorRepository;

    @GetMapping
    public List<Ator> listaTodos(){return atorRepository.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<Ator> buscarPorId(@PathVariable Long id){
        Optional<Ator> ator = atorRepository.findById(id);
        if(ator.isPresent()){
            return ResponseEntity.ok(ator.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Ator criarAtor(@RequestBody Ator ator) {return atorRepository.save(ator);}

    @PutMapping("/{id}")
    public ResponseEntity<Ator> atualizarAtor(@PathVariable Long id, @RequestBody Ator atorDetalhes){
        Optional<Ator> atorOptional = atorRepository.findById(id);
        if (atorOptional.isPresent()){
            Ator ator = atorOptional.get();
            ator.setNome(atorDetalhes.getNome());
            Ator atorAtualizado = atorRepository.save(ator);
            return ResponseEntity.ok(atorAtualizado);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarator(@PathVariable Long id){
        if(atorRepository.existsById(id)) {
            atorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
