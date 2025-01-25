package crochet.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import crochet.store.controller.model.CustomerData;
import crochet.store.controller.model.StoreData;
import crochet.store.controller.model.SuppliesData;
import crochet.store.dao.StoreDao;
import crochet.store.dao.SuppliesDao;
import crochet.store.dao.CustomerDao;
import crochet.store.entity.Customer;
import crochet.store.entity.Store;
import crochet.store.entity.Supplies;


@Service
public class CrochetStoreService {
  
  @Autowired
  private CustomerDao customerDao;
  
  @Autowired
  private SuppliesDao suppliesDao;
  
  @Autowired
  private StoreDao storeDao;
  

  @Transactional(readOnly= false)
  public CustomerData saveCustomer(CustomerData customerData) {
    Long customerId = customerData.getCustomerId();
    Customer customer =findOrCreateCustomer(customerId);
    
    setFieldsInCustomer(customer, customerData);
    return new CustomerData(customerDao.save(customer));
  }

  private void setFieldsInCustomer(Customer customer, CustomerData customerData) {
    customer.setCustomerEmail(customerData.getCustomerEmail());
    customer.setCustomerFirstName(customerData.getCustomerFirstName());
    customer.setCustomerLastName(customerData.getCustomerLastName());
    
  }

  private Customer findOrCreateCustomer(Long customerId) {
    Customer customer;
    
    if(Objects.isNull(customerId)) {
      customer = new Customer();
    }
    else {
      customer = findCustomerById(customerId);
    }
    return customer;
  }

  private Customer findCustomerById(Long customerId) {
    return customerDao.findById(customerId).orElseThrow(() -> new NoSuchElementException(
        "Customer with ID=" + customerId + " was not found."));
  }
@Transactional(readOnly=true)
  public List<CustomerData> retrieveAllCustomers() {
    List<Customer> customers = customerDao.findAll();
    List<CustomerData> response = new LinkedList<>();
    
    for(Customer customer : customers) {
      response.add(new CustomerData(customer));
    }
    
    return response;
  }
@Transactional(readOnly = false)
public CustomerData retrieveCustomerById(Long customerId) {
  Customer customer =findCustomerById(customerId);
  return new CustomerData(customer);
}
@Transactional(readOnly = false)
public void deleteCustomerById(Long customerId) {
  Customer customer = findCustomerById(customerId);
  customerDao.delete(customer);
  
}
@Transactional(readOnly= false)
public StoreData saveStore(StoreData storeData) {
  
  Long storeId = storeData.getStoreId();
 
 Store store = findOrCreateStore(storeId);
  setStoreFields(store, storeData);

  Store dbStore = storeDao.save(store);
  return new StoreData(dbStore);
}

private void setStoreFields(Store store, StoreData storeData) {
 store.setCity(storeData.getCity());
 store.setState(storeData.getState());
 store.setStoreName(storeData.getStoreName());
 store.setStoreId(storeData.getStoreId());
 store.setZip(storeData.getZip());
  
}

private Store findOrCreateStore(Long storeId) {
  Store store;
  
  if(Objects.isNull(storeId)) {
    store = new Store();
  }
  else {
    store = findStoreById(storeId);
  }
  return store;
}

public Store findStoreById(Long storeId) {
  return storeDao.findById(storeId)
      .orElseThrow(() -> new NoSuchElementException(
          "Store with ID=" + storeId + " does not exist."));
}
public void addSuppliesToCustomer(Long suppliesId, Long customerId) {
  Supplies supplies = findOrCreateSupplies(suppliesId);
  Customer customer = findOrCreateCustomer(customerId);
  
  customer.getSupplies().add(supplies);
  supplies.getCustomer().add(customer);
  
}
private Supplies findOrCreateSupplies(Long suppliesId, Long storeId) {
  Supplies supplies;
  
  if(Objects.isNull(suppliesId)) {
    supplies = new Supplies();
  }
  else {
    supplies = findSuppliesById(suppliesId, storeId);
  }
  return supplies;
}

private Supplies findSuppliesById(Long suppliesId, Long storeId) {
  Supplies supplies = suppliesDao.findById(suppliesId).orElseThrow(() -> new NoSuchElementException( 
      "Supplies with ID=" + suppliesId + " does not exist."));
  
  if(supplies.getStore().getStoreId() != storeId) {
    throw new IllegalArgumentException("Supply with ID" + suppliesId + 
        " is not sold at the store with ID=" + storeId);
  }
  return supplies;
  
}

public SuppliesData saveSupplies(Long storeId, SuppliesData suppliesData) {
  Store store = findStoreById(storeId);
  Long suppliesId = suppliesData.getSuppliesId();
  Supplies supplies = findOrCreateSupplies(suppliesId, storeId);
  copysuppliesFields(supplies, suppliesData);
  
  supplies.setStore(store);
  store.getSupplies().add(supplies);
  Supplies dbSupplies = suppliesDao.save(supplies);
   
   return new SuppliesData(dbSupplies);
}
   
   private Supplies findOrCreateSupplies(Long suppliesId) {
     Supplies supplies;
     
     if(Objects.isNull(suppliesId)) {
       supplies = new Supplies();
     }
     else {
       supplies = findSuppliesById(suppliesId);
     }
     return supplies;
}

private Supplies findSuppliesById(Long suppliesId) {
  return suppliesDao.findById(suppliesId).orElseThrow(() -> new NoSuchElementException( 
      "Supplies with ID=" + suppliesId + " does not exist."));
  }

private void copysuppliesFields(Supplies supplies, SuppliesData suppliesData) {
  supplies.setSuppliesId(suppliesData.getSuppliesId());
  supplies.setColor(suppliesData.getColor());
  supplies.setMaterial(supplies.getMaterial());
  supplies.setSizeOrWeight(supplies.getSizeOrWeight());
  supplies.setType(supplies.getType());
  
}

}
