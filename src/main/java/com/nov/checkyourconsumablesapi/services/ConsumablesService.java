package com.nov.checkyourconsumablesapi.services;

import com.nov.checkyourconsumablesapi.models.Consumables;
import com.nov.checkyourconsumablesapi.repositories.ConsumablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ConsumablesService {

    private final ConsumablesRepository consumablesRepository;

    @Autowired
    public ConsumablesService(ConsumablesRepository consumablesRepository) {
        this.consumablesRepository = consumablesRepository;
    }

    public List<Consumables> findAllByPersonId(int id) {
        return consumablesRepository.findAllByPersonId(id);
    }

    @Transactional
    public void addConsumables(Consumables consumables) {
        consumablesRepository.save(consumables);
    }

    @Transactional
    public void updateConsumables(int id, Consumables updatedConsumables) {
        updatedConsumables.setId(id);
        consumablesRepository.save(updatedConsumables);
    }
}
