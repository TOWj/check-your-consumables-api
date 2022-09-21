package com.nov.checkyourconsumablesapi.repositories;

import com.nov.checkyourconsumablesapi.models.Consumables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumablesRepository extends JpaRepository<Consumables, Integer> {
    List<Consumables> findAllByOwnerUser(int id);
}
