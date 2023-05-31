package ch.ubs.m295.m295andreas.dao;

import ch.ubs.m295.generated.v1.dto.User;
import ch.ubs.m295.generated.v1.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ch.ubs.m295.m295andreas.services.tools.WebToDBConverter;

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
            userDAO.addUser(WebToDBConverter.convert(user));
      }

      public void addProduct(Product product) {
            productDAO.addProduct(WebToDBConverter.convert(product));
      }

}