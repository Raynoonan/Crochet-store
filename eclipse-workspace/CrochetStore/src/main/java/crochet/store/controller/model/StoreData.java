package crochet.store.controller.model;

import java.util.HashSet;
import java.util.Set;
import crochet.store.controller.model.CustomerData.suppliesResponse;
import crochet.store.entity.customer;
import crochet.store.entity.store;
import crochet.store.entity.supplies;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor


public class StoreData {

  private Long storeId;
  private String storeName;
  private String city;
  private String state;
  private String zip;
  private Set<String> supplies = new HashSet<>();
  
  public StoreData(store store) {
    storeId= store.getStoreId();
    storeName = store.getStoreName();
    city = store.getCity();
    state = store.getState();
    zip = store.getZip();
    
    for(supplies supply: store.getSupplies()) {
      supplies.add(new Storesupplies(supply));
  
  }
  }
  @Data
  @NoArgsConstructor
  public static class Storesupplies {
    private Long suppliesId;
    private String type;
    private Double sizeOrWeight;
    private String color;
    private String material;
    
   public Storesupplies(supplies supplies) {
     suppliesId = supplies.getSuppliesId();
     type = supplies.getType();
     sizeOrWeight = supplies.getSizeOrWeight();
     color = supplies.getColor();
     material = supplies.getMaterial();
         
   }
  }
}


  
 
