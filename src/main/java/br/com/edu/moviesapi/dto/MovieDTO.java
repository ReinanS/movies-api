package br.com.edu.moviesapi.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.data.domain.Page;

import br.com.edu.moviesapi.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieDTO {
    
    @Min(1)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String sinopse;

    @NotBlank
    private String foto;

    public MovieDTO(Movie movie) {
        this.id = movie.getId();
        this.nome = movie.getNome();
        this.sinopse = movie.getSinopse();
        this.foto = movie.getFoto();
    }

    public static Page<MovieDTO> pageConvert(Page<Movie> pages) {
        return pages.map(MovieDTO::new);
    }
}
