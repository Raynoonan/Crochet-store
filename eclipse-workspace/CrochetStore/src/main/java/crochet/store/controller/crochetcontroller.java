package crochet.store.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import crochet.store.controller.model.CustomerData;
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
    log.info("Creating customer { }", customerData);
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
}
