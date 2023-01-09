package com.ibm.cube.api.controller;

import com.ibm.cube.api.dto.ReservationDto;
import com.ibm.cube.api.dto.TimeSlotDto;
import com.ibm.cube.api.entity.Reservation;
import com.ibm.cube.api.entity.Timeslot;
import com.ibm.cube.api.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rooms")
public class ReservationController {

    private static final int ALLOWED_INTERVAL = 60;
    public static final int DEFAULT_DURATION = 60;
    private static final int MAX_DURATION = 120;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/reservations/{timeString}")
    @ResponseBody
    public List<TimeSlotDto> getAllTimeslotsByDate(@PathVariable String timeString) {
        LocalDate date;
        try {
            date = LocalDateTime.parse(timeString, DateTimeFormatter.ISO_ZONED_DATE_TIME).toLocalDate();
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Date in incorrect format, must use ISO format(YYYY-MM-DDThh:mm:ss)");
        }
        List<Timeslot> timeslots = reservationService.getAllTimeslotsByDate(date, ALLOWED_INTERVAL);
        return timeslots.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/{roomID}/reservations")
    @ResponseBody
    public List<ReservationDto> getReservations(@PathVariable String roomID) {
        List<Reservation> reservations = reservationService.getReservations(roomID);
        return reservations.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/{roomID}/reservations/findByDate/{timeString}")
    @ResponseBody
    public List<ReservationDto> getReservationsByDate(@PathVariable String roomID, @PathVariable String timeString) {
        LocalDateTime time;
        try {
            time = LocalDateTime.parse(timeString, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Date in incorrect format, must use ISO format(YYYY-MM-DDThh:mm:ss)");
        }
        List<Reservation> reservations = reservationService.getReservationsByDate(roomID, time);
        return reservations.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/{roomID}/reservations/findByDateAndTime/{timeString}")
    @ResponseBody
    public ReservationDto getReservationByDateAndTime(@PathVariable String roomID, @PathVariable String timeString) {
        LocalDateTime time;
        try {
            time = LocalDateTime.parse(timeString, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Date in incorrect format, must use ISO format(YYYY-MM-DDThh:mm:ss)");
        }
        Reservation reservation = reservationService.getReservationByDateAndTime(roomID, time);
        return convertToDto(reservation);
    }

    @PostMapping("/{roomID}/reservations")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ReservationDto createReservation(@PathVariable String roomID, @RequestBody ReservationDto reservationDto) {
        if (reservationDto.getDuration() == 0)
            reservationDto.setDuration(DEFAULT_DURATION);
        else if (reservationDto.getDuration() % ALLOWED_INTERVAL != 0)
            reservationDto.setDuration(DEFAULT_DURATION);
        else if (reservationDto.getDuration() > MAX_DURATION)
            reservationDto.setDuration(MAX_DURATION);
        Reservation reservation;
        try {
            reservation = convertToEntity(reservationDto);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Date in incorrect format, must use ISO format(YYYY-MM-DDThh:mm:ss)");
        }
        Reservation reservationCreated = reservationService.createReservation(roomID, reservation);
        return convertToDto(reservationCreated);
    }

    @DeleteMapping("/{roomID}/reservations/delete/{id}")
    @ResponseBody
    public String deleteReservationById(@PathVariable String roomID, @PathVariable String id) {
        return reservationService.deleteReservationById(roomID, id);
    }

    private ReservationDto convertToDto(Reservation reservation) {
        ReservationDto reservationDto = modelMapper.map(reservation, ReservationDto.class);
        reservationDto.setTime(reservation.getTime());
        return reservationDto;
    }

    private Reservation convertToEntity(ReservationDto reservationDto) throws DateTimeParseException {
        Reservation reservation = modelMapper.map(reservationDto, Reservation.class);
        reservation.setTime(reservationDto.getTime());
        if (reservationDto.getId() != null) {
            Reservation oldReservation = reservationService.getReservationById(reservationDto.getId());
            reservation.setId(oldReservation.getId());
        }
        return reservation;
    }

    private TimeSlotDto convertToDto(Timeslot timeslot) {
        TimeSlotDto timeSlotDto = modelMapper.map(timeslot, TimeSlotDto.class);
        timeSlotDto.setTime(timeslot.getTime());
        return timeSlotDto;
    }
}
