package br.com.novo.condominio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.novo.condominio.service.UsuarioService;
import lombok.Data;
//Data: cria automaticamente os métodos toString, equals, hashCode, getters e setters.
//A anotação @Entity pertence ao JPA e isso significa que a classe será automaticamente mapeada à tabela com o mesmo nome.
@Data
@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String senha;
	
	
	
	
	public Usuario() {
	}
	
	public Usuario(Long id, String nome, String senha, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;	
		
	}
}
