package com.makarenko.main.servise;

import com.makarenko.main.model.Person;
import com.makarenko.main.model.Ticket;
import com.makarenko.main.repository.MovieRepository;
import com.makarenko.main.repository.MovieRepositotyImp;
import com.makarenko.main.repository.TicketRepository;
import com.makarenko.main.repository.TicketRepositoryImp;
import com.makarenko.main.util.ProcessStepFromUser;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import static com.makarenko.main.util.Constants.*;

@Slf4j
public class TicketServiseImp implements TicketServise {
    private final TicketRepository ticketRepository = new TicketRepositoryImp();
    private final MovieRepository movieRepository = new MovieRepositotyImp();

    @Override
    public boolean createAllTickets(int idMovie) {
        List<Ticket> tickets = new ArrayList<>();
        System.out.println(PRICE_TICKET);
        int price = ProcessStepFromUser.returnStep(FIVE, TWENTY_FIVE);
        for (int place = ONE; place <= TEN; place++) {
            Ticket ticket = new Ticket(idMovie, place, price);
            tickets.add(ticket);
        }
        ticketRepository.createAllTickets(tickets);
        System.out.println(CREATE_TICKETS);
        return true;
    }

    @Override
    public boolean deleteAllTicketsByIdMovie(int idMovie) {
        ticketRepository.deleteAllTicketsByIdMovie(idMovie);
        return true;
    }

    @Override
    public List<Ticket> getAllTickets(Integer idMovie) {
        return ticketRepository.getAllTicketsByIdMovie(idMovie);
    }

    @Override
    public boolean buyTicket(Person person, List<Integer> idMovies) {
        System.out.println(BUY_TICKETS_ENTER_ID);
        int choiseUserIdMovie = ProcessStepFromUser.checkChoiseFromUser(idMovies);
        int ageLimit = movieRepository.getMovieByIdMovie(choiseUserIdMovie).getAgeLimit();
        if (person.getAge() < ageLimit) {
            System.out.println(BUY_TICKETS_YANG);
            return false;
        }
            List<Ticket> tickets = getAllTickets(choiseUserIdMovie);
            List<Integer> freePlaces = tickets.stream()
                    .filter(a->a.getIdPerson() == ZERO)
                    .map(Ticket::getPlace)
                    .toList();
            if (freePlaces.isEmpty()){
                System.out.println(BUY_TICKETS_NO_PLACE);
                return false;
            } else {
                System.out.println(BUY_TICKETS_FREE_PLACE);
                tickets.stream()
                        .filter(a -> a.getIdPerson() == ZERO)
                        .forEach(a -> System.out.println(BUY_TICKETS_PLACE + a.getPlace() + BUY_TICKETS_PRICE + a.getPrice()));
                System.out.println(BUY_TICKETS_CHOOSE_PLACE);
                int choiseUserPlace = ProcessStepFromUser.checkChoiseFromUser(freePlaces);
                ticketRepository.buyTicket(choiseUserIdMovie, person.getId(), choiseUserPlace);
                log.info(LOG_USER +person.getUsername()+ LOG_BUY_TICKET +choiseUserIdMovie);
                System.out.println(BUY_TICKETS_SUCCESS);
                return true;
            }
        }

    @Override
    public boolean recoverTicket(Integer idPerson) {
        List<Ticket> tickets = ticketRepository.getAllTicketsByIdPerson(idPerson);
        List<Integer> idTickets = new ArrayList<>();
        if (tickets.isEmpty()) {
            System.out.println(RECOVER_TICKETS_NO_TICKETS);
            return false;
        } else {
            System.out.println(RECOVER_TICKETS_LIST_TICKETS);
            for (Ticket ticket : tickets) {
                idTickets.add(ticket.getId());
                String nameMovie = movieRepository.getMovieByIdMovie(ticket.getIdMovie()).getNameMovie();
                String dateTime = movieRepository.getMovieByIdMovie(ticket.getIdMovie()).getDateTime();
                System.out.println(ID_TICKETS + ticket.getId() + MOVIE
                        + nameMovie + DATE + dateTime + PLACE + ticket.getPlace()
                        + PRICE + ticket.getPrice());
            }
        }
            System.out.println(RECOVER_TICKETS_DELETE);
            int choiseUser = ProcessStepFromUser.checkChoiseFromUser(idTickets);
            ticketRepository.recoverTicketByIdTicket(choiseUser);
            log.info(LOG_PERSON_ID +idPerson+ LOG_RECOVER_TICKET);
            System.out.println(RECOVER_TICKETS_SUCCESS);
            return true;
    }


    @Override
    public void showAllTicketsByIdPerson(Integer idPerson) {
        List<Ticket> tickets = ticketRepository.getAllTicketsByIdPerson(idPerson);
        if (tickets.isEmpty()) {
            System.out.println(SHOW_TICKETS_NO_TICKETS);
        } else {
            System.out.println(SHOW_TICKETS_LIST_TICKETS);
            for (Ticket ticket : tickets) {
                String nameMovie = movieRepository.getMovieByIdMovie(ticket.getIdMovie()).getNameMovie();
                String dateTime = movieRepository.getMovieByIdMovie(ticket.getIdMovie()).getDateTime();
                System.out.println(ID_TICKETS + ticket.getId() + MOVIE + nameMovie + DATE
                        + dateTime + PLACE + ticket.getPlace() + PRICE + ticket.getPrice());
            }
        }
    }
}
