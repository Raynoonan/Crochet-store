package crochet.store.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class supplies {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long suppliesId;
   private String type;
   private Double sizeOrWeight;
   private String color;
   private String material;
   
   @ManyToMany(mappedBy= "supplies")
   private Set<customer> customer = new HashSet<>();
   
   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "store_id", nullable = false)
   private store store;
   
}
