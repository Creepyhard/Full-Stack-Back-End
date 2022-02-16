package br.com.novo.condominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.novo.condominio.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	
    @Query(nativeQuery = true, value = "select u.* from usuario u where u.email = :email and u.senha = :senha ")
    public Usuario findByEmailSenha(@Param("email") String email, @Param("senha") String senha);

}
