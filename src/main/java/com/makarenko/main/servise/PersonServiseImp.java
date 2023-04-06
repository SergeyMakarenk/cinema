package com.makarenko.main.servise;

import com.makarenko.main.model.Movie;
import com.makarenko.main.model.Person;
import com.makarenko.main.model.RoleOfPerson;
import com.makarenko.main.repository.PersonRepository;
import com.makarenko.main.repository.PersonRepositoryImp;
import com.makarenko.main.util.ProcessStepFromUser;
import lombok.extern.slf4j.Slf4j;
import java.security.SecureRandom;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import static com.makarenko.main.util.Constants.*;

@Slf4j
public class PersonServiseImp implements PersonServise, PersonAdminServise {
    private final PersonRepository personRepository = new PersonRepositoryImp();
    private final MovieServise movieServise = new MovieServiseImp();
    private final TicketServise ticketServise = new TicketServiseImp();
    private final static Scanner scanner = new Scanner(System.in);
    private int choiseUser;
    private final SecureRandom random = new SecureRandom();

    @Override
    public Person createPerson() {
        String username = SPACING;
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        while (true) {
            System.out.print(CREATE_PERSON_ENTER_LOGIN);
            Pattern pattern = Pattern.compile(CREATE_PERSON_PATTERN_LOGIN);
            username = ProcessStepFromUser.checkStepUserWithRegex(pattern);
            if (personRepository.getPersonByUsername(username) != null) {
                System.out.println(CREATE_PERSON_EXIST);
            } else {
                break;
            }
        }
        System.out.print(CREATE_PERSON_ENTER_PASSWORD);
        Pattern pattern1 = Pattern.compile(CREATE_PERSON_PATTERN_PASSWORD);
        String password = ProcessStepFromUser.checkStepUserWithRegex(pattern1);

        System.out.print(CREATE_PERSON_ENTER_AGE);
        int age = ProcessStepFromUser.returnStep(ZERO, HUNDRED);
        Person person = new Person(username, password, age);
        person.setRole(RoleOfPerson.USER.getTranslation());
        person.setSalt(salt);
        personRepository.createPerson(person);
        log.info(LOG_PERSON_CREATE + person.getUsername());
        System.out.println(CREATE_PERSON_SUCCESS);
        return personRepository.getPersonByUsername(username);
    }

    @Override
    public Person getPerson() {
        String username = SPACING;
        String password = SPACING;
        while (true) {
            System.out.print(GET_PERSON_ENTER_LOGIN);
            username = scanner.nextLine();
            Person person = personRepository.getPersonByUsername(username);
            if (username.equals(ZERO1)) {
                return null;
            } else if (person != null) {
                break;
            } else {
                System.out.println(GET_PERSON_NOT_PERSON);
            }
        }
        Person person = personRepository.getPersonByUsername(username);
        while (true) {
            System.out.print(GET_PERSON_ENTER_PASSWORD);
            password = scanner.nextLine();
            if (password.equals(ZERO1)) {
                return null;
            } else if (password.equals(person.getPassword())) {
                log.info(LOG_USER +person.getUsername()+ LOG_PERSON_ENTER);
                System.out.println(GET_PERSON_ENTER_SUCCESS);
                return person;
            } else {
                System.out.println(GET_PERSON_ENTER_WRONG);
            }
        }
    }

    @Override
    public void deleteRedactPerson() {
        do {
            System.out.println(REDACT_PERSON_MENU);
            choiseUser = ProcessStepFromUser.returnStep(ZERO, THREE);
            switch (choiseUser) {
                case ONE -> deletePersonByAdmin();
                case TWO -> redactPersonByAdmin();
                case THREE -> createPerson();
                case ZERO -> System.out.println(REDACT_PERSON_EXIT);
            }
        } while (choiseUser != ZERO);
    }

    @Override
    public void deleteCreateMovie() {
        String nameMovie = SPACING;
        do {
            System.out.println(CREATE_DELETE_MOVIE_MENU);
            choiseUser = ProcessStepFromUser.returnStep(ZERO, TWO);
            switch (choiseUser) {
                case ONE -> {
                    nameMovie = movieServise.createMovie();
                    Integer id = movieServise.getIdMovie(nameMovie);
                    ticketServise.createAllTickets(id);
                }
                case TWO -> movieServise.deleteMovieById();
                case ZERO -> System.out.println(REDACT_PERSON_EXIT);
            }
        } while (choiseUser != ZERO);
    }

    @Override
    public boolean redactMovie() {
        if (movieServise.redactMovie()) {
            System.out.println(REDACT_MOVIE_SUCCESS);
        } else {
            System.out.println(REDACT_MOVIE_WRONG);
        }
        System.out.println(REDACT_MOVIE_EXIT);
        choiseUser = ProcessStepFromUser.returnStep(ZERO, ZERO);
        return true;
    }

    @Override
    public boolean buyOrRecoverTicketOfUser() {
        Person personForEdit = null;
        List<Person> persons = personRepository.getAllPersons();
        List<Integer> idList = showListPerson(persons);
        System.out.println(BUY_TICKET_USER_ID);
        choiseUser = ProcessStepFromUser.checkIdFromUser(ZERO, idList);
        if (choiseUser == ZERO) {
            return false;
        } else {
            for (Person person : persons) {
                if (choiseUser == person.getId()) {
                    personForEdit = person;
                }
            }
            buyOrRecoverTicket(personForEdit);
            return true;
        }
    }

    @Override
    public boolean viewMovie() {
        movieServise.getAllMovies();
        System.out.println(REDACT_MOVIE_EXIT);
        choiseUser = ProcessStepFromUser.returnStep(ZERO, ZERO);
        return true;
    }

    @Override
    public void buyOrRecoverTicket(Person person) {
        do {
            System.out.println(BUY_TICKET_MENU);
            choiseUser = ProcessStepFromUser.returnStep(ZERO, TWO);
            switch (choiseUser) {
                case ONE -> {
                    List<Movie> movies = movieServise.getAllMovies();
                    if (!movies.isEmpty()) {
                        List<Integer> idMovies = movies.stream()
                                .map(Movie::getId)
                                .toList();
                        ticketServise.buyTicket(person, idMovies);
                    }
                }
                case TWO -> ticketServise.recoverTicket(person.getId());
                case ZERO -> System.out.println(BUY_TICKET_EXIT);
            }
        } while (choiseUser != ZERO);

    }

    @Override
    public void viewTickets(Person person) {
        ticketServise.showAllTicketsByIdPerson(person.getId());
        System.out.println(REDACT_MOVIE_EXIT);
        choiseUser = ProcessStepFromUser.returnStep(ZERO, ZERO);
    }

    private boolean deletePersonByAdmin() {
        List<Person> persons = personRepository.getAllPersons();
        List<Integer> idList;
        idList = showListPerson(persons);
        System.out.println(DELETE_PERSON_ID);
        choiseUser = ProcessStepFromUser.checkIdFromUser(ZERO, idList);
        if (choiseUser == ZERO) {
            return false;
        } else {
            personRepository.deletePersonById(choiseUser);
            log.info(LOG_PERSON_ID +choiseUser+ LOG_DELETE);
            System.out.println(DELETE_PERSON_SUCCESS);
            return true;
        }
    }

    private boolean redactPersonByAdmin() {
        String username = SPACING;
        String password = SPACING;
        int age = ZERO;
        String role = SPACING;
        List<Person> persons = personRepository.getAllPersons();
        List<Integer> idList;
        idList = showListPerson(persons);
        System.out.println(REDACT_ROLE_MENU);
        choiseUser = ProcessStepFromUser.checkIdFromUser(ZERO, idList);
        if (choiseUser == ZERO) {
            return false;
        } else {
            for (Person person : persons) {
                if (person.getId().equals(choiseUser)) {
                    username = person.getUsername();
                    password = person.getPassword();
                    age = person.getAge();
                    role = person.getRole();
                }
            }
            System.out.println(REDACT_ROLE_CURTAIN + username + DASH + role);
            System.out.println(REDACT_ROLE_CHOOSE);
            int choiseUserRole = ProcessStepFromUser.returnStep(ZERO, THREE);
            role = switch (choiseUserRole) {
                case ONE -> RoleOfPerson.USER.getTranslation();
                case TWO -> RoleOfPerson.MANAGER.getTranslation();
                case THREE -> RoleOfPerson.ADMIN.getTranslation();
                default -> role;
            };
            Person person = new Person(username, password, age, role);
            personRepository.updatePerson(person, choiseUser);
            log.info(LOG_USER +username+ LOG_GET_ROLE +role);
            System.out.println(REDACT_ROLE_SUCCESS);
            return true;
        }
    }

    private List<Integer> showListPerson(List<Person> persons) {
        System.out.println(SHOW_PERSON_SHOW);
        return persons.stream()
                .sorted((a1, a2) -> a1.getRole().compareTo(a2.getRole()))
                .peek(a -> System.out.println(SHOW_PERSON_ID + a.getId() + SHOW_PERSON_NAME + a.getUsername() +
                        SHOW_PERSON_PASSWORD + a.getPassword() + SHOW_PERSON_AGE + a.getAge() +
                        SHOW_PERSON_ROLE + a.getRole()))
                .map(Person::getId)
                .toList();
    }
}
