package com.project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@ToString
public class Flight {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NotNull
    @Column(nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name="gate_id", nullable = false)
    @Getter
    @Setter
    private Gate gate;

    @Getter
    @Setter
    private boolean onBoarding;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (onBoarding != flight.onBoarding) return false;
        if (!id.equals(flight.id)) return false;
        if (!number.equals(flight.number)) return false;
        return gate.equals(flight.gate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + gate.hashCode();
        result = 31 * result + (onBoarding ? 1 : 0);
        return result;
    }
}
