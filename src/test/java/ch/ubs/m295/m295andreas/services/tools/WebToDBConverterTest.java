package ch.ubs.m295.m295andreas.services.tools;

import ch.ubs.m295.m295andreas.dto.*;
import ch.ubs.m295.generated.v1.dto.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebToDBConverterTest {

      @Test
      public void testUserConversion() {
            User user = new User();
            user.setId(1);
            user.setUsername("test");
            user.setEmail("test@gmail.com");
            user.setPassword("password");

            UserTable userTable = WebToDBConverter.convert(user);
            assertEquals(user.getId(), userTable.getId());
            assertEquals(user.getUsername(), userTable.getUsername());
            assertEquals(user.getEmail(), userTable.getEmail());
            assertEquals(user.getPassword(), userTable.getPassword());
      }

      @Test
      public void testProductConversion() {
            Product product = new Product();
            product.setId(1);
            product.setProductname("testProduct");
            product.setPrice(100.0);
            product.setSeller("seller");

            ProductTable productTable = WebToDBConverter.convert(product);
            assertEquals(product.getId(), productTable.getId());
            assertEquals(product.getProductname(), productTable.getProductname());
            assertEquals(product.getPrice(), productTable.getPrice());
            assertEquals(product.getSeller(), productTable.getSeller());
      }

      @Test
      public void testPurchaseConversion() {
            Purchase purchase = new Purchase();
            purchase.setId(1);
            purchase.setIsPending(true);

            PurchaseTable purchaseTable = WebToDBConverter.convert(purchase, 1);
            assertEquals(purchase.getId(), purchaseTable.getId());
            assertEquals(purchase.getIsPending(), purchaseTable.getIsPending());
      }

      @Test
      public void testJoinedRecordConversion() {
            // Initialize users
            User user = new User();
            user.setId(1);
            user.setUsername("test");
            user.setEmail("test@gmail.com");
            user.setPassword("password");
            List<User> users = new ArrayList<>(Arrays.asList(user));

            JoinedRecord joinedRecord = WebToDBConverter.convert(users);
            assertEquals(1, joinedRecord.getUser().size());
            assertEquals(user.getId(), joinedRecord.getUser().get(0).getId());
            assertEquals(user.getUsername(), joinedRecord.getUser().get(0).getUsername());
      }

      // Add more tests here for other methods if needed
}
