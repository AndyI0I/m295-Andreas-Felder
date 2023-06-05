package ch.ubs.m295.m295andreas.dao;

import ch.ubs.m295.generated.v1.dto.Purchase;
import ch.ubs.m295.generated.v1.dto.User;
import ch.ubs.m295.generated.v1.dto.Product;
import ch.ubs.m295.m295andreas.dao.subDAOs.ProductDAO;
import ch.ubs.m295.m295andreas.dao.subDAOs.PurchaseDAO;
import ch.ubs.m295.m295andreas.dao.subDAOs.PurchaseToProductMappingDAO;
import ch.ubs.m295.m295andreas.dao.subDAOs.UserDAO;
import ch.ubs.m295.m295andreas.dto.ProductTable;
import ch.ubs.m295.m295andreas.dto.PurchaseTable;
import ch.ubs.m295.m295andreas.dto.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ch.ubs.m295.m295andreas.services.tools.WebToDBConverter;

import java.util.ArrayList;
import java.util.List;

public class DBOrchestrator {

      @Autowired
      private NamedParameterJdbcTemplate jdbcTemplate;
      @Autowired
      private UserDAO userDAO;
      @Autowired
      private ProductDAO productDAO;
      @Autowired
      private PurchaseDAO purchaseDAO;
      @Autowired
      private PurchaseToProductMappingDAO purchaseToProductMappingDAO;

      public void addUser(User user) {
            userDAO.add(WebToDBConverter.convert(user));
      }

      public void addProduct(Product product) {
            productDAO.add(WebToDBConverter.convert(product));
      }

      public void addPurchase(Purchase purchase, int userId) {
            purchaseDAO.add(WebToDBConverter.convert(purchase, userId));
      }

      public void addPurchaseToProductMapping(Purchase purchase, int productId) {
            WebToDBConverter.convertToMappingList(purchase, productId).forEach(purchaseToProductMappingDAO::add);
      }

      public void deletePurchase(int purchaseId) {
            purchaseToProductMappingDAO.deleteByPurchaseId(purchaseId);
            purchaseDAO.delete(purchaseId);
      }

      public int deleteProduct(int productId) {
            int affectedRows = 0;
            affectedRows += purchaseToProductMappingDAO.deleteByProductId(productId);
            affectedRows += productDAO.delete(productId);
            return affectedRows;
      }

      public List<Purchase> getPurchasesByUserId(int userId) {
            List<PurchaseTable> purchases = purchaseDAO.getByUserId(userId);
            List<Purchase> purchasesResult = new ArrayList<>();
            purchases.forEach(purchase -> {
                  Purchase p = WebToDBConverter.convert(purchase);
                  p.setProducts(new ArrayList<>());
                  purchaseToProductMappingDAO.getByPurchaseId(purchase.getId()).forEach(mapping -> {
                        ProductTable pt = productDAO.get(mapping.getProductId()).orElseThrow();
                        Product pr = WebToDBConverter.convert(pt);
                        pr.setQuantity(mapping.getQuantity());
                        p.getProducts().add(pr);
                  });
                  purchasesResult.add(p);
            });
            return purchasesResult;
      }

      public void deleteUser(int userId) {
            getPurchasesByUserId(userId).forEach(purchase -> deletePurchase(purchase.getId()));
            userDAO.delete(userId);
      }

      public Product getProductById(int productId) {
            ProductTable pt = productDAO.get(productId).orElseThrow();
            return WebToDBConverter.convert(pt);
      }

      public List<User> getAllUsers() {
            List<UserTable> users = userDAO.getAll();
            List<User> usersResult = new ArrayList<>();
            users.forEach(user -> {
                  User u = WebToDBConverter.convert(user);
                  u.setPurchaseHistory(getPurchasesByUserId(u.getId()));
            });
            return usersResult;
      }

      public List<Product> getAllProducts() {
            List<ProductTable> products = productDAO.getAll();
            List<Product> productsResult = new ArrayList<>();
            products.forEach(product -> {
                  Product p = WebToDBConverter.convert(product);
                  productsResult.add(p);
            });
            return productsResult;
      }

      public List<Purchase> getAllPurchases() {
            List<PurchaseTable> purchases = purchaseDAO.getAll();
            List<Purchase> purchasesResult = new ArrayList<>();
            purchases.forEach(purchase -> {
                  Purchase p = WebToDBConverter.convert(purchase);
                  p.setProducts(new ArrayList<>());
                  purchaseToProductMappingDAO.getByPurchaseId(purchase.getId()).forEach(mapping -> {
                        ProductTable pt = productDAO.get(mapping.getProductId()).orElseThrow();
                        Product pr = WebToDBConverter.convert(pt);
                        pr.setQuantity(mapping.getQuantity());
                        p.getProducts().add(pr);
                  });
                  purchasesResult.add(p);
            });
            return purchasesResult;
      }

}