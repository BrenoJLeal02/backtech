package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FilmeService {

    private final FilmeRepository repository;

    public List<Filme> listar(){
        return repository.findAll();
    }
    public Filme buscarPorId(Long id){
        Optional<Filme> filme = repository.findById(id);
        if(filme.isEmpty()){
            throw new RuntimeException("Filme not found");
        }
        return filme.get();
    }

    public Filme criarFilme(Filme filme){
        return repository.save(filme);
    }

    public void deletarFilme(Long id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Filme not found");
        }
    }
    public Filme atualizarFilme(Long id, Filme filme){
        Optional<Filme> filmeOptional = repository.findById(id);
        if(filmeOptional.isEmpty()){
            throw  new RuntimeException("Filme Not Found");
        }
        filme.setId(id);
        return repository.save(filme);

    }
}
