package com.project.service.gate;

import com.project.entity.Flight;
import com.project.entity.Gate;
import com.project.errors.AppException;
import com.project.errors.AppExceptionStrings;
import com.project.service.flight.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GateBoardingService {

    private final FlightService flightService;

    private final GateService gateService;

    /**
     * Occupy {@link Gate} with gateNumber for {@link Flight} if possible and save Flight
     *
     * @param flight
     * @param gateId
     */
     synchronized public Flight occupyGateByFlight(Flight flight, Long gateId) {
        Gate gate = gateService.findByIdOrThrowEx(gateId);

        if (gate.isAvailable()) {
            occupyGate(flight, gate);
        } else {
            throw new AppException(HttpStatus.NOT_FOUND, AppExceptionStrings.GATE_NOT_AVAILABLE, gate);
        }

        return flight;
    }

    /**
     * Occupy first available  {@link Gate} and save {@link Flight} if possible
     *
     * @param flight
     */
    synchronized public Flight occupyFirstAvailableGate(Flight flight) {
        List<Gate> gates = gateService.findByAvailable(true);

        if (gates.isEmpty()) {
            throw new AppException(HttpStatus.NOT_FOUND, AppExceptionStrings.GATES_NOT_AVAILABLE);
        } else {
            Gate gate = gates.get(0);
            occupyGate(flight, gate);
        }

        return flight;
    }

    /**
     * Stops {@link Flight} boarding and make {@link Gate} available
     *
     * @param gateId
     */
     public Gate stopFlightBoarding(Long gateId) {
        Gate gate = gateService.findByIdOrThrowEx(gateId);

        if (gate.isAvailable()) {
            throw new AppException(HttpStatus.METHOD_NOT_ALLOWED, AppExceptionStrings.GATE_ALREADY_AVAILABLE, gate);
        }

        Optional<Flight> flight = flightService.findByGate(gate);
        flight.ifPresent(f -> {
            f.setOnBoarding(false);
            flightService.save(f);
        });

         gate.setAvailable(true);
         gateService.save(gate);

        return gate;
    }

    synchronized private void occupyGate(Flight flight, Gate gate) {
        gate.setAvailable(false);
        gateService.save(gate);
        flight.setGate(gate);
        flight.setOnBoarding(true);
        flightService.save(flight);

    }

}
