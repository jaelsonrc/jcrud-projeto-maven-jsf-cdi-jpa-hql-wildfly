package br.com.jcrud.persistence.model;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "imovel")
@NamedQueries({
	@NamedQuery(name = Imovel.BUSCA_POR_NOME, query = Imovel.BUSCA_POR_NOME)
})
public class Imovel extends AbstractEntity{

	
	private static final long serialVersionUID = 501545657145780440L;


	private String setor;
	private String quadra;
	private String zona;
	private String lote;
	private String unidade;
	private String inscricao;

	public static final String BUSCA_POR_NOME="select new br.com.jcrud.persistence.model.VO.ImovelVO(i.id, i.contribuinte.id, i.contribuinte.nome, i.contribuinte.cpf, i.contribuinte.rua.nome,  i.inscricao) from Imovel i where i.contribuinte.nome like :nome";
	
	@NotNull(message="Campo obrigatorio!")
	@ManyToOne
	@JoinColumn(name="contribuinte_id")
	private Contribuinte contribuinte;

	public Contribuinte getContribuinte() {
		return this.contribuinte;
	}

	public void setContribuinte(Contribuinte contribuinte) {
		this.contribuinte = contribuinte;
	}

	@NotNull(message="Campo obrigatorio")
    @Size(min=2, max=5, message="Minimo 2 e o maximo 5 caracter")
	@Column(name = "setor", length = 5)
	public String getSetor() {
		return this.setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	@NotNull(message="Campo obrigatorio")
    @Size(min=2, max=5, message="Minimo 2 e o maximo 5 caracter")
	@Column(name = "quadra", length = 5)
	public String getQuadra() {
		return this.quadra;
	}

	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}

	@NotNull(message="Campo obrigatorio")
    @Size(min=2, max=5, message="Minimo 2 e o maximo 5 caracter")
	@Column(name = "zona", length = 5)
	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	@NotNull(message="Campo obrigatorio")
    @Size(min=2, max=5, message="Minimo 2 e o maximo 5 caracter")
	@Column(name = "lote", length = 5)
	public String getLote() {
		return this.lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	@Column(name = "unidade", length = 5)
	public String getUnidade() {
		return this.unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	@Column(name = "inscricao", length = 20)
	public String getInscricao() {
		return this.inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

}
