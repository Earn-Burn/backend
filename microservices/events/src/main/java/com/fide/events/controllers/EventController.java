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
        return eventService.saveEvent(event);
    }
}
