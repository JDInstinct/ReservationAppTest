package com.ibm.cube.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "room")
public class Room {

    @Id
    private String id;

    private String name;

    @DBRef
    private List<Reservation> reservations;

    public Room(String id, String name, List<Reservation> reservations) {
        this.id = id;
        this.name = name;
        this.reservations = reservations;
    }

    public String getName() {
        return name;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", reservations=" + reservations +
                '}';
    }
}
