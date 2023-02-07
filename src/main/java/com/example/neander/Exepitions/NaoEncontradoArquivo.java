package com.example.neander.Exepitions;

public class NaoEncontradoArquivo extends Exception{
    @Override
    public String toString(){
        return "arquivo n encontrado";
    }
}
