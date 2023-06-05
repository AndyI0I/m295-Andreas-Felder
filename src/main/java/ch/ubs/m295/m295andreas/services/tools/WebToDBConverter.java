package ch.ubs.m295.m295andreas.services.tools;

import ch.ubs.m295.m295andreas.dto.*;
import ch.ubs.m295.generated.v1.dto.*;

import java.util.ArrayList;
import java.util.List;

public class WebToDBConverter {

      public static User convert(UserTable userTable) {
            User user = new User();
            user.setId(userTable.getId());
            user.setUsername(userTable.getUsername());
            user.setEmail(userTable.getEmail());
            user.setPassword(userTable.getPassword());
            return user;
      }

      public static UserTable convert(User user) {
            UserTable userTable = new UserTable();
            userTable.setId(user.getId());
            userTable.setUsername(user.getUsername());
            userTable.setEmail(user.getEmail());
            userTable.setPassword(user.getPassword());
            return userTable;
      }

      public static Product convert(ProductTable productTable) {
            Product product = new Product();
            product.setId(productTable.getId());
            product.setProductname(productTable.getProductname());
            product.setPrice(productTable.getPrice());

            product.setSeller(productTable.getSeller());
            return product;
      }

      public static ProductTable convert(Product product) {
            ProductTable productTable = new ProductTable();
            productTable.setId(product.getId());
            productTable.setProductname(product.getProductname());
            productTable.setPrice(product.getPrice());
            productTable.setSeller(product.getSeller());
            return productTable;
      }

      public static PurchaseTable convert(Purchase purchase, int userId) {
            PurchaseTable purchaseTable = new PurchaseTable();
            purchaseTable.setId(purchase.getId());
            purchaseTable.setIsPending(purchase.getIsPending());
            purchaseTable.setUserId(userId);
            return purchaseTable;
      }

      public static JoinedRecord convert (List<User> users){
            JoinedRecord joinedRecord = new JoinedRecord();
            List<UserTable> userTables = new ArrayList<>();
            List<PurchaseTable> purchaseTables = new ArrayList<>();
            List<ProductTable> productTables = new ArrayList<>();
            List<PurchaseToProductMappingTable> purchaseToProductMappings = new ArrayList<>();

            users.forEach(user -> {
                  userTables.add(convert(user));

                  if(user.getPurchaseHistory() != null){
                  user.getPurchaseHistory().forEach(purchase -> {
                        purchaseTables.add(convert(purchase, user.getId()));
                        purchase.getProducts().forEach(product -> {
                              purchaseToProductMappings.add(new PurchaseToProductMappingTable(purchase.getId(), product.getId(), product.getQuantity()));
                        });
                  });
                  }
            });

            joinedRecord.setUser(userTables);
            joinedRecord.setPurchases(purchaseTables);
            joinedRecord.setMappings(purchaseToProductMappings);
            joinedRecord.setMappings(purchaseToProductMappings);

            return joinedRecord;
      }

      public static Purchase convert(PurchaseTable purchaseTable){
            Purchase purchase = new Purchase();
            purchase.setId(purchaseTable.getId());
            purchase.setIsPending(purchaseTable.getIsPending());
            return purchase;
      }

      public static PurchaseToProductMappingTable convertToMapping (Purchase purchase){
            PurchaseToProductMappingTable purchaseToProductMappingTable = new PurchaseToProductMappingTable();
            purchaseToProductMappingTable.setPurchaseId(purchase.getId());
            purchaseToProductMappingTable.setProductId(purchase.getProducts().get(0).getId());
            purchaseToProductMappingTable.setQuantity(purchase.getProducts().get(0).getQuantity());

            return purchaseToProductMappingTable;
      }

      public static List<PurchaseToProductMappingTable> convertToMappingList (Purchase purchase, int userId){
            List<PurchaseToProductMappingTable> purchaseToProductMappingTable = new ArrayList<>();
            purchase.getProducts().forEach(product -> {
                  purchaseToProductMappingTable.add(convertToMapping(purchase));
            });
            return purchaseToProductMappingTable;
      }

}
