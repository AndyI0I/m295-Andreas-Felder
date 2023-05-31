package ch.ubs.m295.m295andreas.dto;

import java.util.List;

public class JoinedRecord {
      private List<UserTable> user;

      private List<PurchaseTable> purchases;

      private List<ProductTable> products;

      private List<PurchaseToProductMapping> mappings;

      public JoinedRecord() {}

      public JoinedRecord(List<UserTable> user, List<PurchaseTable> purchases, List<ProductTable> products, List<PurchaseToProductMapping> mappings) {
    	  this.user = user;
    	  this.purchases = purchases;
    	  this.products = products;
    	  this.mappings = mappings;
      }

      public List<UserTable> getUser() {
    	  return user;
      }

      public void setUser(List<UserTable> user) {
    	  this.user = user;
      }

      public List<PurchaseTable> getPurchases() {
    	  return purchases;
      }

      public void setPurchases(List<PurchaseTable> purchases) {
    	  this.purchases = purchases;
      }

      public List<ProductTable> getProducts() {
    	  return products;
      }

      public void setProducts(List<ProductTable> products) {
    	  this.products = products;
      }

      public List<PurchaseToProductMapping> getMappings() {
    	  return mappings;
      }

      public void setMappings(List<PurchaseToProductMapping> mappings) {
    	  this.mappings = mappings;
      }
}
