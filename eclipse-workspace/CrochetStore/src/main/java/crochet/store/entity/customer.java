package crochet.store.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@Entity
 @Data
public class Customer { 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String customerFirstName;
    private String customerLastName;
    
    @Column(unique = true)
    private String customerEmail;
    
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "customer_supplies", joinColumns = @JoinColumn(name ="customer_id"),
    inverseJoinColumns = @JoinColumn(name ="supplies_id")) 
    private Set<Supplies> supplies = new HashSet<>();
}
