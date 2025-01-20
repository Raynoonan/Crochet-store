package crochet.store.dao;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import crochet.store.entity.supplies;

public interface SuppliesDao extends JpaRepository<supplies, Long> {

  Set<supplies> findAllBySupplyIn(Set<String> supplies);

}
