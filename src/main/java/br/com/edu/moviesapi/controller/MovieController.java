package br.com.edu.moviesapi.controller;


import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.edu.moviesapi.dto.MovieDTO;
import br.com.edu.moviesapi.service.MovieService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController 
@RequestMapping("/filmes")
public class MovieController {

    private final MovieService movieService;

    @ApiOperation(value = "Retorna os filmes por página")
    @GetMapping
    public Page<MovieDTO> list(@RequestParam(required = false) String nome, Pageable pageable) {
        return movieService.listAll(nome, pageable);
    }

    @ApiOperation(value = "Retorna um filme por id")
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> detail(@PathVariable Long id) {
        MovieDTO movieDTO = movieService.detail(id);
        return new ResponseEntity<MovieDTO>(movieDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Salva um filme")
    @PostMapping
    public ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieDTO movieDTO) {
        MovieDTO movie = movieService.save(movieDTO);
        return new ResponseEntity<MovieDTO>(movie, HttpStatus.CREATED);
    }   

    @ApiOperation(value = "Atualiza um flme")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> replace(@PathVariable Long id, @Valid @RequestBody MovieDTO movieDTO) {
         movieService.replace(id, movieDTO);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Deleta um filme por id")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id, @Valid @RequestBody MovieDTO movieDTO) {
         movieService.delete(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
