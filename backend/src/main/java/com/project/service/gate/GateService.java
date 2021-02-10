package com.project.service.gate;

import com.project.entity.Gate;
import com.project.errors.AppException;
import com.project.errors.AppExceptionStrings;
import com.project.errors.ItemNotFoundException;
import com.project.repository.GateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GateService {

    final private GateRepository repository;

    public Gate findById(Long id) {
        return repository.findById(id).orElse(null);
    }
    public Gate findByIdOrThrowEx(Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Gate findByNumber(String number) {
        return repository.findByNumber(number).orElse(null);
    }

    public Gate save(Gate gate) {
        return repository.save(gate);
    }

    public Gate update(Gate inputGate) {
        Gate gate = this.findByIdOrThrowEx(inputGate.getId());
        Optional<Gate> gateWithSameNumber = repository.findByNumber(inputGate.getNumber());

        if (gateWithSameNumber.isPresent() && !gate.equals(gateWithSameNumber.orElse(null))) {
            throw new AppException(HttpStatus.FORBIDDEN, AppExceptionStrings.GATE_ALREADY_EXISTS,
                    gateWithSameNumber.get());

        }

        return repository.save(inputGate);
    }

    public Gate create(Gate inputGate) {
        Optional<Gate> gate = repository.findByNumber(inputGate.getNumber());
        if (gate.isPresent()) {
           throw new AppException(HttpStatus.METHOD_NOT_ALLOWED, AppExceptionStrings.GATE_ALREADY_EXISTS, gate.get());
        }

        return repository.save(inputGate);
    }

    public List<Gate> findAll() {
        List<Gate> gates = repository.findAll();

        return gates.isEmpty() ? Collections.emptyList() : gates;
    }

    public List<Gate> findByAvailable(Boolean available) {
        List<Gate> gates = repository.findByAvailable(available);

        return gates.isEmpty() ? Collections.emptyList() : gates;
    }
}
