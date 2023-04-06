package com.makarenko.main.repository;

import com.makarenko.main.model.Ticket;
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
public class TicketRepositoryImp implements TicketRepository{

    @Override
    public void createAllTickets(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            try (Connection connection = ConnectionManager.open()) {
                PreparedStatement statement =
                        connection.prepareStatement(TICKET_REPOSITORY_COMMAND_1);
                statement.setInt(ONE, ticket.getIdMovie());
                statement.setInt(TWO, ticket.getPlace());
                statement.setInt(THREE, ticket.getPrice());
                statement.execute();
            } catch (SQLException e) {
                log.warn(LOG_LIST_TICKET_ERROR +e);
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean deleteAllTicketsByIdMovie(int idMovie) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(TICKET_REPOSITORY_COMMAND_2);
            statement.setInt(ONE, idMovie);
            statement.execute();
            return true;
        } catch (SQLException e) {
            log.warn(LOG_DELETE_TICKET_ERROR +e);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Ticket> getAllTicketsByIdMovie(int idMovie) {
        List<Ticket> tickets = new ArrayList<>();
        try(Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(TICKET_REPOSITORY_COMMAND_3);
            statement.setInt(ONE, idMovie);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt(MOVE_REPOSITORY_ID);
                Integer idPerson = resultSet.getInt(TICKET_REPOSITORY_ID_PERSON);
                int place = resultSet.getInt(TICKET_REPOSITORY_PLACE);
                int price = resultSet.getInt(TICKET_REPOSITORY_PRICE);
                Ticket ticket = new Ticket(id, idMovie, idPerson, place, price);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            log.warn(LOG_GET_TICKET_ERROR +e);
            throw new RuntimeException(e);
        }
        return tickets;
    }

    @Override
    public List<Ticket> getAllTicketsByIdPerson(Integer idPerson) {
        List<Ticket> tickets = new ArrayList<>();
        try(Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(TICKET_REPOSITORY_COMMAND_4);
            statement.setInt(ONE, idPerson);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt(MOVE_REPOSITORY_ID);
                int idMovie = resultSet.getInt(TICKET_REPOSITORY_ID_MOVIE);
                int place = resultSet.getInt(TICKET_REPOSITORY_PLACE);
                int price = resultSet.getInt(TICKET_REPOSITORY_PRICE);
                Ticket ticket = new Ticket(id, idMovie, idPerson, place, price);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            log.warn(LOG_GET_PERSON_TICKET_ERROR +e);
            throw new RuntimeException(e);
        }
        return tickets;
    }

    @Override
    public boolean buyTicket(int idMovie, int idPerson, int place) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement(TICKET_REPOSITORY_COMMAND_5);
            statement.setInt(ONE, idPerson);
            statement.setInt(TWO, idMovie);
            statement.setInt(THREE, place);
            statement.execute();
            return true;
        } catch (SQLException e) {
            log.warn(LOG_BUY_TICKET_ERROR +e);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean recoverTicketByIdTicket(Integer idTicket) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement(TICKET_REPOSITORY_COMMAND_6);
            statement.setObject(ONE, null);
            statement.setInt(TWO, idTicket);
            statement.execute();
            return true;
        } catch (SQLException e) {
            log.warn(LOG_RECOVER_TICKET_ERROR +e);
            e.printStackTrace();
            return false;
        }
    }
}
