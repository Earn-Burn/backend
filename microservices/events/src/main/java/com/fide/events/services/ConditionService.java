package com.fide.events.services;

import com.fide.events.models.Condition;
import com.fide.events.repositories.ConditionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConditionService {

    private final ConditionRepository conditionRepository;

    public List<Condition> findAll() {
        return conditionRepository.findAll();
    }

    public  List<Condition> findConditionByType(String type) {
        return conditionRepository.findByType(type);
    }

    public Condition saveCondition(Condition condition) {
        return conditionRepository.save(condition);
    }

    public List<Condition> findConditionsByCodes(List<Integer> codes) {
        return conditionRepository.findByCodeIn(codes);
    }
}
