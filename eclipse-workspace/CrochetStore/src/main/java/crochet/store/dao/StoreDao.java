package crochet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import crochet.store.entity.Store;

public interface StoreDao extends JpaRepository<Store, Long> {

}
