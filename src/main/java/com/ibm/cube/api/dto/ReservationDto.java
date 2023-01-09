package com.ibm.cube.api.dto;

import com.ibm.cube.api.entity.Employee;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class ReservationDto {

    private String id;
    private String time;
    private int duration;
    private String reason;
    private Employee reserver;

    public LocalDateTime getTime() throws DateTimeParseException {
        return LocalDateTime.parse(this.time);
    }

    public void setTime(LocalDateTime time) {
        this.time = time.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "ReservationDto{" +
                "time='" + time + '\'' +
                ", duration=" + duration +
                ", reason='" + reason + '\'' +
                ", reserver=" + reserver +
                '}';
    }
}
