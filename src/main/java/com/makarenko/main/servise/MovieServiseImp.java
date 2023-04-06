package com.makarenko.main.servise;

import com.makarenko.main.model.Movie;
import com.makarenko.main.model.NameOfMovie;
import com.makarenko.main.repository.MovieRepository;
import com.makarenko.main.repository.MovieRepositotyImp;
import com.makarenko.main.util.ProcessStepFromUser;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import static com.makarenko.main.util.Constants.*;

@Slf4j
public class MovieServiseImp implements MovieServise {

    private final MovieRepository movieRepository = new MovieRepositotyImp();
    private final TicketServise ticketServise = new TicketServiseImp();
    private int choiseUser;
    private final static Scanner scanner = new Scanner(System.in);

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.getAllMovies();
        if (movies.isEmpty()) {
            System.out.println(GET_MOVIES_NO_MOVIES);
        } else {
            System.out.println(GET_MOVIES_LIST_MOVIES);
            movies.stream()
                    .sorted((a1, a2) -> a1.getDateTime().compareTo(a2.getDateTime()))
                    .forEach(a -> System.out.println(GET_MOVIES_ID + a.getId() + SPACING + a.getNameMovie() +
                            GET_MOVIES_AGE_LIMIT + a.getAgeLimit() + GET_MOVIES_DATE + a.getDateTime()));
        }
        return movies;
    }

    @Override
    public boolean redactMovie() {
        LocalDateTime dateTime = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        String nameMovie = SPACING;
        int ageLimit = ZERO;
        String dateTimeString = SPACING;
        List<Movie> movies = getAllMovies();
        List<Integer> idList = movies.stream()
                .map(Movie::getId)
                .toList();
        System.out.println(REDACT_MOVIE_ID_MOVIE);
        choiseUser = ProcessStepFromUser.checkChoiseFromUser(idList);
            for (Movie movie : movies) {
                if (movie.getId().equals(choiseUser)){
                    nameMovie = movie.getNameMovie();
                    ageLimit = movie.getAgeLimit();
                    dateTimeString = movie.getDateTime();
                }
            }
            System.out.println(REDACT_MOVIE_DATE_MOVIE + nameMovie + DASH + dateTimeString);
            do {
                System.out.println(REDACT_MOVIE_NEW_DATE);
                String text = scanner.nextLine();
                try {
                    dateTime = ProcessStepFromUser.checkDateTimeFromUser(text, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println(DATE_INCORRECT);
                }
            } while (dateTime == null);
            dateTimeString = dateTime.format(formatter);
            Movie movie = new Movie(nameMovie,ageLimit,dateTimeString);
            movieRepository.redactMovie(movie, choiseUser);
            log.info(LOG_TIME_MOVIE +nameMovie+ LOG_UPDATE);
            return true;
    }

    @Override
    public boolean deleteMovieById() {
        List<Movie> movies = getAllMovies();
        List<Integer> idList = movies.stream()
                .map(Movie::getId)
                .toList();
        if (movies.isEmpty()) {
            return false;
        }
        System.out.println(DELETE_MOVIE_CHOOSE_ID);
        choiseUser = ProcessStepFromUser.checkIdFromUser(ZERO, idList);
        if (choiseUser == ZERO) {
            return false;
        } else {
            movieRepository.deleteMovieById(choiseUser);
            ticketServise.deleteAllTicketsByIdMovie(choiseUser);
            log.info(LOG_MOVIE_ID +choiseUser+ LOG_DELETE);
            System.out.println(DELETE_MOVIE_SUCCESS);
            return true;
        }
    }

    @Override
    public String createMovie() {
        LocalDateTime dateTime = null;
        String nameMovie = SPACING;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        System.out.println(CREATE_MOVIE_LIST);
        NameOfMovie[] nameOfMovies = NameOfMovie.values();
        for (NameOfMovie nameOfMovie : nameOfMovies) {
            System.out.println(nameOfMovie.toString());
        }
        System.out.println(CREATE_MOVIE_CHOOSE);
        choiseUser = ProcessStepFromUser.returnStep(ZERO, TEN);
        if (choiseUser != ZERO) {
            do {
                System.out.println(CREATE_MOVIE_CHOOSE_DATE);
                String text = scanner.nextLine();
                try {
                    dateTime = ProcessStepFromUser.checkDateTimeFromUser(text, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println(DATE_INCORRECT);
                }
            } while (dateTime == null);
            String dateTimeString = dateTime.format(formatter);
            NameOfMovie[] nameOfMovies2 = NameOfMovie.values();
            for (NameOfMovie nameOfMovie : nameOfMovies2) {
                if (nameOfMovie.getCount() == choiseUser) {
                    Movie movie = new Movie(nameOfMovie.getTranslation(), nameOfMovie.getAgeLimit(), dateTimeString);
                    movieRepository.createMovie(movie);
                    nameMovie = nameOfMovie.getTranslation();
                    System.out.println(CREATE_MOVIE_SUCCESS);
                    log.info(LOG_MOVIE +nameMovie+ LOG_CREATE);
                }
            }
            return nameMovie;
        } else {
            System.out.println(CREATE_MOVIE_GOOD_BYE);
            return null;
        }
    }

    @Override
    public Integer getIdMovie(String nameMovie) {
        return movieRepository.getIdMovieByNameMovie(nameMovie);
    }
}
