package com.ibm.cube.api.dto;

import com.ibm.cube.api.entity.Employee;
import com.ibm.cube.api.entity.Reservation;
import com.ibm.cube.api.entity.Timeslot;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class ReservationDtoUnitTest {

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertReservationEntityToReservationDto_thenCorrect() {
        Reservation reservation = new Reservation();
        reservation.setId("123");
        reservation.setDuration(60);
        reservation.setTime(LocalDateTime.parse("2023-01-01T13:00:00.000Z", DateTimeFormatter.ISO_ZONED_DATE_TIME));
        reservation.setReserver(new Employee("John", "John@email.com"));

        ReservationDto reservationDto = modelMapper.map(reservation, ReservationDto.class);
        assertEquals("Verify ID", reservation.getId(), reservationDto.getId());
        assertEquals("Verify Duration", reservation.getDuration(), reservationDto.getDuration());
        assertEquals("Verify Reserver", reservation.getReserver(), reservationDto.getReserver());
    }

    @Test
    public void whenConvertReservationDtoToReservationEntity_thenCorrect() {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId("123");
        reservationDto.setTime(LocalDateTime.parse("2023-01-01T13:00:00.000Z", DateTimeFormatter.ISO_ZONED_DATE_TIME));
        reservationDto.setDuration(60);
        reservationDto.setReserver(new Employee("John", "John@email.com"));

        Reservation reservation = modelMapper.map(reservationDto, Reservation.class);
        assertEquals("Verify ID", reservation.getId(), reservationDto.getId());
        assertEquals("Verify Duration", reservation.getDuration(), reservationDto.getDuration());
        assertEquals("Verify Reserver", reservation.getReserver(), reservationDto.getReserver());
    }

    @Test
    public void whenConvertTimeslotEntityToTimeslotDto_thenCorrect() {
        Timeslot timeslot = new Timeslot("TestRoomID", "TestRoom",
                LocalDateTime.parse("2022-11-28T13:00:00Z", DateTimeFormatter.ISO_ZONED_DATE_TIME));

        TimeSlotDto timeSlotDto = modelMapper.map(timeslot, TimeSlotDto.class);
        assertEquals("Verify Room ID", timeslot.getRoomId(), timeSlotDto.getRoomId());
        assertEquals("Verify Room Name", timeslot.getRoomName(), timeSlotDto.getRoomName());
        assertTrue("Verify Time", timeSlotDto.getTime().equals(timeSlotDto.getTime()));
    }

    @Test
    public void whenConvertTimeslotDtoTimeslotEntity_thenCorrect() {
        TimeSlotDto timeSlotDto = new TimeSlotDto();
        timeSlotDto.setRoomId("TestRoomID");
        timeSlotDto.setRoomName("TestRoom");
        timeSlotDto.setTime(LocalDateTime.parse("2022-11-28T13:00:00Z", DateTimeFormatter.ISO_ZONED_DATE_TIME));

        Timeslot timeslot = modelMapper.map(timeSlotDto, Timeslot.class);
        assertEquals("Verify Room ID", timeslot.getRoomId(), timeSlotDto.getRoomId());
        assertEquals("Verify Room Name", timeslot.getRoomName(), timeSlotDto.getRoomName());
        assertTrue("Verify Time", timeSlotDto.getTime().equals(timeSlotDto.getTime()));

    }
}
