package ch.ubs.m295.m295andreas.services.extractors;

import ch.ubs.m295.m295andreas.dto.ProductTable;
import ch.ubs.m295.m295andreas.dto.UserTable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProductSetExtractor implements ResultSetExtractor<Optional<ProductTable>> {
      @Override
      public Optional<ProductTable> extractData(ResultSet rs) throws SQLException, DataAccessException {
            if(rs.next()) {
                  ProductTable s = new ProductTable();
                  s.setId(rs.getInt("id"));
                  s.setSeller(rs.getString("seller"));
                  s.setProductname(rs.getString("productname"));
                  s.setPrice(rs.getDouble("price"));
                  return Optional.of(s);
            }else{
                  return Optional.empty();
            }
      }
}
