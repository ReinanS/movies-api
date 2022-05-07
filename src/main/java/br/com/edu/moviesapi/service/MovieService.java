package br.com.edu.moviesapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.edu.moviesapi.dto.MovieDTO;
import br.com.edu.moviesapi.model.Movie;
import br.com.edu.moviesapi.repository.MovieRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    private Movie findByIdOrThrowNotFoundRequestException(Long id) {
        return movieRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado"));
    }

    public Page<MovieDTO>  listAll(String nome, Pageable pageable) {
        if((nome != null) && (!nome.equals(""))) {
            return list(nome, pageable);
        } 

        return list(pageable);
    }

    private Page<MovieDTO> list(Pageable pageable) {
        return MovieDTO.pageConvert(movieRepository.findAll(pageable));
    }

    private Page<MovieDTO> list(String nome, Pageable pageable) {
        return MovieDTO.pageConvert(movieRepository.findByNomeContaining(nome, pageable));
    }

    public MovieDTO detail(Long id) {
        return new MovieDTO(findByIdOrThrowNotFoundRequestException(id));
    }

    public MovieDTO save(MovieDTO movieDTO) {
        
        Movie movie = new Movie();
        MovieDTO savedMovieDTO = new MovieDTO(movieRepository.save(setMovieFromDTO(movieDTO, movie)));

        return savedMovieDTO;
    }

    public void replace(Long id, MovieDTO movieDTO) {
        Movie movieSaved = findByIdOrThrowNotFoundRequestException(id);
        movieRepository.save(setMovieFromDTO(movieDTO, movieSaved));
    }

    private Movie setMovieFromDTO(MovieDTO movieDTO, Movie movie) {
        movie.setNome(movieDTO.getNome());
        movie.setSinopse(movieDTO.getSinopse());
        movie.setFoto(movieDTO.getSinopse());

        return movie;
    }

    public void delete(Long id) {
        movieRepository.delete(findByIdOrThrowNotFoundRequestException(id));
    }
}
