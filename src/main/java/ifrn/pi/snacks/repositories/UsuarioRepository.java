package ifrn.pi.snacks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ifrn.pi.snacks.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);
	
	
}
