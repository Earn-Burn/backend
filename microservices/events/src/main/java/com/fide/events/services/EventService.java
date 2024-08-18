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

    public Event updateEvent(String id, Event updatedEvent) {
        return eventRepository.findById(id)
                .map(event -> {
                    event.setCode(updatedEvent.getCode());
                    event.setDescription(updatedEvent.getDescription());
                    event.setTypologie(updatedEvent.getTypologie());
                    event.setProduit(updatedEvent.getProduit());
                    event.setPoints(updatedEvent.getPoints());
                    event.setConditionCode(updatedEvent.getConditionCode());
                    return eventRepository.save(event);
                })
                .orElseThrow(() -> new RuntimeException("Event not found with id " + id));
    }



    public void deleteEvent(String id) {
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Event not found with id " + id);
        }
        eventRepository.deleteById(id);
    }
}
