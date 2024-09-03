package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Diretor;
import br.com.fujideia.iesp.tecback.repository.DiretorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/diretor")
public class DiretorController {

    @Autowired
    private DiretorRepository diretorRepository;

    @GetMapping
    public List<Diretor> listaTodos(){return diretorRepository.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<Diretor> buscarPorId(@PathVariable Long id){
        Optional<Diretor> diretor = diretorRepository.findById(id);
        if(diretor.isPresent()){
            return ResponseEntity.ok(diretor.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Diretor criarDiretor(@RequestBody Diretor diretor) {return diretorRepository.save(diretor);}

    @PutMapping("/{id}")
    public ResponseEntity<Diretor> atualizarDiretor(@PathVariable Long id, @RequestBody Diretor diretorDetalhes){
        Optional<Diretor> diretorOptional = diretorRepository.findById(id);
        if (diretorOptional.isPresent()){
            Diretor diretor = diretorOptional.get();
            diretor.setNome(diretorDetalhes.getNome());
            Diretor diretorAtualizado = diretorRepository.save(diretor);
            return ResponseEntity.ok(diretorAtualizado);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDiretor(@PathVariable Long id){
        if(diretorRepository.existsById(id)) {
            diretorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
