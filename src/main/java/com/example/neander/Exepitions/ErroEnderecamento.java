package com.example.neander.Exepitions;

public class ErroEnderecamento extends RuntimeException{
   private String mensagem;

    public ErroEnderecamento(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return "erro na linha:"+mensagem;
    }
}
