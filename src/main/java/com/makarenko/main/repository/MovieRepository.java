package com.makarenko.main.repository;

import com.makarenko.main.model.Movie;
import java.util.List;

public interface MovieRepository {

    boolean createMovie(Movie movie);
    List<Movie> getAllMovies();
    boolean redactMovie(Movie movie, Integer id);
    Integer getIdMovieByNameMovie(String nameMovie);
    boolean deleteMovieById(int id);
    Movie getMovieByIdMovie(int idMovie);
}
