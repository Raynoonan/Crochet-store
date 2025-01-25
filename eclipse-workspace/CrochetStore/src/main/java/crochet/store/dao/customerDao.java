package crochet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import crochet.store.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
