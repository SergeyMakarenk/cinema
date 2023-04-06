package com.makarenko.main.servise;

import com.makarenko.main.model.Movie;
import java.util.List;

/**
 * Сервис по работе с фильмами
 * для пользователя с уровнем доступа USER
 */
public interface MovieServise {

    /**
     * метод для просмотра списка фильмов, доступных к показу
     */
    List<Movie> getAllMovies();

    /**
     *метод для редактирования даты и времени показа фильма
     */
    boolean redactMovie();

    /**
     * метод для удаления фильма с показа
     */
    boolean deleteMovieById();

    /**
     * метод добавления фильма в показ
     */
    String createMovie();

    /**
     * метод получения id фильма по названию фильма
     */
    Integer getIdMovie(String nameMovie);

}
