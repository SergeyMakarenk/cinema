package com.makarenko.main.controller;

import com.makarenko.main.model.Person;
import com.makarenko.main.model.RoleOfPerson;
import com.makarenko.main.servise.*;
import com.makarenko.main.util.ProcessStepFromUser;
import lombok.extern.slf4j.Slf4j;
import static com.makarenko.main.util.Constants.*;
@Slf4j
public class PersonController {
    private Person person;
    private int choiseUser;

    public void startMenu(){
        PersonServise personServise = new PersonServiseImp();
        do {
            System.out.println(WELCOME_ALL);
            choiseUser = ProcessStepFromUser.returnStep(ZERO, TWO);
            switch (choiseUser) {
                case ONE -> person = personServise.createPerson();
                case TWO -> person = personServise.getPerson();
                case ZERO -> {
                    System.out.println(GOOD_BYE);
                    System.exit(ZERO);
                }
            }
        } while (person == null);
        chooseRolePerson(person);
    }

    private void chooseRolePerson(Person person){
        RoleOfPerson role = RoleOfPerson.valueOf(person.getRole());
        switch (role){
            case USER -> workPersonUser(person);
            case MANAGER -> workPersonManager(person);
            case ADMIN -> workPersonAdmin(person);
        }
    }

    private void workPersonUser(Person person) {
        PersonUserServise personUserServise = new PersonServiseImp();
        do {
            System.out.println(MENU_USER);
            choiseUser = ProcessStepFromUser.returnStep(ZERO, THREE);
            switch (choiseUser) {
                case ONE -> personUserServise.viewMovie();
                case TWO -> personUserServise.buyOrRecoverTicket(person);
                case THREE -> personUserServise.viewTickets(person);
                case ZERO -> {
                    log.info(LOG_USER +person.getUsername()+ LOG_OUT);
                    startMenu();
                }
            }
        } while (choiseUser != ZERO);
    }

    private void workPersonManager(Person person) {
        PersonManagerServise personManagerServise = new PersonServiseImp();
        do {
            System.out.println(MENU_MANAGER);
            choiseUser = ProcessStepFromUser.returnStep(ZERO, FIVE);
            switch (choiseUser) {
                case ONE -> personManagerServise.viewMovie();
                case TWO -> personManagerServise.buyOrRecoverTicket(person);
                case THREE -> personManagerServise.viewTickets(person);
                case FOUR -> personManagerServise.redactMovie();
                case FIVE -> personManagerServise.buyOrRecoverTicketOfUser();
                case ZERO -> {
                    log.info(LOG_USER +person.getUsername()+ LOG_OUT);
                    startMenu();
                }
            }
        } while (choiseUser != ZERO);
    }

    private void workPersonAdmin(Person person) {
        PersonAdminServise personAdminServise = new PersonServiseImp();
        do {
            System.out.println(MENU_ADMIN);
            choiseUser = ProcessStepFromUser.returnStep(ZERO, SEVEN);
            switch (choiseUser) {
                case ONE -> personAdminServise.viewMovie();
                case TWO -> personAdminServise.buyOrRecoverTicket(person);
                case THREE -> personAdminServise.viewTickets(person);
                case FOUR -> personAdminServise.redactMovie();
                case FIVE -> personAdminServise.buyOrRecoverTicketOfUser();
                case SIX -> personAdminServise.deleteRedactPerson();
                case SEVEN -> personAdminServise.deleteCreateMovie();
                case ZERO -> {
                    log.info(LOG_USER +person.getUsername()+ LOG_OUT);
                    startMenu();
                }
            }
        } while (choiseUser != ZERO);
    }
}
