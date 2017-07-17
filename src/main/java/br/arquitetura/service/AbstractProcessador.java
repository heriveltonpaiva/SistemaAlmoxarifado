package br.arquitetura.service;

import java.util.ArrayList;
import java.util.List;

public class AbstractProcessador {

	public Movimento movimento;
	
	public List<String> erros;
	
	public void setMovimento(Movimento movimento) {
		this.movimento = movimento;
	}
	
	public List<String> getErros() {
		return erros;
	}
	
	public void addErro(String mensagem){
		if(erros == null)
			erros = new ArrayList<String>();
		else
			erros.add(mensagem);
	}
	
	public boolean hasErros(){
		if(erros != null && erros.size() > 0)
			return true;
		return false;
	}
	
}
