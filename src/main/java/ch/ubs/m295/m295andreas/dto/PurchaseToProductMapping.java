package ch.ubs.m295.m295andreas.dto;

//model for holding the mapping between a purchase and a product
public class PurchaseToProductMapping {

      private Integer purchaseId;

      private Integer productId;

      private Integer quantity;

      public PurchaseToProductMapping() {}

      public PurchaseToProductMapping(Integer purchaseId, Integer productId, Integer quantity) {
    	  this.purchaseId = purchaseId;
    	  this.productId = productId;
    	  this.quantity = quantity;
      }

      public Integer getPurchaseId() {
    	  return purchaseId;
      }

      public void setPurchaseId(Integer purchaseId) {
    	  this.purchaseId = purchaseId;
      }

      public Integer getProductId() {
    	  return productId;
      }

      public void setProductId(Integer productId) {
    	  this.productId = productId;
      }

      public Integer getQuantity() {
    	  return quantity;
      }

      public void setQuantity(Integer quantity) {
    	  this.quantity = quantity;
      }
}
