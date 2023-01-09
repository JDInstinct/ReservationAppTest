package com.ibm.cube.api.service;

import com.ibm.cube.api.entity.Reservation;
import com.ibm.cube.api.entity.Room;
import com.ibm.cube.api.repository.EmployeeRepository;
import com.ibm.cube.api.repository.ReservationRepository;
import com.ibm.cube.api.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    public Room createRoom(Room room) throws RuntimeException {
        Set<String> roomNames = roomRepository
                .findAll()
                .stream()
                .map(Room::getName)
                .collect(Collectors.toSet());
        if (roomNames.contains(room.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A room with this name already exists");
        }
        if (room.getReservations() != null) {
            for (Reservation res : room.getReservations()) {
                employeeRepository.save(res.getReserver());
            }
            reservationRepository.saveAll(room.getReservations());
        } else {
            room.setReservations(new ArrayList<>());
        }
        return roomRepository.save(room);
    }

    public Room updateRoom(Room room) {
        Room oldRoom = roomRepository.findById(room.getId()).orElse(null);
        if (room.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Must Provide ID when updating");
        }
        if (room.getName() != null) {
            assert oldRoom != null;
            oldRoom.setName(room.getName());
        }
        if (room.getReservations() != null) {
            assert oldRoom != null;
            oldRoom.setReservations(room.getReservations());
        }
        assert oldRoom != null;
        return roomRepository.save(oldRoom);
    }

    public Room findByID(String id) {
        return roomRepository.findById(id).orElse(null);
    }

    public String deleteByID(String id) {
        if (roomRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No room found with name {" +  id + "}");
        }
        Room room = roomRepository.findById(id).get();
        for (Reservation r :
                room.getReservations()) {
            reservationRepository.deleteById(r.getId());
        }
        roomRepository.deleteById(id);
        return "Room has been successfully deleted";
    }
}

