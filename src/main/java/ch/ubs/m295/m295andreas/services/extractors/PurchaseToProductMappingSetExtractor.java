package ch.ubs.m295.m295andreas.services.extractors;

import ch.ubs.m295.m295andreas.dto.PurchaseToProductMappingTable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PurchaseToProductMappingSetExtractor implements ResultSetExtractor<Optional<PurchaseToProductMappingTable>> {
      @Override
      public Optional<PurchaseToProductMappingTable> extractData(ResultSet rs) throws SQLException, DataAccessException {
            if(rs.next()){
                  PurchaseToProductMappingTable p = new PurchaseToProductMappingTable();
                  p.setProductId(rs.getInt("productId"));
                  p.setPurchaseId(rs.getInt("purchaseId"));
                  p.setQuantity(rs.getInt("quantity"));
                  return Optional.of(p);
            }
            else {
                  return Optional.empty();
            }
      }
}
