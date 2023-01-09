package com.ibm.cube.api.service;

import com.ibm.cube.api.entity.Reservation;
import com.ibm.cube.api.entity.Room;
import com.ibm.cube.api.entity.Timeslot;
import com.ibm.cube.api.repository.EmployeeRepository;
import com.ibm.cube.api.repository.ReservationRepository;
import com.ibm.cube.api.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private static final String DAY_BEGIN = "08:00:00";
    private static final String DAY_END = "16:59:59";

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public Reservation createReservation(String roomID, Reservation reservation) {
        Room room = roomRepository.findById(roomID).orElse(null);
        if (room == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A room with given id{" + roomID + "} was not found");
        List<Reservation> reservations = room.getReservations();
        Set<String> existing = reservations
                .stream()
                .map(res -> res.getTime().toString())
                .collect(Collectors.toSet());
        if (existing.contains(reservation.getTime().toString())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A reservation already exists for this time");
        }
        reservations.add(reservation);
        employeeRepository.save(reservation.getReserver());
        reservationRepository.saveAll(reservations);
        roomRepository.save(room);
        return reservation;
    }

    public List<Reservation> getReservations(String roomID) {
        Room room = roomRepository.findById(roomID).orElse(null);
        if (room == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A room with given id{" + roomID + "} was not found");
        return room.getReservations();
    }

    public List<Reservation> getReservationsByDate(String roomID, LocalDateTime date) {
        Room room = roomRepository.findById(roomID).orElse(null);
        LocalDate resDate = date.toLocalDate();
        if (room == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A room with given id{" + roomID + "} was not found");
        List<Reservation> reservations = room.getReservations();
        return reservations.stream()
                .filter(reservation -> reservation.getTime().toLocalDate().equals(resDate))
                .toList();
    }

    public Reservation getReservationByDateAndTime(String roomID, LocalDateTime time) {
        Room room = roomRepository.findById(roomID).orElse(null);
        if (room == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A room with given id{" + roomID + "} was not found");
        List<Reservation> reservations = room.getReservations();
        return reservations.stream()
                .filter(reservation -> reservation.getTime().equals(time))
                .toList().get(0);
    }

    public Reservation getReservationById(String id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public String deleteReservationById(String roomID, String id) {
        if (reservationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No reservation found with id {" + id + "}");
        }
        if (roomRepository.findById(roomID).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No room found with id {" + id + "}");
        }
        Room room = roomRepository.findById(roomID).get();
        List<Reservation> reservations =
                room.getReservations()
                        .stream()
                        .filter(reservation -> !reservation.getId().equals(id))
                        .toList();
        room.setReservations(reservations);
        roomRepository.save(room);
        reservationRepository.deleteById(id);
        return "Reservation was successfully deleted";
    }

    public List<Timeslot> getAllTimeslotsByDate(LocalDate date, int interval) {
        List<Room> all = roomRepository.findAll();
        List<Timeslot> allTimeslots = new ArrayList<>();
        for (Room room : all) {
            LocalTime timeIterator = LocalTime.parse(DAY_BEGIN);
            LocalTime dayEnd = LocalTime.parse(DAY_END);
            List<Reservation> reservations = new ArrayList<>(room.getReservations()
                    .stream()
                    .filter(reservation -> reservation.getTime().toLocalDate().equals(date))
                    .toList());
            Collections.sort(reservations);
            for (Reservation reservation : reservations) {
                LocalTime resTime = reservation.getTime().toLocalTime();
                while (timeIterator.isBefore(resTime)) {
                    allTimeslots.add(new Timeslot(room.getId(), room.getName(), LocalDateTime.of(date, timeIterator)));
                    timeIterator = timeIterator.plusMinutes(interval);
                }
                timeIterator = timeIterator.plusMinutes(reservation.getDuration());
            }
            while (timeIterator.isBefore(dayEnd)) {
                allTimeslots.add(new Timeslot(room.getId(), room.getName(), LocalDateTime.of(date, timeIterator)));
                timeIterator = timeIterator.plusMinutes(interval);
            }
        }
        Collections.sort(allTimeslots);
        return allTimeslots;
    }
}
