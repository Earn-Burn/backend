package com.fide.events.controllers;

import com.fide.events.models.Condition;
import com.fide.events.services.ConditionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/conditions/")
@AllArgsConstructor
public class ConditionController {

    private final ConditionService conditionService;

    @GetMapping("all")
    public List<Condition> getAllConditions() {
        return conditionService.findAll();
    }

    @GetMapping("conditions")
    public List<Condition> getConditionsByCodes(@RequestParam List<Integer> ids)
    {
        return conditionService.findConditionsByCodes(ids);
}

    @GetMapping("condition/{type}")
    public List<Condition> getConditionsByType(@PathVariable String type) {
        return conditionService.findConditionByType(type);
    }

    @PostMapping("save")
    public Condition saveCondition(@RequestBody Condition condition) {
        return conditionService.saveCondition(condition);
    }
}
