package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Genero;
import br.com.fujideia.iesp.tecback.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private GeneroRepository generoRepository;

    @GetMapping
    public List<Genero> listaTodos(){return generoRepository.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<Genero> buscarPorId(@PathVariable Long id){
        Optional<Genero> genero = generoRepository.findById(id);
        if(genero.isPresent()){
            return ResponseEntity.ok(genero.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Genero criarGenero(@RequestBody Genero genero) {return generoRepository.save(genero);}

    @PutMapping("/{id}")
    public ResponseEntity<Genero> atualizarGenero(@PathVariable Long id, @RequestBody Genero generorDetalhes){
        Optional<Genero> generoOptional = generoRepository.findById(id);
        if (generoOptional.isPresent()){
            Genero genero = generoOptional.get();
            genero.setNome(generorDetalhes.getNome());
            Genero generoAtualizado = generoRepository.save(genero);
            return ResponseEntity.ok(generoAtualizado);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGenero(@PathVariable Long id){
        if(generoRepository.existsById(id)) {
            generoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
