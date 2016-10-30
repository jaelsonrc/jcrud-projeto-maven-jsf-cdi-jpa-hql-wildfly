package br.com.jcrud.persistence.model.VO;

public class ImovelVO {
	
	private Long id;
	private Long contribuinte_id;
	private String nome;
	private String cpf;
	private String rua;
	private String inscricao;
	
	public ImovelVO(){
		super();
	}
	
	public ImovelVO(Long id, Long contribuinte_id, String nome, String cpf, String rua, String inscricao) {
		super();
		this.id = id;
		this.contribuinte_id = contribuinte_id;
		this.nome = nome;
		this.cpf = cpf;
		this.rua = rua;
		this.inscricao = inscricao;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getContribuinte_id() {
		return contribuinte_id;
	}
	public void setContribuinte_id(Long contribuinte_id) {
		this.contribuinte_id = contribuinte_id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	
	
	

}
