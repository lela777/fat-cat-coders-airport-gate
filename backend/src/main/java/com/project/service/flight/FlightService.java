package com.project.service.flight;

import com.project.entity.Flight;
import com.project.entity.Gate;
import com.project.errors.ItemNotFoundException;
import com.project.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository repository;

    public Flight findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Optional<Flight> findByGate(Gate gate) {
        return repository.findByGate(gate);
    }
    public Flight findByGateOrThrow(Gate gate) {
        return repository.findByGate(gate).orElseThrow(() -> new ItemNotFoundException());
    }

    public void save(Flight flight) {
        repository.save(flight);
    }

    public List<Flight> findAll() {
        List<Flight> flights = repository.findAll();

        return flights.isEmpty() ? Collections.emptyList() : flights;
    }

}
