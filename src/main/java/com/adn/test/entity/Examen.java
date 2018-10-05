package com.adn.test.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Examen {

    @Id
    private Long id;

    private String adn;
    private Boolean mutante;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdn() {
        return adn;
    }

    public void setAdn(String adn) {
        this.adn = adn;
    }

    public Boolean getMutante() {
        return mutante;
    }

    public void setMutante(Boolean mutante) {
        this.mutante = mutante;
    }

    public Examen(String adn, Boolean mutante) {
        this.adn = adn;
        this.id = Integer.toUnsignedLong(adn.hashCode());
        this.mutante = mutante;
    }

    public Examen() {
    }
}
