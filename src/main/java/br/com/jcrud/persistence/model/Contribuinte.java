package br.com.jcrud.persistence.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "contribuinte")
public class Contribuinte extends AbstractEntity {


	private static final long serialVersionUID = 7498756677341881240L;
	

	private String nome;
	private String cpf;
	private Integer numero;
	private Integer qtdeImovel;
	
	@NotNull(message="Campo obrigatorio!")
	@ManyToOne
	@JoinColumn(name="rua_id")
	private Rua rua;
	
	@OneToMany(mappedBy = "contribuinte", targetEntity = Imovel.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Imovel> imoveis;

	


	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<Imovel> imoveis) {
		this.imoveis = imoveis;
	}

	public Rua getRua() {
		return this.rua;
	}

	public void setRua(Rua rua) {
		this.rua = rua;
	}

	@NotNull(message="Campo obrigatorio")
    @Size(min=2, max=50, message="Minimo 3 e o maximo 50 caracter")
	@Column(name = "nome", nullable = false, length = 50)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "cpf", length = 11)
	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "numero")
	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Column(name = "qtde_imovel")
	public Integer getQtdeImovel() {
		return this.qtdeImovel;
	}

	public void setQtdeImovel(Integer qtdeImovel) {
		this.qtdeImovel = qtdeImovel;
	}



}
