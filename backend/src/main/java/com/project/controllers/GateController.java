package com.project.controllers;

import com.project.entity.Gate;
import com.project.service.gate.GateBoardingService;
import com.project.service.gate.GateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gates")
@RequiredArgsConstructor
public class GateController {

    private final GateService gateService;

    private final GateBoardingService gateBoardingService;

    /**
     * Returns all gates or gates by available if specified
     *
     * @param available
     * @return
     */
    @GetMapping
    List<Gate> getGates(@RequestParam(required = false) Boolean available) {
        if (available != null) {
            return gateService.findByAvailable(available);
        }

        return gateService.findAll();
    }

    /**
     * Returns Gate by id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Gate getApps(@PathVariable Long id) {
        return gateService.findByIdOrThrowEx(id);
    }

    /**
     * Stops flight boarding
     *
     * @param gateId
     * @return
     */
    @PostMapping("/finishBoarding/{gateId}")
    Gate finishGateBoarding(@PathVariable Long gateId) {
        return gateBoardingService.stopFlightBoarding(gateId);
    }

    @PostMapping()
    Gate updateGate(@Valid @RequestBody Gate gate) {
        return gateService.update(gate);
    }

    @PutMapping()
    Gate createGate(@RequestBody Gate gate) {
        return gateService.create(gate);
    }
}
