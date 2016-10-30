package br.com.jcrud.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jcrud.annotations.Transactional;
import br.com.jcrud.persistence.daointerfaces.DAO;
import br.com.jcrud.persistence.model.Contribuinte;
import br.com.jcrud.persistence.model.Imovel;
import br.com.jcrud.util.FacesUtil;

@Named
@ViewScoped
public class ImovelBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DAO<Imovel> dao;
	
	@Inject
	private DAO<Contribuinte> daoContribuinte;

	private Imovel imovel;
	private Imovel exclusaoImovel;
	private List<Imovel> imoveis;
	private List<Imovel> imoveissFiltrado;
	private List<Contribuinte> contribuintes;
	
	

	public void init() {
		setImoveis(dao.getAll());
		setContribuintes(daoContribuinte.getAll());
		imovel=null;
	}

	public void back() {
		imovel = null;
	}

	public void add() {
		imovel = new Imovel();
	}
	
	public void edit(Imovel projeto){
		this.imovel=projeto;
		
	}

   @Transactional
	public void save() {
	   
	   String insc = imovel.getSetor() + imovel.getZona() + imovel.getLote() + imovel.getQuadra();
	   this.imovel.setInscricao(insc);
	 	// faz verificação se é para inserir ou atualizar
		if (imovel.getId() != null) {
			if (imovel.getId() > 0) {
				dao.update(imovel);			
			}
		} else {
			dao.save(imovel);		
		}	
		
		//sucesso é id do arquivo messege.propertie		
		FacesUtil.AddSuccessMessege("sucesso");
	    init();
	}
   
   
   public void selectDelete(Imovel projeto){
	   this.exclusaoImovel=projeto;
   }
   
   @Transactional
   public void delete(){
	   dao.remove(this.exclusaoImovel);
	   FacesUtil.AddSuccessMessege("sucesso");
	   init();
   }

	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<Imovel> imoveis) {
		this.imoveis = imoveis;
	}

	public Imovel getImovel() {
		setImoveis(dao.getAll());
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public List<Imovel> getImoveisFiltrado() {
		return imoveissFiltrado;
	}

	public void setImoveisFiltrado(List<Imovel> imoveisFiltrado) {
		this.imoveissFiltrado = imoveisFiltrado;
	}

	public Imovel getExclusaoImovel() {
		return exclusaoImovel;
	}

	public void setExclusaoImovel(Imovel exclusao) {
		this.exclusaoImovel = exclusao;
	}

	public List<Contribuinte> getContribuintes() {
		return contribuintes;
	}

	public void setContribuintes(List<Contribuinte> contribuintes) {
		this.contribuintes = contribuintes;
	}

}
