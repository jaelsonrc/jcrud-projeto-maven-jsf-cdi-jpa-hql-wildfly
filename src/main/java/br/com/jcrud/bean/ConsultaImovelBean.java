package br.com.jcrud.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jcrud.persistence.daointerfaces.DAO;
import br.com.jcrud.persistence.model.Imovel;
import br.com.jcrud.persistence.model.VO.ImovelVO;
import br.com.jcrud.util.StringUtil;


@Named
@ViewScoped
public class ConsultaImovelBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DAO<ImovelVO> dao;
	
	private List<ImovelVO> imoveis;
  
	private String strPesquisa;
	
	public void init() {
		
		setImoveis(dao.findByHQLQuery("queryImovelVOAll", 0));
		
	}
	
	public void pesquisar(){
		//sugestão de Henrique Smoco 
		setImoveis(dao.findByHQLNamedQuery(Imovel.BUSCA_POR_NOME, Collections.singletonList(StringUtil.likeLower(this.getStrPesquisa())), 0));
	}

	public List<ImovelVO> getImoveis() {		
		
		return imoveis;
	}


	public void setImoveis(List<ImovelVO> imoveis) {
		this.imoveis = imoveis;
	}

	public String getStrPesquisa() {
		return strPesquisa;
	}

	public void setStrPesquisa(String strPesquisa) {
		this.strPesquisa = strPesquisa;
	}

}
