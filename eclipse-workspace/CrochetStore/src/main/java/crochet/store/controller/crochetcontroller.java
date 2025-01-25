package crochet.store.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import crochet.store.controller.model.CustomerData;
import crochet.store.controller.model.StoreData;
import crochet.store.controller.model.SuppliesData;
import crochet.store.service.CrochetStoreService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/crochet_store")
@Slf4j
public class crochetcontroller {
  @Autowired
  private CrochetStoreService crochetStoreService;

  @PostMapping("/customer")
  @ResponseStatus(code = HttpStatus.CREATED)
  public CustomerData insertCustomer(@RequestBody CustomerData customerData) {
    log.info("Creating customer {}", customerData);
    return crochetStoreService.saveCustomer(customerData);
  }
  @PutMapping("/customer/{customerId}")
  public CustomerData updateCustomer(@PathVariable Long customerId, 
      @RequestBody CustomerData customerData) {
    customerData.setCustomerId(customerId);
    log.info("Updating customer {}", customerData);
    return crochetStoreService.saveCustomer(customerData);
    
  }
  @GetMapping("/customer")
  public List<CustomerData> retrieveAllCustomers(){
    log.info("Retrieve all Customers.");
    return crochetStoreService.retrieveAllCustomers();
  }
  
  @GetMapping("/customer/{customerId}")
  public CustomerData retrieveCustomerById(@PathVariable Long customerId) {
    log.info("Retrieving customer By ID={}", customerId);
    return crochetStoreService.retrieveCustomerById(customerId);
    
  }
  @DeleteMapping("/customer")
  public void deleteAllCustomers() {
    log.info("Deleting all customers..");
    throw new UnsupportedOperationException("Deleting all customers is not allowed.");
  }
  @DeleteMapping("/customer/{customerId}")
  public Map<String, String> deleteCustomerById(@PathVariable Long customerId) {
    log.info("Deleting customer with ID = {}",customerId);
    
    crochetStoreService.deleteCustomerById(customerId);
    return Map.of("message", "Deleted customer with ID=" + customerId);
  }
  @ResponseStatus(code = HttpStatus.CREATED)
  @PostMapping("/store")
  public StoreData insertStore( @RequestBody StoreData storeData) {
    
    log.info("Creating store {}", storeData);
    
    return crochetStoreService.saveStore(storeData);
  }
    
   @ResponseStatus(code = HttpStatus.CREATED)
   @PostMapping("/customer/{customerId}/supplies")
   public SuppliesData insertSupplies(@PathVariable Long customerId,
       @RequestBody SuppliesData suppliesData) {
     
     log.info("Creating supplies {} for customer with ID={}", customerId, suppliesData);
     
     return crochetStoreService.saveSupplies(customerId, suppliesData);
   }
  }
  
