package com.fide.events.repositories;

import com.fide.events.models.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
    public Event findByCode(String code);
}
