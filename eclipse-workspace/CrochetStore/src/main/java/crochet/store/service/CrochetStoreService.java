package crochet.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ssl.JksSslBundleProperties.Store;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import crochet.store.controller.model.CustomerData;
import crochet.store.controller.model.StoreData;
import crochet.store.controller.model.SuppliesData;
import crochet.store.dao.StoreDao;
import crochet.store.dao.SuppliesDao;
import crochet.store.dao.customerDao;
import crochet.store.entity.customer;
import crochet.store.entity.store;
import crochet.store.entity.supplies;

@Service
public class CrochetStoreService {
  
  @Autowired
  private customerDao customerDao;
  
  @Autowired
  private SuppliesDao suppliesDao;
  
  @Autowired
  private StoreDao storeDao;
  

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
@Transactional(readOnly = false)
public CustomerData retrieveCustomerById(Long customerId) {
  customer customer =findCustomerById(customerId);
  return new CustomerData(customer);
}
@Transactional(readOnly = false)
public void deleteCustomerById(Long customerId) {
  customer customer = findCustomerById(customerId);
  customerDao.delete(customer);
  
}
@Transactional(readOnly= false)
public StoreData saveStore(Long customerId, StoreData storeData) {
  customer customer = findCustomerById(customerId);
  
  Set<supplies> supplies = suppliesDao.findAllBySupplyIn(storeData.getSupplies());
  
 Store store = findOrCreateStore(storeData.getStoreId());
  setStoreFields(store, storeData);
  
  store.setcustomer(customer);
  customer.getStore().add(store);
  
  for(supplies Supplies : supplies) {
   Supplies.getStore().add(store);
   supplies.getSupplies().add(supplies);
  }
  store dbStore = StoreDao.save(store);
  return new StoreData(dbStore);
}

private void setStoreFields(store store, StoreData storeData) {
 store.setCity(store.getCity());
 store.setState(store.getState());
 store.setStoreName(store.getStoreName());
 store.setStoreId(store.getStoreId());
 store.setZip(store.getZip());
  
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

public store findStoreById(Long storeId) {
  return storeDao.findById(storeId)
      .orElseThrow(() -> new NoSuchElementException(
          "Store with ID=" + storeId + " does not exist."));
}

public SuppliesData saveSupplies(Long customerId, SuppliesData suppliesData) {
  // TODO Auto-generated method stub
  return null;
}

}
