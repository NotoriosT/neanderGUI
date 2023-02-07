package com.example.neander.neander;


import com.example.neander.Exepitions.ArquivoVazio;
import com.example.neander.Exepitions.InstrucaoNexiste;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class processadorInstrucaoNeander {
	private List<List<String>> listaStrings;
	private List<InstrucaoNeander> instrucoes;

	public processadorInstrucaoNeander(String nomeDoArquivo) throws Exception {

		List<String> dados = leitura(nomeDoArquivo);


		this.listaStrings = analiseSintatica(dados);

		manipulaString();

	}
	public static List<List<String>> analiseSintatica(List<String> dados) throws Exception {
		if(dados.isEmpty()){
			throw new ArquivoVazio();
		}


		List<List<String>> listaRetorno = new ArrayList<>();


		List<String> listaObjeto = new ArrayList<>();



		for(int i=0;i<dados.size();i++){

			if(!dados.get(i).equals("") && !dados.get(i).startsWith("--")){


				for(String string : dados.get(i).split(" ")){
					listaObjeto.add(string.toUpperCase());
				}

				listaObjeto.add(""+(i+1));

				listaRetorno.add(new ArrayList<String>(listaObjeto));
				listaObjeto.clear();
			}

		}

		return listaRetorno;
	}

	public static List<String> leitura(String file) throws Exception {

		List<String> linhas = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

			while(br.ready()){
				linhas.add(br.readLine());
			}
			System.out.println(linhas);

			br.close();
		} catch (IOException e) {


			e.printStackTrace();
		}

		if(linhas.isEmpty()){
			throw new ArquivoVazio();
		}

		return linhas;
	}
	public List<List<String>> getListaStrings() {
		return listaStrings;
	}

	public void manipulaString() throws Exception {

		instrucoes = new ArrayList<>();

		for (int i = 0; i < this.getListaStrings().size(); i++) {

			if (!(this.getListaStrings().get(i).get(0).equals("")
					|| this.getListaStrings().get(i).get(0).equals(" "))) {
				instrucoes.add(construirInstrucao(this.getListaStrings().get(i)));
			}
		}
	}

	private InstrucaoNeander construirInstrucao(List<String> lista) throws Exception {
		InstrucaoNeander in = new InstrucaoNeander();
		System.out.println(lista.get(0));
		for(NeanderCodigos codigos : NeanderCodigos.values()){

			if(codigos.name().equals(lista.get(0))){
				in.setCodigo(codigos);
                in.setCodigoString(codigos.name());
			}
		}

		if(in.getCodigo()==null){


	    	throw new InstrucaoNexiste(lista.get(0));

		}else{
			if(lista.size()==2){
				in.setEndereco(0);
				in.setLinha(Integer.parseInt(lista.get(1)));
			}else{
				if(lista.size()==3){
					in.setEndereco(Integer.parseInt(lista.get(1)));
					in.setLinha(Integer.parseInt(lista.get(2)));
				}
			}	
		}

		return in;
	}

	public List<InstrucaoNeander> getInstrucoes() {
		return instrucoes;
	}

}
