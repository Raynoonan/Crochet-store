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
public class SuppliesData {

  private Long suppliesId;
  private String type;
  private Double sizeOrWeight;
  private String color;
  private String material;
  private Set<CustomerSupplies> customers = new HashSet<>();
  private Store store;
  
  public SuppliesData( Supplies supplies) {
    suppliesId = supplies.getSuppliesId();
    type = supplies.getType();
    sizeOrWeight = supplies.getSizeOrWeight();
    color = supplies.getColor();
    material = supplies.getColor();
    
    for(Customer customer: supplies.getCustomer()) {
      customers.add(new CustomerSupplies(customer));
  }
}

@Data
@NoArgsConstructor 
static class CustomerSupplies {
  private Long customerId;
  private String customerFirstName;
  private String customerLastName;
  private String customerEmail;
  
  public CustomerSupplies(Customer customer) {
    customerId = customer.getCustomerId();
    customerFirstName = customer.getCustomerFirstName();
    customerLastName = customer.getCustomerLastName();
    customerEmail = customer.getCustomerEmail();
    
  }
}
@Data
@NoArgsConstructor
static class StoreSupplies {
  private Long storeId;
  private String storeName;
  private String city;
  private String state;
  private String zip;
  
  public StoreSupplies(Store store) {
    storeId = store.getStoreId();
    storeName = store.getStoreName();
    city = store.getCity();
    state = store.getState();
    zip = store.getZip();
  }
}
}
  
  



  

