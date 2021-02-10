package com.project.controllers;

import com.project.entity.Flight;
import com.project.service.flight.FlightService;
import com.project.service.gate.GateBoardingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    private final GateBoardingService gateBoardingService;

    /**
     * Returns all flights
     *
     * @return
     */
    @GetMapping
    List<Flight> getAllFlights() {
        return flightService.findAll();
    }

    /**
     * Returns Flight by id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Flight getFlights(@PathVariable Long id) {
        return flightService.findById(id);
    }

    /**
     * Occupies first available gate
     *
     * @param flight
     * @return
     */
    @PostMapping
    Flight occupyAnyAvailableGate(@RequestBody Flight flight) {
        return gateBoardingService.occupyFirstAvailableGate(flight);
    }

    /**
     * Occupies specified Gate for Flight
     *
     * @param flight
     * @param gateId
     * @return
     */
    @PostMapping("/{gateId}")
    Flight occupyGate(@RequestBody Flight flight, @PathVariable Long gateId) {
        return gateBoardingService.occupyGateByFlight(flight, gateId);
    }

}
