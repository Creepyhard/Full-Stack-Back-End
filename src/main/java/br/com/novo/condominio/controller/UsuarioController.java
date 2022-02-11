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
@CrossOrigin(origins="http://localhost:4200")  
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

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

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<Object> delete(@PathVariable long id) {
		return usuarioRepository.findById(id).map(record -> {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
