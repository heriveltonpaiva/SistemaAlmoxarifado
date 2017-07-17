package br.arquitetura.service;

/**
 * Interface Implementada pelo Processador
 * @author Herivelton
 *
 */
public interface Comando {
	
	public Movimento execute();

	public void validate();
}
