package com.example.neander.neander;

public class InstrucaoNeander {

	private int endereco;
	private int linha;
	private NeanderCodigos codigo;
  private   String codigoString;

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;

	}

	public int getEndereco() {
		return endereco;
	}

	public void setEndereco(int endereco) {
		this.endereco = endereco;
	}



	public InstrucaoNeander() {

	}



	public NeanderCodigos getCodigo() {
		return codigo;
	}

	public void setCodigo(NeanderCodigos codigo) {
		this.codigo = codigo;
	}

    public String getCodigoString() {
        return codigoString;
    }

    public void setCodigoString(String codigoString) {
        this.codigoString = codigoString;
    }
}
