package ch.ubs.m295.m295andreas.dao;

import ch.ubs.m295.generated.v1.dto.User;
import ch.ubs.m295.generated.v1.dto.Product;
import ch.ubs.m295.m295andreas.dao.subDAOs.ProductDAO;
import ch.ubs.m295.m295andreas.dao.subDAOs.PurchaseDAO;
import ch.ubs.m295.m295andreas.dao.subDAOs.PurchaseToProductMappingDAO;
import ch.ubs.m295.m295andreas.dao.subDAOs.UserDAO;
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
            userDAO.add(WebToDBConverter.convert(user));
      }

      public void addProduct(Product product) {
            productDAO.add(WebToDBConverter.convert(product));
      }

}