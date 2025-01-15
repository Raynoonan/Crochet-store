package crochet.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import crochet.store.controller.model.CustomerData;
import crochet.store.dao.customerDao;
import crochet.store.entity.customer;

@Service
public class CrochetStoreService {
  
  @Autowired
  private customerDao customerDao;

  @Transactional(readOnly= false)
  public CustomerData saveCustomer(CustomerData customerData) {
    Long customerId = customerData.getCustomerId();
    customer customer =findOrCreateCustomer(customerId);
    
    setFieldsInCustomer(customer, customerData);
    return new CustomerData(customerDao.save(customer));
  }

  private void setFieldsInCustomer(customer customer, CustomerData customerData) {
    customer.setCustomerEmail(customerData.getCustomerEmail());
    customer.setCustomerFirstName(customerData.getCustomerFirstName());
    customer.setCustomerLastName(customerData.getCustomerLastName());
    
  }

  private customer findOrCreateCustomer(Long customerId) {
    customer customer;
    
    if(Objects.isNull(customerId)) {
      customer = new customer();
    }
    else {
      customer = findCustomerById(customerId);
    }
    return customer;
  }

  private customer findCustomerById(Long customerId) {
    return customerDao.findById(customerId).orElseThrow(() -> new NoSuchElementException(
        "Customer with ID=" + customerId + " was not found."));
  }
@Transactional(readOnly=true)
  public List<CustomerData> retrieveAllCustomers() {
    List<customer> customers = customerDao.findAll();
    List<CustomerData> response = new LinkedList<>();
    
    for(customer customer : customers) {
      response.add(new CustomerData(customer));
    }
    
    return response;
  }

public CustomerData retrieveCustomerById(Long customerId) {
  customer customer =findCustomerById(customerId);
  return new CustomerData(customer);
}

}
