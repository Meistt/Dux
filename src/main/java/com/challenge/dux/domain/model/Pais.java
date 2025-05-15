package com.challenge.dux.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "pais")
public class Pais {
    @Id
    private Long id;
    private String nombre;
}
