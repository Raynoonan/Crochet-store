package crochet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import crochet.store.entity.customer;

public interface customerDao extends JpaRepository<customer, Long> {

}
