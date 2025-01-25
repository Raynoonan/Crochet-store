package crochet.store.controller.model;

import java.util.HashSet;
import java.util.Set;
import crochet.store.entity.Store;
import crochet.store.entity.Supplies;
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
  private Set<StoreSupplies> supplies = new HashSet<>();
  
  public StoreData(Store store) {
    storeId= store.getStoreId();
    storeName = store.getStoreName();
    city = store.getCity();
    state = store.getState();
    zip = store.getZip();
    
    for(Supplies supply: store.getSupplies()) {
      supplies.add(new StoreSupplies(supply));
  
  }
    
  }
  @Data
  @NoArgsConstructor
  public static class StoreSupplies {
    private Long suppliesId;
    private String type;
    private Double sizeOrWeight;
    private String color;
    private String material;
    
   public StoreSupplies(Supplies supplies) {
     suppliesId = supplies.getSuppliesId();
     type = supplies.getType();
     sizeOrWeight = supplies.getSizeOrWeight();
     color = supplies.getColor();
     material = supplies.getMaterial();
         
   }
  }
}


  
 
