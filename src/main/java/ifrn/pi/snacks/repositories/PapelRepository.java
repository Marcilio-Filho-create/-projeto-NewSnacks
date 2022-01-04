package ifrn.pi.snacks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.snacks.models.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {
	
	List<Papel> findAllByTipo(char tipo);

}
