package com.makarenko.main.model;

public class Movie {

    private Integer id;
    private String nameMovie;
    private Integer ageLimit;
    private String dateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public Integer getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Movie(String nameMovie, Integer ageLimit, String dateTime) {
        this.nameMovie = nameMovie;
        this.ageLimit = ageLimit;
        this.dateTime = dateTime;
    }

    public Movie(Integer id, String nameMovie, Integer ageLimit, String dateTime) {
        this.id = id;
        this.nameMovie = nameMovie;
        this.ageLimit = ageLimit;
        this.dateTime = dateTime;
    }
}
