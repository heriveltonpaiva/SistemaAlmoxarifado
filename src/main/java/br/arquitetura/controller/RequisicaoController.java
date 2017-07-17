package br.arquitetura.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.arquitetura.dao.FuncionarioImpl;
import br.arquitetura.dao.ProdutoImpl;
import br.arquitetura.dao.RequisicaoImpl;
import br.arquitetura.dominio.Funcionario;
import br.arquitetura.dominio.ItemRequisicao;
import br.arquitetura.dominio.Produto;
import br.arquitetura.dominio.Requisicao;
import br.arquitetura.service.ListaComandos;
import br.arquitetura.service.Movimento;
import br.arquitetura.service.ProcessadorCadastroRequisicao;;

@SuppressWarnings("serial")
@Component
@Scope("session")
public class RequisicaoController extends AbstractController<Requisicao>{

	 private List<ItemRequisicao> listaItemRequisicao;
	 private List<Requisicao> listaRequisicao;
	 private ItemRequisicao itemRequisicao;
	 private RequisicaoImpl requisicaoDao;
	 private Produto produto;
	 private Funcionario funcionario;
	 private double valorTotal;
	 private Integer quantidade;
	 private Integer codigoProduto;
	 private Integer quantidadeTemp;
	
	public RequisicaoController() {
		reset();
	}
	
	public void reset(){
		obj = new Requisicao();
		obj.setFuncionario(new Funcionario());
	}

	 /**
     * Adiciona um produto na lista de itens
     */
    public void addlistaItemRequisicao() {
        
        if (this.produto != null) {
            if (this.verificarEstoqueDisponivel() == true) {
            	itemRequisicao = new ItemRequisicao();
            	itemRequisicao.setProduto(produto);
            	itemRequisicao.setQuantidade(quantidade);
            	itemRequisicao.setValorTotal(produto.getValor().multiply(BigDecimal.valueOf(quantidade)));
                if (listaItemRequisicao == null) {
                    listaItemRequisicao = new ArrayList<ItemRequisicao>();
                }
                this.listaItemRequisicao.add(itemRequisicao);
                this.calcularValorTotal();
                this.setCodigoProduto(0);
                this.setQuantidade(0);
                this.produto = new Produto();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Quantidade Requisitada é maior do que o Estoque!"));
            }
        }

    }

    /**
     * Finaliza a requisição de todos os produtos da lista
     */
    @Transactional
    public void finalizarRequisicao() {

        try {
            if (listaItemRequisicao != null) {
                Requisicao r = new Requisicao();
                requisicaoDao = new RequisicaoImpl();

                r.setCodigo(requisicaoDao.listar().size() + 1);
                r.setData(new Date());
                r.setFuncionario(this.buscarFuncionario());
                r.setItens(listaItemRequisicao);

                Movimento mov = new Movimento();
                ProcessadorCadastroRequisicao processador = new ProcessadorCadastroRequisicao();
        		
        		mov.setCodMovimento(ListaComandos.CADASTRAR_REQUISICAO);
        		mov.setObjMovimentado(r);
        		processador.setMovimento(mov);
        		mov.setComando(processador);
        		mov.execute();
        		
        		reset();
        		getListagem();

                // remove todos os itens da lista
                listaItemRequisicao.clear();
                this.produto = null;
                // limpa os campos
                setValorTotal(0.0);
                this.getListaRequisicao();
        		exibirMensagemSucesso();

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Sua Lista está vazia, insira um produto antes!"));

            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "" + ex.getMessage()));
        }
      
    }

    /**
     * Calcula o valor total dos itens da lista temporária de produtos
     */
    public void calcularValorTotal() {
        // Calcula o valor total dos produtos requisitados
        double valorTotalLista = 0;
        for (ItemRequisicao ItemRequisicao : listaItemRequisicao) {
            valorTotalLista += ItemRequisicao.getValorTotal().doubleValue();
        }
        setValorTotal(valorTotalLista);

    }

    /**
     * Remove os itens da lista temporária(Carrinho)
     */
    @Transactional
    public void removerItemRequisicaoLista() {
        this.listaItemRequisicao.remove(itemRequisicao);
        System.out.println("Tamanho da Lista " + listaItemRequisicao.size());
        this.calcularValorTotal();
    }

    /**
     * Faz a busca do produto pelo código passado e mostra os dados na página
     * JSF utilizando AJAX do Primefaces
     *
     * @return
     */
    public Produto buscarProduto() {
        try {
            
            
            if (this.codigoProduto > 0) {
                produto = new Produto();
                ProdutoImpl proDao = new ProdutoImpl();
                
                produto = proDao.findByCodigo(codigoProduto);
                
                this.quantidadeTemp = 0;

                // atualizar o estoque ao adicionar os itens na lista temporária
                if (this.listaItemRequisicao != null) {

                    for (ItemRequisicao list : listaItemRequisicao) {

                        if (list.getProduto().getCodigo() == this.codigoProduto) {

                            this.quantidadeTemp += list.getQuantidade();

                        }

                    }
                 this.getListaRequisicao();
                }
            }
        } catch (Exception e) {

        }
        return produto;
    }

    /**
     * Faz a busca do Funcionário
     *
     * @return
     */
    public final Funcionario buscarFuncionario() {
    	FuncionarioImpl funDao = new FuncionarioImpl();
        funcionario = funDao.findByNome(obj.getFuncionario().getNome());
        return funcionario;
    }

    /**
     * Retorna uma lista de requisições
     *
     * @return
     */
    public List<Requisicao> getListaRequisicao() {

            listaRequisicao = new ArrayList<>();
            requisicaoDao = new RequisicaoImpl();
            this.listaRequisicao = requisicaoDao.listar();
        
        return listaRequisicao;
    }

    /**
     * VERIFICA SE POSSUI ESTOQUE PARA QUANTIDADE REQUISITADA
     *
     * @return
     */
    public boolean verificarEstoqueDisponivel() {
        boolean result = false;
        if (this.quantidade != null) {

            if (this.quantidade > this.produto.getQuantidade().intValue()) {

                result = false;
            } else {

                result = true;
            }

        }
        return result;
    }
	
    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Funcionario getFuncionario() {
        funcionario = this.buscarFuncionario();
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<ItemRequisicao> getListaItemRequisicao() {
		return listaItemRequisicao;
	}

    public void setListaItemRequisicao(List<ItemRequisicao> listaItemRequisicao) {
		this.listaItemRequisicao = listaItemRequisicao;
	}

    public String getDataAtual() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date());
    }

    public void limparListaDialog() {
        //Limpar a lista de item carregada no dialog
        this.listaItemRequisicao = null;
    }

    public Integer getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Integer codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public ItemRequisicao getItemRequisicao() {
		return itemRequisicao;
	}

    public void setItemRequisicao(ItemRequisicao itemRequisicao) {
		this.itemRequisicao = itemRequisicao;
	}
    
    public Integer getQuantidadeTemp() {
        return quantidadeTemp;
    }

    public void setQuantidadeTemp(Integer quantidadeTemp) {
        this.quantidadeTemp = quantidadeTemp;
    }

    @Override
    public List<Requisicao> getListagem() {
    	requisicaoDao = new RequisicaoImpl();
    	
    	List<Requisicao> listaBD = requisicaoDao.listar();
    	listagem = new ArrayList<Requisicao>();
    	if(listaBD != null){
	    	for (Requisicao req : listaBD) {
	    		req.setItens(requisicaoDao.findItensByRequisicao(req.getId()));
	    		listagem.add(req);
			}
    	}
    	return listagem;
    }
	
}
