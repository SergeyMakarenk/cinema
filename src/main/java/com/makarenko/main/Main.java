package com.makarenko.main;

import com.makarenko.main.controller.PersonController;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        PersonController personController = new PersonController();
        personController.startMenu();
    }
}
