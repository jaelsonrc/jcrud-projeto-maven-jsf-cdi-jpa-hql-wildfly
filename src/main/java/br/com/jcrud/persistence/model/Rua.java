package br.com.jcrud.persistence.model;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "rua")
public class Rua extends AbstractEntity {

	
	private static final long serialVersionUID = 1L;
	private String tipo;
	private String nome;
	private Date dataCadastro;	
	
	@OneToMany(mappedBy = "rua", targetEntity = Contribuinte.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Contribuinte> contribuintes;



	@NotNull(message="Campo obrigatorio")
    @Size(min=2, max=10, message="Minimo 2 e o maximo 10 caracter")
	@Column(name = "tipo", length = 10)
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@NotNull(message="Campo obrigatorio")
    @Size(min=2, max=50, message="Minimo 3 e o maximo 50 caracter")
	@Column(name = "nome", length = 50)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "data_cadastro", length = 15)
	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	
	public List<Contribuinte> getContribuintes() {
		return contribuintes;
	}

	public void setContribuintes(List<Contribuinte> contribuintes) {
		this.contribuintes = contribuintes;
	}

	

}
