package br.com.jcrud.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jcrud.annotations.Transactional;
import br.com.jcrud.persistence.daointerfaces.DAO;
import br.com.jcrud.persistence.model.Projeto;
import br.com.jcrud.util.FacesUtil;

@Named
@ViewScoped
public class ProjetoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DAO<Projeto> dao;

	private Projeto projeto;
	private Projeto exclusaoProjeto;
	private List<Projeto> projetos;
	private List<Projeto> projetosFiltrado;
	

	public void init() {
		setProjetos(dao.getAll());
		projeto=null;
	}

	public void back() {
		projeto = null;
	}

	public void add() {
		projeto = new Projeto();
	}
	
	public void edit(Projeto projeto){
		this.projeto=projeto;
		
	}

   @Transactional
	public void save() {

	 	// faz verificação se é para inserir ou atualizar
		if (projeto.getId() != null) {
			if (projeto.getId() > 0) {
				dao.update(projeto);			
			}
		} else {
			dao.save(projeto);		
		}		
		//sucesso é id do arquivo messege.propertie		
		FacesUtil.AddSuccessMessege("sucesso");
		init();
	}
   
   
   public void selectDelete(Projeto projeto){
	   this.exclusaoProjeto=projeto;
   }
   
   @Transactional
   public void delete(){
	   dao.remove(this.exclusaoProjeto);
	   FacesUtil.AddSuccessMessege("sucesso");
	   init();
   }

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public List<Projeto> getProjetosFiltrado() {
		return projetosFiltrado;
	}

	public void setProjetosFiltrado(List<Projeto> projetosFiltrado) {
		this.projetosFiltrado = projetosFiltrado;
	}

	public Projeto getExclusaoProjeto() {
		return exclusaoProjeto;
	}

	public void setExclusaoProjeto(Projeto exclusaoProjeto) {
		this.exclusaoProjeto = exclusaoProjeto;
	}

}
