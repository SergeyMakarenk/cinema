package com.makarenko.main.repository;

import com.makarenko.main.model.Movie;
import com.makarenko.main.util.ConnectionManager;
import lombok.extern.slf4j.Slf4j;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.makarenko.main.util.Constants.*;

@Slf4j
public class MovieRepositotyImp implements MovieRepository{

    @Override
    public boolean createMovie(Movie movie) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement(MOVE_REPOSITORY_COMMAND_1);
            statement.setString(ONE, movie.getNameMovie());
            statement.setInt(TWO, movie.getAgeLimit());
            statement.setString(THREE, movie.getDateTime());
            statement.execute();
            return true;
        } catch (SQLException e) {
            log.warn(LOG_MOVIE +movie.getNameMovie()+ LOG_NOT_CREATE +e);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        try(Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(MOVE_REPOSITORY_COMMAND_2);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt(MOVE_REPOSITORY_ID);
                String nameMovie = resultSet.getString(MOVE_REPOSITORY_NAME);
                int ageLimit = resultSet.getInt(MOVE_REPOSITORY_AGE);
                String dateTime = resultSet.getString(MOVE_REPOSITORY_DATE);
                Movie movie = new Movie(id, nameMovie, ageLimit, dateTime);
                movies.add(movie);
            }
        } catch (SQLException e) {
            log.warn(LOG_LIST_ERROR);
            throw new RuntimeException(e);
        }
        return movies;
    }

    @Override
    public boolean redactMovie(Movie movie, Integer id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement(MOVE_REPOSITORY_COMMAND_4);
            statement.setString(ONE, movie.getNameMovie());
            statement.setInt(TWO, movie.getAgeLimit());
            statement.setString(THREE, movie.getDateTime());
            statement.setInt(FOUR, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            log.warn(LOG_MOVIE +movie.getNameMovie()+ LOG_NOT_UPDATE +e);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Integer getIdMovieByNameMovie(String name) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(MOVE_REPOSITORY_COMMAND_3);
            statement.setString(ONE, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(MOVE_REPOSITORY_ID);
            }
        } catch (SQLException e) {
            log.warn(LOG_NOT_UPDATE +e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteMovieById(int id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(MOVE_REPOSITORY_COMMAND_5);
            statement.setInt(ONE, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            log.warn(LOG_MOVIE_ID + id +  LOG_MOVIE_UPDATE_ERROR +e);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Movie getMovieByIdMovie(int idMovie) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(MOVE_REPOSITORY_COMMAND_6);
            statement.setInt(ONE, idMovie);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                String nameMovie = resultSet.getString(MOVE_REPOSITORY_NAME);
                int age = resultSet.getInt(MOVE_REPOSITORY_AGE);
                String dateTime = resultSet.getString(MOVE_REPOSITORY_DATE);
                return new Movie(idMovie, nameMovie, age, dateTime);
            }
        } catch (SQLException e) {
            log.warn(LOG_GET_MOVIE_ERROR + idMovie + SPACING +e);
            e.printStackTrace();
        }
        return null;
    }
}
