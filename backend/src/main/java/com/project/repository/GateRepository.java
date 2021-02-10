package com.project.repository;

import com.project.entity.Gate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GateRepository extends JpaRepository<Gate, Long> {

    List<Gate> findByAvailable(Boolean available);

    Optional<Gate> findByNumber(String number);

}
