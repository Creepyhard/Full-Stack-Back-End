package br.com.novo.condominio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.novo.condominio.model.Usuario;
import br.com.novo.condominio.repository.UsuarioRepository;
import br.com.novo.condominio.service.UsuarioService;

@CrossOrigin(origins="http://localhost:4200")  
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private UsuarioService usuarioService;

	@GetMapping
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	// findById da interface JpaRepository irá fazer um select * from contacts where
	// id = ?. Caso o registro seja encontrado, é retornado o status HTTP 200.
	// ResponseEntity para indicar sucesso.
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Usuario> buscarId(@PathVariable long id) {
		return usuarioRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Usuario adicionar(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@PutMapping
	public Usuario atualizar(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	@PutMapping({"/{id}"})
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetalhes){
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow();
		
		usuario.setNome(usuarioDetalhes.getNome());
		usuario.setEmail(usuarioDetalhes.getEmail());
		usuario.setSenha(usuarioDetalhes.getSenha());
		
		Usuario atualizaUsuario = usuarioRepository.save(usuario);
		return ResponseEntity.ok(atualizaUsuario);
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<Object> delete(@PathVariable long id) {
		return usuarioRepository.findById(id).map(record -> {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	@PostMapping("/login")
	public Usuario loginUsuario(@RequestBody Usuario usuario) throws Exception {
		String emailT = usuario.getEmail();
		String senhaT = usuario.getSenha();
		Usuario usuarioObjeto = null;
		if(emailT != null && senhaT != null) {
			usuarioObjeto = usuarioRepository.findByEmailSenha(emailT, senhaT);
		}
		if (usuarioObjeto == null) {
			throw new Exception("Email ou senha incorreto");
		}
		return usuarioObjeto;
	}
}
