package br.com.jcrud.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jcrud.annotations.Transactional;
import br.com.jcrud.persistence.daointerfaces.DAO;
import br.com.jcrud.persistence.model.Rua;
import br.com.jcrud.util.FacesUtil;

/**
 * Created by jaels on 20/10/2016.
 */

@Named
@ViewScoped
public class TesteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
    private DAO<Rua> dao;

    private Rua rua;
    private List<Rua> ruas;

    public Rua getRua() {
		return rua;
	}

	public void setRua(Rua rua) {
		this.rua = rua;
	}

	public List<Rua> getRuas() {
		return ruas;
	}

	public void setRuas(List<Rua> ruas) {
		this.ruas = ruas;
	}

	@PostConstruct
    public void init(){
        ruas=dao.getAll();
    }

     @Transactional
    public void inserir(){
        Rua r = new Rua();
        r.setTipo("Av");
        r.setNome("Afonso Pena");
        dao.save(r);
    
        FacesUtil.AddSuccessMessege("sucesso");
    }

    public void lista(){
      //  List<Projeto> projetos = dao.findByHQLQuery("searchProjectByName", Collections.singletonList(likeLower("Projeto 1")), 0 );
        System.out.println(ruas);
    }



}
