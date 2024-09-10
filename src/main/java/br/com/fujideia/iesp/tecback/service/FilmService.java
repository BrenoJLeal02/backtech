package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Film;
import br.com.fujideia.iesp.tecback.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public List<Film> listFilms() {
        return filmRepository.findAll();
    }

    public Film searchById(Long id) {
        var film = filmRepository.findById(id);
        if (film.isEmpty()) {
            throw new RuntimeException("Film not found");
        }
        return film.get();
    }

    public Film createFilm(Film film) {
        return filmRepository.save(film);
    }

    public Film updateFilm(Long id, Film film) {
        Optional<Film> filmOptional = filmRepository.findById(id);
        if (filmOptional.isEmpty()) {
            throw new RuntimeException("Film not found");
        }
        film.setId(id);
        return filmRepository.save(film);
    }

    public void deleteFilm(Long id) {
        if (filmRepository.existsById(id)) {
            filmRepository.deleteById(id);
        } else {
            throw new RuntimeException("Film not found");
        }
    }
}
