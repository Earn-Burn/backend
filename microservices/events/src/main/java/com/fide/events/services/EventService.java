package com.fide.events.services;

import com.fide.events.models.Event;
import com.fide.events.repositories.ConditionRepository;
import com.fide.events.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final ConditionRepository conditionRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventByCode(String Code) {
        return eventRepository.findByCode(Code);
    }

    public Event getEventById(String id) {
        return eventRepository.findById(id).get();
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }
}
