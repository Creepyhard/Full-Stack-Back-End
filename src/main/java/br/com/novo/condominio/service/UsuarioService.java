package br.com.novo.condominio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.novo.condominio.model.Usuario;
import br.com.novo.condominio.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario salvarUsuario (Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario acharEmailESenha(String email, String senha) {
		return usuarioRepository.findByEmailSenha(email, senha);
		
	}
	
}
