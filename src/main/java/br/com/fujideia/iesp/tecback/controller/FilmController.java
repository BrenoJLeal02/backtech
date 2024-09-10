package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Film;
import br.com.fujideia.iesp.tecback.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    @GetMapping
    public List<Film> listAll() {
        return filmService.listFilms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> searchById(@PathVariable Long id) {
        return ResponseEntity.ok(filmService.searchById(id));
    }

    @PostMapping
    public Film createFilm(@RequestBody Film film) {
        return filmService.createFilm(film);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable Long id, @RequestBody Film filmDetails) {
        return ResponseEntity.ok(filmService.updateFilm(id, filmDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }
}