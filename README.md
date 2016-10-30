# JCrud - Projeto exemplo

CRUD completo com JSF, CDI, Hibernate 5.0.10/JPA 2.1 com c3p0 como pool de conexões,Prettyfaces, Primefaces, Maven, MySQL 5.7, Wildfly 10, Internacionalização e o Eclipse como IDE...

		Projeto Maven
		
			src/main/java: contem toda parte back-end os codigos fontes java
			
			src/main/resources: contem persistence.xml, as query hql e as message.properties responsável pela internacionalização
			
			src/main/webapp: contem os arquivo xhtml e parte front-end da aplicação e os arquivo de configuração do jsf
			
		Iniciando Leitura do Projeto
		
		
			src/main/webapp:
			
				pages: contem todas pagina em xhtml do projeto
				
				resources: contem os css, img, js
				
				WEB-INF: contem os arquivo de configuração e templete base do projeto
				
					beans.xml: arquivo responsável pelo cdi, não precisa mexer...
					
					faces-config.xml: arquivo responsável por carrega codigo de internacionalização, não precisa mexer...
					
					pretty-config.xml: arquivo responsável pela url amigavel do projeto. informe aqui toda rota do projeto...
					
					web.xml: arquivo responsável pela configuração do jsf e do primefaces  e etc... não precisa mexer
					
			src/main/resources:
			
				META_INF > persistence.xml: arquivo responsável pela conexão com banco. informe aqui a configuração do banco
				
				hql.xml: arquivo responsável pelo query HQL aqqui vc concentra todas as querie assim quando precisa da manutenção fica mais facil...
				
				messege.propertie: arquivo responsável pela internacionalização da aplicação contem todas mensagem do app
				
				sql.xml: caso precise de querie sql vc coloca aqui...
				
			src/main/java:
			
				br.com.jcrud.annotations: anotação responsável pela transação persistência. Não precisa mexer...
				
				br.com.jcrud.bean: o bean contem os condigo controler que repassa para view.
				
				br.com.jcrud.config: codigo jpa entityManagerFactory é responsável por carregar o persistence.xml.
				
				br.com.jcrud.converter: codigo genérico responsável pela conversão dos objeto que vem da view.
				
				br.com.jcrud.persistence.dao: codigo quem contem implementação do dao genérico
				
				br.com.jcrud.persistence.daointerfaces: contem a interface do dao genérico
				
				br.com.jcrud.persistence.fileservice: codigo que carrega hql do arquivo xml em resources
				
				br.com.jcrud.persistence.model: contem os codigo que modela o banco como toda tabela tem chave primeira a classe AbstracEntity contem chave primaria das tabela aonde todos os model extende dela...
				
				br.com.jcrud.persistence.model.VO: contem classe personalizada para resultado de consulta hql
				
				
				br.com.jcrud.util: contem os codigo de utilidade como messagem na tela "FacesUtil", "StringUtil" junta % para hql com like e "Text" responsável por carrega internacionalização...
				

				Espero que tenha ajudado vocês no estudo e sugiro ainda a crianção do pacote services para separa as regra de negocio do beans...
				
				Quero agradecer ao DevDojo pelo maravilhoso curso projeto inicial graças a eles pode fazer toda a base do crud  e recomendo vcs fazer curso e se inscrever
				https://www.youtube.com/playlist?list=PL62G310vn6nGH6ugBwHbskXxKIaoqz_dk
				
				qualquer duvida mande um e-mail: jaelsonrc@ymail.com
