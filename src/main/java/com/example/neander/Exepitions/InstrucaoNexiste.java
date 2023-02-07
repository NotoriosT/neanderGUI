package com.example.neander.Exepitions;

public class InstrucaoNexiste extends Exception{
    String mensagem;
    public InstrucaoNexiste(String mensage) {
        this.mensagem=mensage;
    }

    @Override
    public String toString() {
        return "instrução não existe:"+mensagem;
    }
}
