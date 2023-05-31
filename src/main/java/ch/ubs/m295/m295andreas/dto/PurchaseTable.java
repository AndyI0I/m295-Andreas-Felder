package ch.ubs.m295.m295andreas.dto;

public class PurchaseTable {
      private Integer id;

      private Integer userId;

      private Boolean isPending;

      public PurchaseTable() {}

      public PurchaseTable(Integer id, Integer userId, Boolean isPending) {
    	  this.id = id;
    	  this.userId = userId;
    	  this.isPending = isPending;
      }

      public Integer getId() {
            return id;
      }

      public void setId(Integer id) {
            this.id = id;
      }

      public Integer getUserId() {
    	  return userId;
      }

      public void setUserId(Integer userId) {
    	  this.userId = userId;
      }

      public Boolean getIsPending() {
    	  return isPending;
      }

      public void setIsPending(Boolean isPending) {
    	  this.isPending = isPending;
      }

}
