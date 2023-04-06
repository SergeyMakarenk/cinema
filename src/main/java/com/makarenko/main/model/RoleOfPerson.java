package com.makarenko.main.model;

import static com.makarenko.main.util.Constants.*;

public enum RoleOfPerson {

    USER(ROLE_USER),
    MANAGER(ROLE_MANAGER),
    ADMIN(ROLE_ADMIN);

    private final String translation;

    RoleOfPerson (String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    @Override
    public String toString() {
        return translation;
    }


}
