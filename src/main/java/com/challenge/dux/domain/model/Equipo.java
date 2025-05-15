package com.challenge.dux.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter  @Setter @Entity
public class Equipo {
    @Id
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "liga_id")
    private Liga liga;


}
