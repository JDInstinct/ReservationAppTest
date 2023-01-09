package com.ibm.cube.api.entity;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Timeslot implements Comparable<Timeslot> {
    private String roomName;
    private String roomId;

    private LocalDateTime time;

    public Timeslot() {
        this.roomName = null;
        this.time = null;
    }

    public Timeslot(String roomId, String roomName, LocalDateTime startTime) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.time = startTime;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Timeslot {roomName=" + roomName + ", roomId=" + roomId + ", time=" + time + "}";
    }

    @Override
    public int compareTo(@NotNull Timeslot o) {
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
