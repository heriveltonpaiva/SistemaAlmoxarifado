package br.arquitetura.service;

/**
 * Classe que permite executar o commando
 * @author Herivelton
 *
 */
public class Movimento {

	private Comando comando;
	
	private Object objMovimentado;
	
	private int codMovimento;
	
	public void setComando(Comando comando) {
		this.comando = comando;
	}
	public void execute(){
		comando.execute();
	}
	
	public void setCodMovimento(int codMovimento) {
		this.codMovimento = codMovimento;
	}
	public int getCodMovimento() {
		return codMovimento;
	}
	
	public void setObjMovimentado(Object objMovimentado) {
		this.objMovimentado = objMovimentado;
	}
	public Object getObjMovimentado() {
		return objMovimentado;
	}

}
