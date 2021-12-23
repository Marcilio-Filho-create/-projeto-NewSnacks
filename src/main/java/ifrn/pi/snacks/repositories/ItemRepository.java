package ifrn.pi.snacks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.snacks.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
