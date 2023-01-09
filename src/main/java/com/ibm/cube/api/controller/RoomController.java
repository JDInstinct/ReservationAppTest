package com.ibm.cube.api.controller;

import com.ibm.cube.api.entity.Room;
import com.ibm.cube.api.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("")
    public List<Room> getRooms() {
        return roomService.getRooms();
    }

    @PostMapping("")
    public Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @GetMapping("/{id}")
    public Room getRoomByName(@PathVariable String id) {
        return roomService.findByID(id);
    }

    @PatchMapping("/update")
    public Room updateRoom(@RequestBody Room room) {
        return roomService.updateRoom(room);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRoom(@PathVariable String id) {
        return roomService.deleteByID(id);
    }
}
