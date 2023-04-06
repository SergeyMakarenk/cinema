package com.makarenko.main.model;

import static com.makarenko.main.util.Constants.*;

public enum NameOfMovie {
    TERMINATOR(ONE,MOVIE_1, FOURTEEN),
    MATRICA(TWO,MOVIE_2, FOURTEEN),
    BURATINO(THREE,MOVIE_3, EIGHTEEN),
    BATMAN(FOUR,MOVIE_4, FOURTEEN),
    ELKI(FIVE,MOVIE_5, TWELVE),
    BOGATIRI(SIX,MOVIE_6, SIX),
    IRONIA(SEVEN,MOVIE_7, TWELVE),
    PIRATS(EIGHT,MOVIE_8, TWELVE),
    MOROZ(NINE,MOVIE_9, TWENTY_ONE),
    NU_POGODI(TEN,MOVIE_10, SIX);

    private final int count;
    private final String translation;
    private final int ageLimit;


    NameOfMovie(int count, String translation, int ageLimit) {
        this.count = count;
        this.translation = translation;
        this.ageLimit = ageLimit;
    }

    public int getCount() {
        return count;
    }

    public String getTranslation() {
        return translation;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    @Override
    public String toString() {
        return MOVIE_NUMBER + count + DASH + translation + AGE_LIMIT + ageLimit + AGE;
    }
}
