package com.example.neander.neander;


import com.example.neander.Exepitions.ErroEnderecamento;
import com.example.neander.Exepitions.InstrucaoNexiste;
import com.example.neander.configs.Dados;

import java.util.ArrayList;
import java.util.List;




public class Neander {
	private final List<Integer> memoria;
	private final List<Integer> registradores;
	private boolean zero=false;
	private boolean negativo=false;

	private int contadorProgrma;
	private final List<InstrucaoNeander> instrucoes;
	boolean stop =false;

	public Neander(List<InstrucaoNeander> numeroInstrucoes, int memoria) {
		this.memoria = new ArrayList<>();

		for(int i=0;i<memoria+1;i++){
			this.memoria.add(0);
		}

		this.registradores = new ArrayList<>();


			this.registradores.add(0);


		instrucoes = numeroInstrucoes;
	}

	public List<InstrucaoNeander> getInstrucoes() {
		return instrucoes;
	}

	public void processaInstrucao(InstrucaoNeander i) throws InstrucaoNexiste {
		switch(i.getCodigo()){

			case LDA:
				registradores.set(0,
						this.memoria.get(i.getEndereco()));

				verificaRegistrador();
			break;
			case ADD:
				registradores.set(0, registradores.get(0)
						+this.memoria.get(i.getEndereco()));

				verificaRegistrador();

			break;
			case OR:
				registradores.set(0, registradores.get(0)
						| memoria.get(i.getEndereco()));

				verificaRegistrador();
			break;
			case AND:
				registradores.set(0, registradores.get(0)
						& memoria.get(i.getEndereco()));

				verificaRegistrador();
			break;

			case JN:
				if(negativo){
					this.contadorProgrma =
							i.getEndereco();
				}
			break;
			case JZ:
				if(zero){
					this.contadorProgrma =
							i.getEndereco();
				}
			break;
			case NOT:
				registradores.set(0,
						registradores.get(0));

				verificaRegistrador();
				break;
			case JMP:
				contadorProgrma = i.getEndereco();
				break;
			case HLT:
				stop =true;
			break;

			case ME:
				memoria.set(i.getLinha(),i.getEndereco());
				break;
			case NOP :

				break;
			case STA:
				memoria.set(i.getEndereco(),
						this.registradores.get(0));
				break;
			default:
				throw new InstrucaoNexiste(""+i.getCodigo());
		}
	}

	private void verificaRegistrador(){
		this.zero= registradores.get(0) == 0;
		this.negativo= registradores.get(0) < 0;
	}

	private void processadorInstrucaoBasic() throws InstrucaoNexiste {
		contadorProgrma=-1;
		for(int i=0;i<this.getInstrucoes().size();i++){

			if(!this.getInstrucoes().get(i).equals(NeanderCodigos.ME)){

				processaInstrucao(this.getInstrucoes().get(i));

				if(contadorProgrma!=-1){
					for(int j=0;j<this.getInstrucoes().size();j++){
						if(this.getInstrucoes().get(j).getLinha()==contadorProgrma){
							i=j-1;
							contadorProgrma=-1;
						}
					}

					if(contadorProgrma!=-1){

						throw new ErroEnderecamento(""+this.getInstrucoes().get(i).getLinha());
					}
				}

				if(stop){
					break;
				}

			}
		}
	}



	public ArrayList<Dados> resultado() throws InstrucaoNexiste {

		for(InstrucaoNeander i :instrucoes){
			if(i.getCodigo().equals(NeanderCodigos.ME)){
				processaInstrucao(i);
			}
		}
		processadorInstrucaoBasic();

		ArrayList<Dados>dados=new ArrayList<>();



		for (Integer integer:memoria) {
			dados.add(new Dados(integer));
		}
		return dados;

	}

}
