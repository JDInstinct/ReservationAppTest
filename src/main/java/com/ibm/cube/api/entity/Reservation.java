package com.ibm.cube.api.entity;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.HTMLDocument;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Iterator;

@Document("reservation")
public class Reservation implements Comparable<Reservation> {

    @Id
    private String id;
    private LocalDateTime time;
    private int duration;

    private String reason;

    @DBRef
    private Employee reserver;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Employee getReserver() {
        return reserver;
    }

    public void setReserver(Employee reserver) {
        this.reserver = reserver;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", duration=" + duration +
                ", reason='" + reason + '\'' +
                ", reserver=" + reserver +
                '}';
    }

    @Override
    public int compareTo(@NotNull Reservation o) {
        if (this.equals(o)) return 0;
        if (this.getTime().isBefore(o.getTime())) {
            return -1;
        } else if (this.getTime().isAfter(o.getTime())) {
            return 1;
        } else {
            return 0;
        }
    }
}
