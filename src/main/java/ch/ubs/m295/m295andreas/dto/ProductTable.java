package ch.ubs.m295.m295andreas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductTable {
      private Integer id;

      private String productname;

      private String seller;

      private Double price;

      public ProductTable() {}

      public ProductTable(Integer id, String productname, String seller, Double price) {
    	  this.id = id;
    	  this.productname = productname;
    	  this.seller = seller;
    	  this.price = price;
      }

      public Integer getId() {
            return id;
      }

      public void setId(Integer id) {
            this.id = id;
      }

      public String getProductname() {
            return productname;
      }

      public void setProductname(String productname) {
            this.productname = productname;
      }

      public String getSeller() {
            return seller;
      }

      public void setSeller(String seller) {
            this.seller = seller;
      }

      public Double getPrice() {
            return price;
      }

      public void setPrice(Double price) {
            this.price = price;
      }
}
