package com.example.neander.configs;

public class Dados {
    Integer memoria;
    Integer ende;

    public Dados(Integer memoria) {
        this.memoria = memoria;

    }

    public Integer getMemoria() {
        return memoria;
    }

    public void setMemoria(Integer memoria) {
        this.memoria = memoria;
    }

    @Override
    public String toString() {
        return "Dados{" +
                "memoria=" + memoria +
                '}';
    }

    public Integer getEnde() {
        return ende;
    }

    public void setEnde(Integer ende) {
        this.ende = ende;
    }
}
