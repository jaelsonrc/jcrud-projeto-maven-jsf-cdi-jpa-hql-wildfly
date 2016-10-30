package br.com.jcrud.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;

import br.com.jcrud.annotations.Transactional;
import br.com.jcrud.persistence.daointerfaces.DAO;
import br.com.jcrud.persistence.model.Contribuinte;
import br.com.jcrud.persistence.model.Rua;
import br.com.jcrud.util.FacesUtil;

@Named
@ViewScoped
public class ContribuinteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DAO<Contribuinte> dao;

	@Inject
	private DAO<Rua> daoRua;

	private Contribuinte contribuinte;
	private Contribuinte exclusaocontribuinte;
	private List<Contribuinte> contribuintes;
	private List<Contribuinte> contribuintesFiltrado;
	private List<Rua> ruas;

	public void init() {
		updateQtde();
		getAll();
	}

	public void getAll() {
		setRuas(daoRua.getAll());
		setContribuintes(dao.getAll());
		contribuinte = null;
	}

	public void updateQtde() {
		dao.updateHQLQueryNoParamaters("updateQtdeImovel");
	}

	public void back() {
		contribuinte = null;
	}

	public void add() {
		contribuinte = new Contribuinte();
	}

	public void edit(Contribuinte contribuinte) {
		this.contribuinte = contribuinte;

	}

	@Transactional
	public void save() {

		// faz verificação se é para inserir ou atualizar
		if (contribuinte.getId() != null) {
			if (contribuinte.getId() > 0) {
				dao.update(contribuinte);
			}
		} else {
			dao.save(contribuinte);
		}
		// sucesso é id do arquivo messege.propertie
		FacesUtil.AddSuccessMessege("sucesso");
		getAll();
	}

	public void selectDelete(Contribuinte contribuinte) {
		this.exclusaocontribuinte = contribuinte;
	}

	@Transactional
	public void delete() {
		String hql = "select count(*) from Imovel i where i.contribuinte.id =" + exclusaocontribuinte.getId().toString();
		Query query = dao.query(hql);
		List<?> listResult = query.getResultList();
		Integer qtde = (Integer) listResult.get(0);
		if (qtde > 0) {
			FacesUtil.AddErrorMessege("errorDeleteContribuinte");
		} else {
			dao.remove(this.exclusaocontribuinte);
			FacesUtil.AddSuccessMessege("sucesso");
			init();
		}
	}

	public List<Contribuinte> getContribuintes() {
		return contribuintes;
	}

	public void setContribuintes(List<Contribuinte> contribuintes) {
		this.contribuintes = contribuintes;
	}

	public Contribuinte getContribuinte() {
		return contribuinte;
	}

	public void setContribuinte(Contribuinte contribuinte) {
		this.contribuinte = contribuinte;
	}

	public List<Contribuinte> getContribuintesFiltrado() {
		return contribuintesFiltrado;
	}

	public void setContribuintesFiltrado(List<Contribuinte> contribuintesFiltrado) {
		this.contribuintesFiltrado = contribuintesFiltrado;
	}

	public Contribuinte getExclusaoContribuinte() {
		return exclusaocontribuinte;
	}

	public void setExclusaoContribuinte(Contribuinte exclusaocontribuinte) {
		this.exclusaocontribuinte = exclusaocontribuinte;
	}

	public List<Rua> getRuas() {
		return ruas;
	}

	public void setRuas(List<Rua> ruas) {
		this.ruas = ruas;
	}

}
