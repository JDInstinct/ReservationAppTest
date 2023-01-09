package com.ibm.cube.api.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeSlotDto {
    private String roomId;
    private String roomName;
    private String time;

    @Autowired
    private static ConversionService conversionService;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public LocalDateTime getTime() {
        return LocalDateTime.parse(time);
    }

    public String getTimeString() {
        return LocalDateTime.parse(time).format(DateTimeFormatter.ofPattern("h:mm a", Locale.US));
    }

    public void setTime(LocalDateTime time) {
        this.time = time.toString();
    }

}
