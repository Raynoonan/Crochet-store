package crochet.store.controller.model;

import java.util.HashSet;
import java.util.Set;
import crochet.store.entity.Customer;
import crochet.store.entity.Store;
import crochet.store.entity.Supplies;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerData {
  
  private Long customerId;
  private String customerFirstName;
  private String customerLastName;    
  private String customerEmail;
  private Set<suppliesResponse> supplies = new HashSet<>();
  
  public CustomerData(Customer customer) {
   customerId = customer.getCustomerId();
   customerFirstName= customer.getCustomerFirstName();
   customerLastName = customer.getCustomerLastName();
   customerEmail= customer.getCustomerEmail();
   
   for(Supplies supply: customer.getSupplies()) {
     supplies.add(new suppliesResponse(supply));
     
   }
  }
  @Data
  @NoArgsConstructor
  static class suppliesResponse {
    
       private Long suppliesId;
       private String type;
       private Double sizeOrWeight;
       private String color;
       private String material;
   
       private Store store;
       
       suppliesResponse(Supplies supplies) {
         suppliesId = supplies.getSuppliesId();
         type = supplies.getType();
         sizeOrWeight = supplies.getSizeOrWeight();
         color = supplies.getColor();
         material = supplies.getMaterial();
           
             
       }
       
  }
}

