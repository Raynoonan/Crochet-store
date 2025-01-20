package crochet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import crochet.store.entity.store;

public interface StoreDao extends JpaRepository<store, Long> {

}
