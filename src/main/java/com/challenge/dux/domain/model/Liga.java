package com.challenge.dux.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
public class Liga {

    @Id
    private Long id;

    
}
