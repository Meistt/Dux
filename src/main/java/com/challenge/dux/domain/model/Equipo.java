package com.challenge.dux.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "equipo")
public class Equipo {
    @Id
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "liga_id")
    private Liga liga;
}
