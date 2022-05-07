package br.com.edu.moviesapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.edu.moviesapi.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
     public Page<Movie> findByNomeContaining(String nome, Pageable pageable);
}
