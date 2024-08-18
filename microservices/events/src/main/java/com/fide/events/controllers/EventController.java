package com.fide.events.controllers;

import com.fide.events.models.Event;
import com.fide.events.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/events/")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5175")
public class EventController {

    private final EventService eventService;


    @GetMapping("all")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("event/{id}")
    public Event getEvent(@PathVariable("id") String id) {
        return eventService.getEventById(id);
    }

    @PostMapping("save")
    public Event saveEvent(@RequestBody Event event) {
        System.out.println("Received event: " + event);
        return eventService.saveEvent(event);
    }

    @PutMapping("update/{id}")
    public Event updateEvent(@PathVariable("id") String id, @RequestBody Event event) {
        System.out.println("Updating event with id: " + id);
        System.out.println("Received event data: " + event);
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping("delete/{id}")
    public void deleteEvent(@PathVariable("id") String id) {
        eventService.deleteEvent(id);
    }


}
