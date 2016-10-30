package br.com.jcrud.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jcrud.annotations.Transactional;
import br.com.jcrud.persistence.daointerfaces.DAO;
import br.com.jcrud.persistence.model.Rua;
import br.com.jcrud.util.FacesUtil;

@Named
@ViewScoped
public class RuaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DAO<Rua> dao;

	private Rua rua;
	private Rua exclusaoRua;
	private List<Rua> ruas;
	private List<Rua> ruasFiltrado;
	

	public void init() {
		setRuas(dao.getAll());
		rua=null;
	}

	public void back() {
		rua = null;
	}

	public void add() {
		rua = new Rua();
	}
	
	public void edit(Rua projeto){
		this.rua=projeto;
		
	}

   @Transactional
	public void save() {

	 	// faz verificação se é para inserir ou atualizar
		if (rua.getId() != null) {
			if (rua.getId() > 0) {
				dao.update(rua);			
			}
		} else {
			dao.save(rua);		
		}		
		//sucesso é id do arquivo messege.propertie		
		FacesUtil.AddSuccessMessege("sucesso");
		init();
	}
   
   
   public void selectDelete(Rua projeto){
	   this.exclusaoRua=projeto;
   }
   
   @Transactional
   public void delete(){
	   dao.remove(this.exclusaoRua);
	   FacesUtil.AddSuccessMessege("sucesso");
	   init();
   }
   
	
	public String getNow(){
		return new SimpleDateFormat("dd/MM/yyyy")
				.format(new Date());
	}

	public List<Rua> getRuas() {
		return ruas;
	}

	public void setRuas(List<Rua> ruas) {
		this.ruas = ruas;
	}

	public Rua getRua() {
		return rua;
	}

	public void setRua(Rua rua) {
		this.rua = rua;
	}

	public List<Rua> getRuasFiltrado() {
		return ruasFiltrado;
	}

	public void setRuasFiltrado(List<Rua> ruasFiltrado) {
		this.ruasFiltrado = ruasFiltrado;
	}

	public Rua getExclusaoRuas() {
		return exclusaoRua;
	}

	public void setExclusaoRuas(Rua exclusaoRuas) {
		this.exclusaoRua = exclusaoRuas;
	}

}
