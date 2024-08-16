package com.fide.events.repositories;

import com.fide.events.models.Condition;
import com.fide.events.models.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ConditionRepository extends MongoRepository<Condition, Integer> {
    public Condition findByCode(Integer code);
    List<Condition> findByCodeIn(List<Integer> codes);
    public List<Condition> findByType(String type);
}
