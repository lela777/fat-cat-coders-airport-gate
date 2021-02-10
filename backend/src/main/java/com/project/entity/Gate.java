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
public class Gate {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NotNull
    @Column(nullable = false, unique = true)
    private String number;

    @Getter
    @Setter
    @Column
    private boolean available;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gate gate = (Gate) o;

        if (available != gate.available) return false;
        if (!id.equals(gate.id)) return false;
        return number.equals(gate.number);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + (available ? 1 : 0);
        return result;
    }
}
