package crochet.store.controller.model;

import java.util.HashSet;
import java.util.Set;
import crochet.store.entity.customer;
import crochet.store.entity.store;

public class SuppliesData {

  private Long suppliesId;
  private String type;
  private Double sizeOrWeight;
  private String color;
  private String material;
  private Set<customer> customer = new HashSet<>();
  private store store;
}
