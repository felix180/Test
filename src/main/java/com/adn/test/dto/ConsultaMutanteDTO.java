package com.adn.test.dto;

import org.jsondoc.core.annotation.ApiBodyObject;

import java.util.List;

public class ConsultaMutanteDTO {
    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}
