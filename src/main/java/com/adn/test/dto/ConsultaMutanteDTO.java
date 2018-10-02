package com.adn.test.dto;

import org.jsondoc.core.annotation.ApiBodyObject;

import java.util.List;

public class ConsultaMutanteDTO {
    private List<String> dna;

    public List<String> getDna() {
        return dna;
    }

    public void setDna(List<String> dna) {
        this.dna = dna;
    }
}
