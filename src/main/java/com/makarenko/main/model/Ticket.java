package com.makarenko.main.model;

public class Ticket {
    private Integer id;
    private Integer idMovie;
    private Integer idPerson;
    private Integer place;
    private Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdMovie() {
        return idMovie;
    }

    public void setNameMovie(Integer idMovie) {
        this.idMovie = idMovie;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Ticket(Integer idMovie, Integer place, Integer price) {
        this.idMovie = idMovie;
        this.place = place;
        this.price = price;
    }

    public Ticket(Integer id, Integer idMovie, Integer idPerson, Integer place, Integer price) {
        this.id = id;
        this.idMovie = idMovie;
        this.idPerson = idPerson;
        this.place = place;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", idMovie=" + idMovie +
                ", idPerson=" + idPerson +
                ", place=" + place +
                ", price=" + price +
                '}';
    }
}
